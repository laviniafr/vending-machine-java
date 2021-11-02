package com.assessment3.vendingmachine.service;

import com.assessment3.vendingmachine.dao.AuditDAO;
import com.assessment3.vendingmachine.dao.InventoryDAO;
import com.assessment3.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class InventoryServiceLayerImpl implements InventoryServiceLayer {

	InventoryDAO inventoryDAO;
	AuditDAO auditDAO;

	public InventoryServiceLayerImpl(InventoryDAO dao, AuditDAO auditDAO) {
		this.inventoryDAO = dao;
		this.auditDAO = auditDAO;
	}

	@Override
	public List<Item> getItems(boolean display) throws InventoryPersistenceException {
		if (display) {
			this.auditDAO.updateAudit("User viewed item list.");
		}
		return inventoryDAO.getItems(display);
	}

	@Override
	public Item getItem(int ID) throws InventoryPersistenceException {
		return inventoryDAO.getItem(ID);
	}

	@Override
	public void buyItem(int ID, int quantityToBuy, BigDecimal funds) throws InsufficentFundsException, NoItemInventoryException, InventoryPersistenceException {
		if(this.getItem(ID).getQuantity()>0) {
			this.auditDAO.updateAudit("User bought an item.");
		}
		else {
			this.auditDAO.updateAudit("User attempted to buy an item but failed.");
		}
		inventoryDAO.buyItem(ID, quantityToBuy, funds);

	}
}
