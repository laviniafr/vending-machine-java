package com.assessment3.vendingmachine.main;

import com.assessment3.vendingmachine.controller.InventoryController;
import com.assessment3.vendingmachine.dao.AuditDAO;
import com.assessment3.vendingmachine.dao.AuditDAOFileImpl;
import com.assessment3.vendingmachine.dao.InventoryDAO;
import com.assessment3.vendingmachine.dao.InventoryDAOFileImpl;
import com.assessment3.vendingmachine.service.*;
import com.assessment3.vendingmachine.ui.InventoryView;
import com.assessment3.vendingmachine.ui.UserIO;
import com.assessment3.vendingmachine.ui.UserIOConsoleImpl;

/**
 * The type Application.
 */
public class Application {
	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 * @throws InventoryPersistenceException - The inventory persistence exception.
	 * @throws NoItemInventoryException      - The no item inventory exception.
	 * @throws InsufficientFundsException    - The insufficient funds exception.
	 */
	public static void main(String[] args) throws InventoryPersistenceException, NoItemInventoryException, InsufficientFundsException {
		UserIO userIO = new UserIOConsoleImpl();
		InventoryView inventoryView = new InventoryView(userIO);
		InventoryDAO inventoryDAO = new InventoryDAOFileImpl();
		AuditDAO auditDAO = new AuditDAOFileImpl();
		InventoryServiceLayer inventoryServiceLayer = new InventoryServiceLayerImpl(inventoryDAO,auditDAO);
		InventoryController inventoryController = new InventoryController(inventoryServiceLayer, inventoryView);
		inventoryController.run();
	}
}
