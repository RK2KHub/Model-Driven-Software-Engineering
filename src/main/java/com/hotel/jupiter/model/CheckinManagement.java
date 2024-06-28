package com.hotel.jupiter.model;

import com.hotel.jupiter.AllData;

public class CheckinManagement {

	private Payment payment;
	private boolean checkIn;
	private boolean checkOut;
	private Reservation reservation;
	
	public CheckinManagement() {
		this.checkIn = false;
		this.checkOut = false;
	}

/****************Getters and Setters*************************/
	public Payment getPayment() {
		return this.payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public boolean getCheckIn() {
		return this.checkIn;
	}

	public void setCheckIn(boolean checkIn) {
		this.checkIn = checkIn;
	}
	
	public boolean getCheckOut() {
		return this.checkOut;
	}

	public void setCheckOut(boolean checkOut) {
		this.checkOut = checkOut;
	}
	
	public Reservation getReservation() {
		return this.reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	/*********************************************************/

	
	public void checkIn() {
		// Establish association between reservation and check-in management
		this.reservation = this.reservation != null ? this.reservation : new Reservation();
		
		this.reservation.setCheckinManagement(this);
		
		// set check in status to true
		this.setCheckIn(true);
		
		// set check out status to false
		this.setCheckOut(false);
		
		// set max period to 0. Since checked-in, no need for max period validity check
		this.reservation.setMaxPeriod(0);
		
		return;
	}


	public void checkOut() {
		// Establish association between reservation and check-in management
		this.reservation = this.reservation != null ? this.reservation : new Reservation();
		
		this.reservation.setCheckinManagement(this);
        
    	// retrieve room    	
    	Room room = AllData.roomList.stream()
    		    .filter(r -> {
    		        // Check if reservation is not null and the reservation ID matches
    		        return r.getReservation() != null && r.getReservation().getReservationId() == this.reservation.getReservationId();
    		    })
    		    .findFirst()
    		    .orElse(null);
    	
    	// make the room available, since reservation is cancelled
    	room.setIsAvailable(true);
    	room.setState(RoomState.CHECKEDOUT.getCurrentValue());
    	// set the reservation property of the Room class to null
    	room.setReservation(null);
        
        // add room back to room list, since it is now available
        this.reservation.getRooms().add(room);
        
		// set check in status to true
		this.setCheckIn(false);
		
		// set check out status to false
		this.setCheckOut(true);
		
        // remove reservation from general manager association list since cancelled
        this.reservation.getGeneralManager().getReservations().remove(this.reservation);
        
		// remove association between customer and reservation, since cancelled
        this.reservation.getCustomer().setReservation(null);
        
		this.reservation.setCustomer(null);
		
		AllData.reservationList.remove(reservation);
        
        return;
	}


	public double generateBill() {
		// Establish association between reservation and check-in management
		this.reservation = this.reservation != null ? this.reservation : new Reservation();
		
		this.reservation.setCheckinManagement(this);
		
		long millisecondsPerDay = 24 * 60 * 60 * 1000; // Number of milliseconds in a day
		
		// get the reservation date
		long reservationDate = this.reservation.getReservationDate();
		
		// get current date
		long currentDate = System.currentTimeMillis();
		
		// number of days spent in reservation
		long numberOfDays = (long) Math.ceil((double) (currentDate - reservationDate) / millisecondsPerDay);
		
		// get the price for the room from the invoice
		double bill = this.reservation.getInvoice().getPrice() * numberOfDays;
		
		return bill;
	}


	public boolean cancelBooking() {
        
		// make sure reservation is not null
        if(this.reservation != null) {
        	
    		// Establish association between reservation and check-in management
    		this.reservation.setCheckinManagement(this);
            
        	// retrieve room    	
        	Room room = AllData.roomList.stream()
        		    .filter(r -> {
        		        // Check if reservation is not null and the reservation ID matches
        		        return r.getReservation() != null && r.getReservation().getReservationId() == this.reservation.getReservationId();
        		    })
        		    .findFirst()
        		    .orElse(null);
        	
        	// make the room available, since reservation is cancelled
        	room.setIsAvailable(true);
        	
        	// set the reservation property of the Room class to null
        	room.setReservation(null);
            
            // add room back to room list, since it is now available
            this.reservation.getRooms().add(room);
            
            // remove reservation from general manager association list since cancelled
            this.reservation.getGeneralManager().getReservations().remove(this.reservation);
            
    		// remove association between customer and reservation, since cancelled
            this.reservation.getCustomer().setReservation(null);
            
    		this.reservation.setCustomer(null);
    		
    		AllData.reservationList.remove(reservation);
            
            return true;
        }else {
        	
        	return false;
        }
    }

}