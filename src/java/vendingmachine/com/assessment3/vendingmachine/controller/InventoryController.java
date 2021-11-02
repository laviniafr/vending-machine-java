package com.assessment3.vendingmachine.controller;

import com.assessment3.vendingmachine.dto.Change;
import com.assessment3.vendingmachine.dto.Item;
import com.assessment3.vendingmachine.service.*;
import com.assessment3.vendingmachine.ui.InventoryView;

import java.math.BigDecimal;
import java.util.List;

public class InventoryController {

	private final InventoryView inventoryView;
	private final InventoryServiceLayer inventoryService;
	private final Change change;

	public InventoryController(InventoryServiceLayer inventoryService, InventoryView inventoryView) {
		this.inventoryService = inventoryService;
		this.inventoryView = inventoryView;
		this.change = new Change();
	}

	public void run() throws InventoryPersistenceException, NoItemInventoryException, InsufficentFundsException {
		inventoryView.displayWelcomeMessage();
		this.listItems();
		if (inventoryView.runningState()) {
			BigDecimal sum = inventoryView.getWallet();
			while (true) {
				try {
					inventoryView.displaySum(sum);
					int userChoice = inventoryView.getUserChoice(this.inventoryService.getItems().size());
					if (userChoice == 0) {
						break;
					} else {
						inventoryService.buyItem(userChoice, 1, sum);
						inventoryView.displayBoughtItem(inventoryService.getItem(userChoice).getName());
						sum = sum.subtract(inventoryService.getItem(userChoice).getCost());
						inventoryView.displayCoins(this.change.getChange(sum));
						this.listItems();
					}
				} catch (NoItemInventoryException | InsufficentFundsException |InventoryPersistenceException e) {
					inventoryView.displayErrorMessage(e);
				}
			}
		}
		inventoryView.displayGoodbyeMessage();

	}



	private void listItems() throws InventoryPersistenceException {
		List<Item> items = inventoryService.getItems();
		inventoryView.displayItems(items);
	}


}
