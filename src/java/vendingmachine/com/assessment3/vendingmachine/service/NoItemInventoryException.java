package com.assessment3.vendingmachine.service;

public class NoItemInventoryException extends Exception {

	public NoItemInventoryException(String message) {
		super(message);
	}

	public NoItemInventoryException(String messgae, Throwable cause) {
		super(messgae, cause);
	}

}
