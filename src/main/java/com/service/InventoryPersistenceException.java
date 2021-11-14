package com.service;


/**
 * The Inventory persistence Exception.
 */
public class InventoryPersistenceException extends Exception {

	/**
	 * Instantiates a new Inventory persistence exception.
	 *
	 * @param message the message
	 */
	public InventoryPersistenceException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new Inventory persistence exception.
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	public InventoryPersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

}
