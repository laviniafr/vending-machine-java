package com.assessment3.vendingmachine.controller;

import com.assessment3.vendingmachine.dao.InventoryDAOFileImpl;
import com.assessment3.vendingmachine.dao.InventoryDAO;
import com.assessment3.vendingmachine.dto.Item;
import com.assessment3.vendingmachine.service.InventoryPersistenceException;
import com.assessment3.vendingmachine.service.InventoryServiceLayer;
import com.assessment3.vendingmachine.service.InventoryServiceLayerImpl;
import com.assessment3.vendingmachine.ui.InventoryView;

import java.math.BigDecimal;
import java.util.List;

public class InventoryController {

	private InventoryView inventoryView;
	private InventoryServiceLayer inventoryService;

	public InventoryController(InventoryServiceLayer inventoryService, InventoryView inventoryView){
		this.inventoryService = inventoryService;
		this.inventoryView = inventoryView;
	}

	public void run() throws InventoryPersistenceException {
		inventoryView.displayWelcomeMessage();
		this.listItems();
		while(inventoryView.runningState()){
			BigDecimal sum = inventoryView.getWallet();
			int userChoice = inventoryView.getUserChoice(this.inventoryService.getItems().size());
			if (userChoice==0) {
				break;
			}
			else{

			}
		}
		inventoryView.displayGoodbyeMessage();

	}

	private void listItems() throws InventoryPersistenceException {
		List<Item> items = inventoryService.getItems();
		inventoryView.displayItems(items);
	}



}
