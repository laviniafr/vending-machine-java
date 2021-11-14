package com.service;

import com.dao.AuditDAO;
import com.dao.InventoryDAO;
import com.dto.Item;

import java.math.BigDecimal;
import java.util.List;

/**
 * The implementation of the Inventory Service Layer interface.
 */
public class InventoryServiceLayerImpl implements InventoryServiceLayer {

	/**
	 * The InventoryDAO object.
	 */
	InventoryDAO inventoryDAO;
	/**
	 * The AuditDAO object.
	 */
	AuditDAO auditDAO;

	/**
	 * Instantiates a new Inventory Service Layer.
	 *
	 * @param dao      - The Inventory DAO.
	 * @param auditDAO - The Audit DAO.
	 */
	public InventoryServiceLayerImpl(InventoryDAO dao, AuditDAO auditDAO) {
		this.inventoryDAO = dao;
		this.auditDAO = auditDAO;
	}

	/**
	 * Method that uses the inventory DAO to get an item list, and records the call in the audit.
	 * @param display - The display parameter.
	 * @return - The Item list.
	 * @throws InventoryPersistenceException - The Inventory Persistence Exception.
	 */
	@Override
	public List<Item> getItems(boolean display) throws InventoryPersistenceException {
		if (display) {
			this.auditDAO.updateAudit("User viewed item list.");
		}
		return inventoryDAO.getItems(display);
	}

	/**
	 * Method that uses the inventory DAO to get an item.
	 * @param ID - The item ID.
	 * @return - The Item.
	 * @throws InventoryPersistenceException - The Inventory Persistence Exception
	 */
	@Override
	public Item getItem(int ID) throws InventoryPersistenceException {
		return inventoryDAO.getItem(ID);
	}

	/**
	 * Method that uses the inventory DAO to buy an item upon user request, and records their attempt on the audit.
	 * @param ID            the id
	 * @param quantityToBuy the quantity to buy
	 * @param funds         the funds
	 * @throws InsufficientFundsException
	 * @throws NoItemInventoryException
	 * @throws InventoryPersistenceException
	 */
	@Override
	public void buyItem(int ID, int quantityToBuy, BigDecimal funds) throws InsufficientFundsException, NoItemInventoryException, InventoryPersistenceException {
		if(this.getItem(ID).getQuantity()>0) {
			this.auditDAO.updateAudit("User bought an item.");
		}
		else {
			this.auditDAO.updateAudit("User attempted to buy an item but failed.");
		}
		inventoryDAO.buyItem(ID, quantityToBuy, funds);

	}
}
