package com.hotel.jupiter.model;

public class Employee extends Account {

	protected int empID;
	protected String role;
	protected HotelPropreitary hotelPropreitary;

	public int getEmpID() {
		return this.empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public HotelPropreitary getHotelPropreitary() {
		return this.hotelPropreitary;
	}

	public void setHotelPropreitary(HotelPropreitary hotelPropreitary) {
		this.hotelPropreitary = hotelPropreitary;
	}

}