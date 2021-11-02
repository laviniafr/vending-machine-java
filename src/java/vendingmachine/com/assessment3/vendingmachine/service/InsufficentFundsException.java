package com.assessment3.vendingmachine.service;

public class InsufficentFundsException extends Exception {

	public InsufficentFundsException(String message) {
		super(message);
	}

	public InsufficentFundsException(String messgae, Throwable cause) {
		super(messgae, cause);
	}
}
