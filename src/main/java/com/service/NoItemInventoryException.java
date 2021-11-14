package com.service;

/**
 * The No Item Inventory Exception.
 */
public class NoItemInventoryException extends Exception {

	/**
	 * Instantiates a new No item inventory exception.
	 *
	 * @param message the message
	 */
	public NoItemInventoryException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new No item inventory exception.
	 *
	 * @param messgae the messgae
	 * @param cause   the cause
	 */
	public NoItemInventoryException(String messgae, Throwable cause) {
		super(messgae, cause);
	}

}
