package com.assessment3.vendingmachine.service;


/**
 * The Insufficient Funds Exception.
 */
public class InsufficientFundsException extends Exception {

	/**
	 * Instantiates a new Insufficient funds exception.
	 *
	 * @param message the message
	 */
	public InsufficientFundsException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new Insufficient funds exception.
	 *
	 * @param messgae the messgae
	 * @param cause   the cause
	 */
	public InsufficientFundsException(String messgae, Throwable cause) {
		super(messgae, cause);
	}
}
