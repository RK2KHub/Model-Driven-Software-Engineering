package com.hotel.jupiter.model;

import java.util.List;
import java.util.Scanner;

import com.hotel.jupiter.AllData;


public class Customer extends Account {

	private Integer customerId;
	private List<OrderMenuItem> order;
	
	
	private Reservation reservation;
	
	
	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public List<OrderMenuItem> getOrder() {
		return order;
	}

	public void setOrder(List<OrderMenuItem> order) {
		this.order = order;
	}

	public void viewProfile(Integer id) {
		Customer c = AllData.customerList.get(id.intValue());
		System.out.println("Name	: " + c.getName());
		System.out.println("Email	: " + c.getEmail());
		System.out.println("Age	: " + c.getAge());
		System.out.println("Contact : " + c.getContact());

	}

	public void updateProfile(Integer customerId, Customer c) {
		Customer info = AllData.customerList.get(customerId.intValue());
		info.setAge(c.getAge());
		info.setContact(c.getContact());
		info.setName(c.getName());
		info.setEmail(c.getEmail());
		info.setCustomerId(customerId.intValue());
		AllData.customerList.remove(customerId.intValue());
		AllData.customerList.add(info);

	}

	public void viewOrders(List<OrderMenuItem> list) {
		int i;
		OrderMenuItem data;
		System.out.println("=============================");
		System.out.println("        Your Orders  ");
		System.out.println("=============================");

		for (i = 1; i <= list.size(); i++) {
			data = list.get(i - 1);
			System.out.println("|- " + i + ". " + data.getName());
			System.out.println("|- Qty: " + data.getQuantity());
			System.out.println("|");
		}

	}
	
	
	public void makeReservation() {
		// assign the reservation property of this class, if not assigned
		this.reservation = (this.reservation != null ? this.reservation : new Reservation());
		
		// assign the customer property of the Reservation class
		this.reservation.setCustomer(this);
		
		// get all the available rooms
		List<Room> rooms = this.reservation.getRooms();
		
		// display all available rooms
		System.out.println("=============================");
		System.out.println("        Choose A Room        ");
		System.out.println("=============================");
		
		for (int i = 1; i <= rooms.size(); i++) {
			Room room = rooms.get(i - 1);
			
			System.out.println("|-" + i);
			System.out.println("|- Title: " + room.getRoomTitle());
			System.out.println("|- Description: " + room.getRoomDescription());
			System.out.println("|- Occupancy: " + room.getRoomOccupancy());
			System.out.println("|- Price: " + room.getRoomPrice());
			System.out.println("|- Room Number: " + room.getRoomNumber());
			System.out.println("");
		}
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.println("|- Enter A Number Option:");
		System.out.println("");
		
		
		int userInput = 0;
		
		try {
		    userInput = Integer.parseInt(scanner.nextLine()); // Wait for user input
		    
		} catch (NumberFormatException e) {
			
		    System.out.println("|- Invalid input. Please enter a valid integer.");
		    System.out.println("");
		}

		

		while (true) {
			
		    // Check if userInput is a valid index in rooms list and if the selected room is available
		    if (userInput > 0 && userInput <= rooms.size() && rooms.get(userInput - 1).getIsAvailable()) {
		    	
		        break; // Valid input; exit loop
		    } else {
		    	
		        System.out.println("|- Invalid, Enter A Valid Number Option:");
		        System.out.println("");
				
				try {
				    userInput = Integer.parseInt(scanner.nextLine()); // Wait for user input
				    
				} catch (NumberFormatException e) {
					
				    System.out.println("Invalid input. Please enter a valid integer.");
				    System.out.println("");
				}
		    }
		}

		// Assuming createReservation takes a roomId and rooms is indexed the same way as room IDs
		String message = this.reservation.createReservation(rooms.get(userInput - 1).getRoomId());
		System.out.println("--------> " + message);
		System.out.println("");
		
		return;
	}
	
	
	public void cancelReservation() {
		// retrieve the reservation for this customer
		Reservation reservation = this.reservation;
		
		// set default message
		String message = "Unsuccessful, You don't have a reservation";
		
		// make sure the customer has a reservation to cancel
		if (reservation != null) {
		
			// change message
			message = "Unsuccessful, you need to check out instead";
			
			// Retrieve the CheckinManagement object from the reservation
			CheckinManagement checkinManagement = reservation.getCheckinManagement();

			// Check if it's null and establish association
			if (checkinManagement == null) {
			    checkinManagement = new CheckinManagement();
			    reservation.setCheckinManagement(checkinManagement);
			}
		
			checkinManagement.setReservation(reservation);
			
			if(!checkinManagement.getCheckIn()) {
				
				// change message
				message = "Unsuccessful, Reservation could not be cancelled";
				
				// check if reservation was successfully cancelled
				boolean isCancelled = reservation.getCheckinManagement().cancelBooking();
				
				if(isCancelled) {
					
					// set success message
					message = "Reservation cancelled successully";
				}
			}
		}
		
		// display message
		System.out.println("--------> " + message);
		System.out.println("");
		
		return;
	}
	
	
	public void makePayment() {
		// payment methods
		String[] paymentMethods = {"CHEQUE", "CREDIT CARD", "DEBIT CARD", "INTERAC TRANSFER"};
		
		// retrieve the reservation for this customer
		Reservation reservation = this.reservation;
		
		// set default message
		String message = "Unsuccessful, You don't have a reservation";
		
		// make sure the customer has a reservation before they can make payment
		if (reservation != null) {
			
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			
			// Retrieve the CheckinManagement object from the reservation
			CheckinManagement checkinManagement = reservation.getCheckinManagement();

			// Check if it's null and Establish association
			if (checkinManagement == null) {
			    checkinManagement = new CheckinManagement();
			    reservation.setCheckinManagement(checkinManagement);
			}
			
			checkinManagement.setReservation(reservation);
			
			// generate the bill for customer
			double generatedAmount = checkinManagement.generateBill();
			
			// display bill
			System.out.println("=============================");
			System.out.println("        Your Bill Is         ");
			System.out.println("=============================");
			System.out.println("");
			
			// print bill
			System.out.println("--------> " + generatedAmount + " CAD");
			
			// prompt customer to enter amount
			System.out.println("|- Enter Amount To Proceed:");
			System.out.println("");
			
			
			int userInput = 0;
			
			try {
			    userInput = Integer.parseInt(scanner.nextLine()); // Wait for user input
			    
			} catch (NumberFormatException e) {
				
			    System.out.println("|- Invalid input. Please enter a valid integer.");
			    System.out.println("");
			}
			
			
			while (true) {
				
			    // Check if userInput is < generated bill
			    if (userInput >= generatedAmount) {
			    	
			        break; // Valid input; exit loop
			    } else {
			    	
			        System.out.println("|- Invalid, Amount is less than your bill. Try again!!!:");
			        System.out.println("");
			        
					
					try {
					    userInput = Integer.parseInt(scanner.nextLine()); // Wait for user input
					    
					} catch (NumberFormatException e) {
						
					    System.out.println("|- Invalid input. Please enter a valid integer.");
					    System.out.println("");
					}
			    }
			}
			
			// get user amount
			int userAmount = userInput;
			
			// choose payment option
			System.out.println("================================");
			System.out.println("  Choose A Payment Method       ");
			System.out.println("================================");
			System.out.println("");
			
			
			for (int i = 0; i < paymentMethods.length; i++) {
			    System.out.println((i + 1) + ". " + paymentMethods[i]); // Display each payment method with an index
			}
			
			System.out.println("|- Enter A Number Option:");
			System.out.println("");
			
			try {
			    userInput = Integer.parseInt(scanner.nextLine()); // Wait for user input
			    
			} catch (NumberFormatException e) {
				
			    System.out.println("|- Invalid input. Please enter a valid integer.");
			    System.out.println("");
			}
			
			while (true) {
				
			    // Check if userInput is < generated bill
				if (userInput > 0 && userInput <= paymentMethods.length) {
			    	
			        break; // Valid input; exit loop
			    } else {
			    	
			        System.out.println("|- Invalid, Enter A Valid Number Option:");
			        System.out.println("");
			        
					
					try {
					    userInput = Integer.parseInt(scanner.nextLine()); // Wait for user input
					    
					} catch (NumberFormatException e) {
						
					    System.out.println("|- Invalid input. Please enter a valid integer.");
					    System.out.println("");
					}
			    }
			}
			
			String paymentMethod = paymentMethods[userInput - 1]; // Adjust userInput to match array index
			
			// establish association link from check-in management to payment
			Payment payment = reservation.getCheckinManagement().getPayment();

			// Check if it's null and set the association
			if (payment == null) {
				payment = new Payment();
				reservation.getCheckinManagement().setPayment(new Payment());
			}
			
			payment.setCheckinManagement(checkinManagement);
			
			payment.setPaymentMethod(paymentMethod);
			
			payment.setPaymentAmount(userAmount);
			
			payment.processPaymentReport();
			
			if(payment.getPaymentStatus()) {
				
				// set success message
				message = "Payment Successful";
			} else {
				
				// set failed message
				message = "Payment Not Successful";
			}
		}
		
		// display message
		System.out.println("--------> " + message);
		System.out.println("");
		
		return;
	}
	
	
	public void checkIn() {
		// retrieve the reservation for this customer
		Reservation reservation = this.reservation;
		
		// set default message
		String message = "Check-in not successful, You don't have a reservation";
		
		// make sure the customer has a reservation before they can make payment
		if (reservation != null) {
			
			// set failed message
			message = "Unable to check-in";
			
			// Retrieve the CheckinManagement object from the reservation
			CheckinManagement checkinManagement = reservation.getCheckinManagement();

			// Check if it's null and Establish association
			if (checkinManagement == null) {
			    checkinManagement = new CheckinManagement();
			    reservation.setCheckinManagement(checkinManagement);
			}
			
			checkinManagement.setReservation(reservation);
			
			checkinManagement.checkIn();
			
			if(checkinManagement.getCheckIn()) {
				
				// set success message
				message = "Successfully checked in";
			}
			
		}
		
		// display message
		System.out.println("--------> " + message);
		System.out.println("");
		
		return;
	}
	
	
	public void checkOut() {
		// retrieve the reservation for this customer
		Reservation reservation = this.reservation;
		
		// set default message
		String message = "Unsuccessful, You don't have a reservation";
		
		// make sure the customer has a reservation to cancel
		if (reservation != null) {
			
			// Retrieve the CheckinManagement object from the reservation
			CheckinManagement checkinManagement = reservation.getCheckinManagement();

			// Check if it's null and establish association
			if (checkinManagement == null) {
			    checkinManagement = new CheckinManagement();
			    reservation.setCheckinManagement(checkinManagement);
			}
			
			checkinManagement.setReservation(reservation);
			
			// change message
			message = "Unsuccessful, because you are not checked in. Try the cancel option";
			
			if(checkinManagement.getCheckIn()) {
		
				// change message
				message = "Unsuccessful, You need to pay your bills first";
				
				// check if customer has paid
				boolean isPaid = reservation.getInvoice().getPaid();
				
				if(isPaid) {
					
					// change message
					message = "Check-out was Unsuccessful";
					
					// check customer out
					reservation.getCheckinManagement().checkOut();
					
					// check if the check-out was successful
					if(checkinManagement.getCheckOut()) {
						
						// set success message
						message = "Successfully checked out";
					}
				}
			
			
			}
			
		}
		
		// display message
		System.out.println("--------> " + message);
		System.out.println("");
		
		return;
	}
	
	
	public void viewRooms()
	{
		Room r = new Room();
		r.viewRoom();

	}

}
