package com.assessment3.vendingmachine.dao;

import com.assessment3.vendingmachine.dto.Item;
import com.assessment3.vendingmachine.service.InventoryPersistenceException;

import java.math.BigDecimal;
import java.util.List;

public interface InventoryDAO {

//	com.vendingmachine.dto.Item addItem(String name, BigInteger cost, int quantity);
	List<Item> getItems() throws InventoryPersistenceException;
	Item getItem(int ID) throws InventoryPersistenceException;
	void buyItem(int ID, int quantity, BigDecimal funds) throws InventoryPersistenceException;
//	void updateInventory(List<com.vendingmachine.dto.Item> items);
}
