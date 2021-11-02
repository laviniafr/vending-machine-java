package com.assessment3.vendingmachine.service;

import com.assessment3.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface InventoryServiceLayer {

	List<Item> getItems() throws InventoryPersistenceException;
	Item getItem(int ID) throws InventoryPersistenceException;
	void buyItem(int ID, int quantityToBuy, BigDecimal funds) throws InsufficentFundsException, NoItemInventoryException, InventoryPersistenceException;

}
