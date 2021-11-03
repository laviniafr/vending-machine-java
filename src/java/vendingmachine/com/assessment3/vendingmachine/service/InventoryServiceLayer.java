package com.assessment3.vendingmachine.service;

import com.assessment3.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

/**
 * The interface Inventory Service Layer.
 */
public interface InventoryServiceLayer {

	/**
	 * Gets items.
	 *
	 * @param display the display
	 * @return the items
	 * @throws InventoryPersistenceException the inventory persistence exception
	 */
	List<Item> getItems(boolean display) throws InventoryPersistenceException;

	/**
	 * Gets item.
	 *
	 * @param ID the id
	 * @return the item
	 * @throws InventoryPersistenceException the inventory persistence exception
	 */
	Item getItem(int ID) throws InventoryPersistenceException;

	/**
	 * Buy item.
	 *
	 * @param ID            the id
	 * @param quantityToBuy the quantity to buy
	 * @param funds         the funds
	 * @throws InsufficientFundsException    the insufficient funds exception
	 * @throws NoItemInventoryException      the no item inventory exception
	 * @throws InventoryPersistenceException the inventory persistence exception
	 */
	void buyItem(int ID, int quantityToBuy, BigDecimal funds) throws InsufficientFundsException, NoItemInventoryException, InventoryPersistenceException;

}
