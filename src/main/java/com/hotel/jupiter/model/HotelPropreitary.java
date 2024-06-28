package com.hotel.jupiter.model;

import java.util.*;

public class HotelPropreitary {

	public String hotelName = "Jupiter Hotels";
	public String hotelAddress = "Montreal";
	public String hotelDescription = "Best Hotel in Montreal";
	GeneralManager branchManager;
	ExecutiveChef executiveChef;
	Collection<Employee> employee;
	Collection<Room> room;
	Customer hotelPropreitary;

	public String getHotelName() {
		return this.hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelAddress() {
		return this.hotelAddress;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	public String getHotelDescription() {
		return this.hotelDescription;
	}

	public void setHotelDescription(String hotelDescription) {
		this.hotelDescription = hotelDescription;
	}

	public GeneralManager getBranchManager() {
		return this.branchManager;
	}

	public void setBranchManager(GeneralManager branchManager) {
		this.branchManager = branchManager;
	}

	public ExecutiveChef getExecutiveChef() {
		return this.executiveChef;
	}

	public void setExecutiveChef(ExecutiveChef executiveChef) {
		this.executiveChef = executiveChef;
	}

	public Collection<Employee> getEmployee() {
		return this.employee;
	}

	public void setEmployee(Collection<Employee> employee) {
		this.employee = employee;
	}

	public HotelPropreitary() {
		// TODO - implement HotelPropreitary.HotelPropreitary
		throw new UnsupportedOperationException();
	}

}