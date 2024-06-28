package com.hotel.jupiter.model;

public class Payment {

	private CheckinManagement checkinManagement;
	private String paymentMethod;
	private boolean paymentStatus;
	private String paymentMessage;
	private int paymentAmount;

	/***************Getters and setters*********************/
	public CheckinManagement getCheckinManagement() {
		return this.checkinManagement;
	}

	public void setCheckinManagement(CheckinManagement checkinManagement) {
		this.checkinManagement = checkinManagement;
	}
	
	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public boolean getPaymentStatus() {
		return this.paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	public String getPaymentMessage() {
		return this.paymentMessage;
	}

	public void setPaymentMessage(String paymentMessage) {
		this.paymentMessage = paymentMessage;
	}
	
	public int getPaymentAmount() {
		return this.paymentAmount;
	}

	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	/**************************************************************/

	
	public void processPaymentReport() {
		// Establish the association between check-in management and payment
		this.checkinManagement = this.getCheckinManagement() != null ? this.getCheckinManagement() : new CheckinManagement();
		
		this.checkinManagement.setPayment(this);
		
		// retrieve the generated bill for the reservation from the check-in management
		double generatedBill = this.checkinManagement.generateBill();
		
		if(this.paymentAmount >= generatedBill) {
			// set the payment status to true
			this.setPaymentStatus(true);
			
			// set the paid property of invoice to true
			this.checkinManagement.getReservation().getInvoice().setPaid(true);
		}
		
		return;
	}

}