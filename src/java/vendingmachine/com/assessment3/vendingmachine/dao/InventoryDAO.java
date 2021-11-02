package com.assessment3.vendingmachine.dao;

import com.assessment3.vendingmachine.dto.Item;
import com.assessment3.vendingmachine.service.InsufficentFundsException;
import com.assessment3.vendingmachine.service.InventoryPersistenceException;
import com.assessment3.vendingmachine.service.NoItemInventoryException;

import java.math.BigDecimal;
import java.util.List;

public interface InventoryDAO {

//	com.vendingmachine.dto.Item addItem(String name, BigInteger cost, int quantity);
	List<Item> getItems() throws InventoryPersistenceException;
	Item getItem(int ID) throws InventoryPersistenceException;
	void buyItem(int ID, int quantity, BigDecimal funds) throws InventoryPersistenceException, NoItemInventoryException, InsufficentFundsException;
//	void updateInventory(List<com.vendingmachine.dto.Item> items);
}
