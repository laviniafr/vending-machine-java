package com.dao;

import com.dto.Item;
import com.service.InsufficientFundsException;
import com.service.InventoryPersistenceException;
import com.service.NoItemInventoryException;

import java.math.BigDecimal;
import java.util.List;

/**
 * The interface InventoryDAO.
 */
public interface InventoryDAO {

	/**
	 * Method to return the list of available items.
	 *
	 * @param display - Parameter that decides whether the method is used for displaying the items to the user or
	 *                for other purposes.
	 * @return - The Item list.
	 * @throws InventoryPersistenceException - The inventory persistence exception.
	 */
	List<Item> getItems(boolean display) throws InventoryPersistenceException;

	/**
	 * Method that returns an item based on its ID.
	 *
	 * @param ID - The item ID.
	 * @return - The Item.
	 * @throws InventoryPersistenceException - The inventory persistence exception.
	 */
	Item getItem(int ID) throws InventoryPersistenceException;

	/**
	 * Method that buys a given number of items from the stock.
	 *
	 * @param ID       - The ID of the item to be bought.
	 * @param quantity - The amount of items to be bought.
	 * @param funds    - The available funds.
	 * @throws InventoryPersistenceException - The inventory persistence exception.
	 * @throws NoItemInventoryException      - The no item inventory exception.
	 * @throws InsufficientFundsException    - The insufficient funds exception.
	 */
	void buyItem(int ID, int quantity, BigDecimal funds) throws InventoryPersistenceException, NoItemInventoryException, InsufficientFundsException;
}
