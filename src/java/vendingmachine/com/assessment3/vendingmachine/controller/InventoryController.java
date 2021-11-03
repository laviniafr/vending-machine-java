package com.assessment3.vendingmachine.controller;

import com.assessment3.vendingmachine.dto.Change;
import com.assessment3.vendingmachine.dto.Item;
import com.assessment3.vendingmachine.service.*;
import com.assessment3.vendingmachine.ui.InventoryView;

import java.math.BigDecimal;
import java.util.List;

/**
 * The Controller component of the MVC design pattern.
 */
public class InventoryController {

	/*
		The View object linked to the controller.
	 */
	private final InventoryView inventoryView;
	/*
		The Service Layer object linked to the controller.
	 */
	private final InventoryServiceLayer inventoryService;
	/*
		 Object responsible with giving the right amount of change in coins.
	 */
	private final Change change;

	/**
	 * Instantiates a new Inventory controller.
	 *
	 * @param inventoryService - The inventory service.
	 * @param inventoryView    - The inventory view.
	 */
	public InventoryController(InventoryServiceLayer inventoryService, InventoryView inventoryView) {
		this.inventoryService = inventoryService;
		this.inventoryView = inventoryView;
		this.change = new Change();
	}

	/**
	 * Method that runs the application.
	 *
	 * @throws InventoryPersistenceException - The inventory persistence exception.
	 */
	public void run() throws InventoryPersistenceException {
		/*
			Display a welcome message to the user.
		 */
		inventoryView.displayWelcomeMessage();
		/*
			Display the list of available items to the user.
		 */
		this.listItems();
		/*
			Prompt the user to choose whether to continue or exit the app.
		 */
		if (inventoryView.runningState()) {
			/*
				Store the user's input amount of money as a BigDecimal.
			 */
			BigDecimal sum = inventoryView.getWallet();
			/*
				Loop through the program until the user exits.
			 */
			while (true) {
				try {
					/*
						Display the sum the user has left.
					 */
					inventoryView.displaySum(sum);
					/*
						Prompt the user to choose a number and store it in an integer variable.
					 */
					int userChoice = inventoryView.getUserChoice(this.inventoryService.getItems(false).size());
					/*
						If the user picked 0, the application terminates.
					 */
					if (userChoice == 0) {
						break;
					} else {
						/*
							The inventory service layer is called for the user to purchase the chosen item. Exceptions
							are thrown if the item isn't available, if the funds are insufficient or if the item is not
							in the list.
						 */
						inventoryService.buyItem(userChoice, 1, sum);
						/*
							If successful, the user is shown the item they purchased.
						 */
						inventoryView.displayBoughtItem(inventoryService.getItem(userChoice).getName());
						/*
							The user is then given the change.
						 */
						sum = sum.subtract(inventoryService.getItem(userChoice).getCost());
						inventoryView.displayCoins(this.change.getChange(sum));
						this.listItems();
					}
				} catch (NoItemInventoryException | InsufficientFundsException | InventoryPersistenceException e) {
					inventoryView.displayErrorMessage(e);
				}
			}
		}
		/*
			The user is shown a goodbye message when the application is finished.
		 */
		inventoryView.displayGoodbyeMessage();

	}

	/**
	 * Method that gets the available item list and displays it to the user.
	 * @throws InventoryPersistenceException - The Inventory Persistence Exception.
	 */
	private void listItems() throws InventoryPersistenceException {
		List<Item> items = inventoryService.getItems(true);
		inventoryView.displayItems(items);
	}

}
