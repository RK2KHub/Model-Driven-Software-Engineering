package com.hotel.jupiter.model;

public enum RoomState {
	
	AVAILABLE("AVAILABLE"), 
	RESERVED("RESERVED"),
	CHECKEDIN("CHECKEDIN"),
	CHECKEDOUT("CHECKEDOUT"),
	CANCELLED("CANCELLED");
	
	private final String currentValue;
	
	private RoomState(String value) {
		this.currentValue = value;
	}
	
	public String getCurrentValue() {
		return this.currentValue;
	}

}
