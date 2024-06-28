package com.hotel.jupiter.model;

import com.hotel.jupiter.AllData;
import java.util.List;

public class ReservationReport {
	
	private Reservation reservation;
	private long reportCreationDate;

	public ReservationReport() {
		this.reportCreationDate = System.currentTimeMillis();
	}
	
	/***********Getters and Setters************************/
	public Reservation getReservation() {
		return this.reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
	public long getReportCreationDate() {
		return this.reportCreationDate;
	}
	
	public void setReportCreationDate(long reportCreationDate) {
		this.reportCreationDate = reportCreationDate;
	}
	/********************************************************/

	
	public List<Reservation> getReservationReport() {
		// Establish association between reservation and check-in management
		this.reservation = this.reservation != null ? this.reservation : new Reservation();
		
		this.reservation.setReservationReport(this);
		
		if(this.reservation.getGeneralManager() != null && this.reservation.getGeneralManager().getRole() == "General_Manager") {
			
			return AllData.reservationList;
		}
		
		return null;
	}

}