package com.ensoa.order.flow;

public class CustomerNotFoundException extends Exception {
	public CustomerNotFoundException() {
		
	}
	public CustomerNotFoundException(String message) {
		super(message);
	}
}
