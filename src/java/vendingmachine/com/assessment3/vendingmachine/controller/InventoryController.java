package com.assessment3.vendingmachine.controller;

import com.assessment3.vendingmachine.dto.Item;
import com.assessment3.vendingmachine.service.*;
import com.assessment3.vendingmachine.ui.InventoryView;

import java.math.BigDecimal;
import java.util.List;

public class InventoryController {

	private final InventoryView inventoryView;
	private final InventoryServiceLayer inventoryService;

	public InventoryController(InventoryServiceLayer inventoryService, InventoryView inventoryView){
		this.inventoryService = inventoryService;
		this.inventoryView = inventoryView;
	}

	public void run() throws InventoryPersistenceException, NoItemInventoryException, InsufficentFundsException {
		inventoryView.displayWelcomeMessage();
		this.listItems();
		while(inventoryView.runningState()){
			BigDecimal sum = inventoryView.getWallet();
			inventoryView.displaySum(sum);
			try {
				int userChoice = inventoryView.getUserChoice(this.inventoryService.getItems().size());
				if (userChoice == 0) {
					break;
				} else {
					inventoryService.buyItem(userChoice, 1, sum);
				}
			}catch (NoItemInventoryException | InsufficentFundsException e){
				inventoryView.displayErrorMessage(e);
			}
		}
		inventoryView.displayGoodbyeMessage();

	}

	private void listItems() throws InventoryPersistenceException {
		List<Item> items = inventoryService.getItems();
		inventoryView.displayItems(items);
	}



}
