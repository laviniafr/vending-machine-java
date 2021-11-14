package com.dao;

import com.dto.Item;
import com.service.InsufficientFundsException;
import com.service.InventoryPersistenceException;
import com.service.NoItemInventoryException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Stub Implementation of the InventoryDAO Interface to be used for testing the Service Layer.
 */
public class InventoryDAOStubImpl implements InventoryDAO {

	/*
		The one and only item of the stub implementation.
	 */
	Item singleItem;

	/**
	 * Constructor to initialise the item.
	 */
	public InventoryDAOStubImpl() {
		this.singleItem = new Item();
		singleItem.setName("Crisps");
		singleItem.setID(1);
		singleItem.setQuantity(3);
		singleItem.setCost(new BigDecimal("4.5"));
	}

	public InventoryDAOStubImpl(Item testItem) {
		this.singleItem = testItem;
	}

	/**
	 * Method that creates a list of type Item and adds the local item to it.
	 * @param display - Parameter that decides whether the method is used for displaying the items to the user or
	 *                for other purposes.
	 * @return The Item list.
	 * @throws InventoryPersistenceException
	 */
	@Override
	public List<Item> getItems(boolean display) throws InventoryPersistenceException {
		List<Item> itemList = new ArrayList<>();
		itemList.add(singleItem);
		return itemList;
	}

	/**
	 *	Method that returns the local Item object.
	 *
	 * @param ID - The item ID.
	 * @return The item.
	 * @throws InventoryPersistenceException
	 */
	@Override
	public Item getItem(int ID) throws InventoryPersistenceException {
		if (ID == singleItem.getID()) {
			return singleItem;
		} else {
			return null;
		}
	}

	/**
	 * Method to imitate the purchase of an item by decrementing the value of the quantity.
	 *
	 * @param ID       - The ID of the item to be bought.
	 * @param quantity - The amount of items to be bought.
	 * @param funds    - The available funds.
	 * @throws InventoryPersistenceException
	 * @throws NoItemInventoryException
	 * @throws InsufficientFundsException
	 */
	@Override
	public void buyItem(int ID, int quantity, BigDecimal funds) throws InventoryPersistenceException, NoItemInventoryException, InsufficientFundsException {
		singleItem.setQuantity(singleItem.getQuantity()-quantity);
	}
}
