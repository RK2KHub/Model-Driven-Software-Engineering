package com.hotel.jupiter.model;

import com.hotel.jupiter.AllData;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Reservation {

    private Invoice invoice;
    private ReservationReport reservationReport;
    private Customer customer;
    private CheckinManagement checkinManagement;
    private GeneralManager generalManager;
    private List<Room> rooms = new ArrayList<>();
    private int reservationId;
    private long reservationDate;
    private int maxPeriod;

    public Reservation() {
    	
    	this.rooms.addAll(AllData.roomList.stream()
                .filter(r -> r.getIsAvailable()) // Assuming the getter is named getIsAvailable()
                .collect(Collectors.toList()));
    	
    	// current date in milliseconds
    	this.reservationDate = System.currentTimeMillis();
    	
    	// max period in milliseconds
    	this.maxPeriod = 3; // 3 days
    	
    	// reservation id
    	this.reservationId = AllData.reservationList.size() + 1;
    	
    	
    	/********************Check for reservations that are more than their validity period and cancel them*************************/
    	
    	// retrieve list of active reservations
		List<Reservation> reservations = AllData.reservationList.stream()
			    .filter(r -> (r.getCustomer() != null) && ((System.currentTimeMillis() - r.reservationDate) > (r.maxPeriod * 24 * 60 * 60 * 1000)))
			    .collect(Collectors.toList());
		
		// no need to proceed if the list is empty
		if (!reservations.isEmpty()) {
			
			for (Reservation reservation : reservations) {
				
				// remove the reservations
				reservation.checkinManagement.cancelBooking();
			}
		}
    }
    
    
    /****************************Setters and Getters***********************************/
    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public ReservationReport getReservationReport() {
        return reservationReport;
    }

    public void setReservationReport(ReservationReport reservationReport) {
        this.reservationReport = reservationReport;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CheckinManagement getCheckinManagement() {
        return checkinManagement;
    }

    public void setCheckinManagement(CheckinManagement checkinManagement) {
        this.checkinManagement = checkinManagement;
    }
    
    public GeneralManager getGeneralManager() {
        return generalManager;
    }

    public void setGeneralManager(GeneralManager generalManager) {
        this.generalManager = generalManager;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms (List<Room> rooms) {
        this.rooms = rooms;
    }

    public long getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(long reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getMaxPeriod() {
        return maxPeriod;
    }

    public void setMaxPeriod(int maxPeriod) {
        this.maxPeriod = maxPeriod;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }
    /*************************************************************/
    
    
    public String createReservation(int roomId) {
		// Establish association between reservation and customer
		this.customer = this.customer != null ? this.customer : new Customer();
		
		this.customer.setReservation(this);
    	
        // Check if the customer already has a reservation        
        boolean hasReservation = AllData.reservationList.stream()
        	    .anyMatch(reservation -> {
        	        // Check if reservation is not null and the customer ID matches
        	        return reservation != null && reservation.getCustomer() != null && reservation.getCustomer().getCustomerId() == this.customer.getCustomerId();
        	    });
        
        if (hasReservation) {
            return "Reservation was not created, You can only have one reservation at a time";
        }
        
        
        // retrieve room
        Room room = this.getRooms().stream()
                .filter(r -> (r.getRoomId() == roomId) && (r.getIsAvailable())).findFirst()
                        .orElse(null);
        
        if (room != null) {
        	
        	// assign the reservation property of the Room class
        	room.setReservation(this);
        	
            // Set isAvailable attribute to false
            room.setIsAvailable(false);
            
            // Remove room from the rooms list, since no more available
            this.getRooms().remove(room);
            
    		// assign the invoice property of this class, if not assigned
            this.invoice = (this.invoice != null ? this.invoice : new Invoice());
           
    		// assign the reservation property of the Invoice class
            this.invoice.setReservation(this);
            
            // generate the invoice for this reservation
            int invoiceId = this.invoice.generateInvoice(room.getRoomPrice());
            
            // Establish association between reservation and check-in management
            this.checkinManagement = (this.checkinManagement != null ? this.checkinManagement : new CheckinManagement());
            
            // add reservation to check-in Management association list
            this.checkinManagement.setReservation(this);
            
            // Establish association between reservation and general manager
            this.generalManager = (this.generalManager != null ? this.generalManager : new GeneralManager());
            
            // add reservation to general manager association list
            this.generalManager.getReservations().add(this);
            
            // Add the reservation to reservationList
            this.addToHistory(this);

            return String.format("Reservation created successfully:%n Room Number: %s%n Invoice ID: %d%n Price: $%.2f CAD", 
            		room.getRoomNumber(), invoiceId, this.invoice.getPrice());
        } else {
        	
            return "Reservation was not created, Room not found";
        }
    }
    
    
    public void addToHistory(Reservation reservation) {
        // Add the reservation to reservationList
        AllData.reservationList.add(reservation);
        
        return;
    }
    
}
