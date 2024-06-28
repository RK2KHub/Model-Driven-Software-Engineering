package com.hotel.jupiter.model;

import com.hotel.jupiter.AllData;

public class Invoice {

	private Reservation reservation;
	private double price;
	private boolean paid;
	private int invoiceId;
	
	public Invoice () {
		this.price = 0;
		this.paid = false;
		this.invoiceId = AllData.reservationList.size() + 1;
	}

	/*******Setters and Getters*********/
	public Reservation getReservation() {
		return this.reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean getPaid() {
		return this.paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	
	public int getInvoceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	/************************************/

	
	public int generateInvoice(double roomPrice) {
		// assign the reservation property of this class, if not assigned
        this.reservation = (this.reservation != null ? this.reservation : new Reservation());
       
		// assign the invoice property of the Reservation class
        this.reservation.setInvoice(this);
        
        // assign the price to the invoice
        this.price = roomPrice;
        
        return this.invoiceId;
	}

}