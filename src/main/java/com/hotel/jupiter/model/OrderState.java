package com.hotel.jupiter.model;

public enum OrderState {
	
	IDLE("IDLE"),
	ORDER_RECEIVED("ORDER RECEIVED"),
	ORDER_PREPARED("ORDER PREPARED"),
	ORDER_CANCELLED("ORDER CANCELLED"),
	ORDER_DELIVERED("ORDER DELIVERED");

	private final String currentValue;
	
	private OrderState(String value) {
		this.currentValue = value;
	}
	
	public String getCurrentValue() {
		return this.currentValue;
	}
}
