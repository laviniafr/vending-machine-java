package com.assessment3.vendingmachine.main;

import com.assessment3.vendingmachine.controller.InventoryController;
import com.assessment3.vendingmachine.dao.InventoryDAO;
import com.assessment3.vendingmachine.dao.InventoryDAOFileImpl;
import com.assessment3.vendingmachine.service.*;
import com.assessment3.vendingmachine.ui.InventoryView;
import com.assessment3.vendingmachine.ui.UserIO;
import com.assessment3.vendingmachine.ui.UserIOConsoleImpl;

public class Application {
	public static void main(String[] args) throws InventoryPersistenceException, NoItemInventoryException, InsufficentFundsException {
		UserIO userIO = new UserIOConsoleImpl();
		InventoryView inventoryView = new InventoryView(userIO);
		InventoryDAO inventoryDAO = new InventoryDAOFileImpl();
		InventoryServiceLayer inventoryServiceLayer = new InventoryServiceLayerImpl(inventoryDAO);
		InventoryController inventoryController = new InventoryController(inventoryServiceLayer, inventoryView);
		inventoryController.run();
	}
}
