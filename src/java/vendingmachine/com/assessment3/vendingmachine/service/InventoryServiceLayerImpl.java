package com.assessment3.vendingmachine.service;

import com.assessment3.vendingmachine.dao.InventoryDAO;
import com.assessment3.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class InventoryServiceLayerImpl implements InventoryServiceLayer {

	InventoryDAO inventoryDAO;

	public InventoryServiceLayerImpl(InventoryDAO dao) {
		this.inventoryDAO = dao;
	}

	@Override
	public List<Item> getItems() throws InventoryPersistenceException {
		return inventoryDAO.getItems(true);
	}

	@Override
	public Item getItem(int ID) throws InventoryPersistenceException {
		return inventoryDAO.getItem(ID);
	}

	@Override
	public void buyItem(int ID, int quantityToBuy, BigDecimal funds) throws InsufficentFundsException, NoItemInventoryException, InventoryPersistenceException {
		inventoryDAO.buyItem(ID, quantityToBuy, funds);
	}
}
