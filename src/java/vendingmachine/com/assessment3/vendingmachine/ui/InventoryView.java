package com.assessment3.vendingmachine.ui;

import com.assessment3.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class InventoryView {
	private final UserIO userIO;

	public InventoryView(UserIO userIO) {
		this.userIO = userIO;
	}

	public BigDecimal getWallet() {
		return userIO.readBigDecimal("Please enter a sum of money you'd like to spend, making sure it's a whole number.");
	}

	public void displaySum(BigDecimal sum) {
		userIO.printMessage("Here is your current sum." + sum);
	}

	public int getUserChoice(int maxItems) {
		return userIO.getItemChoice("Please choose a number corresponding to an item from the list.", 0, maxItems);
	}

	public void displayItems(List<Item> items) {
		userIO.printMessage("Here are the available items. Please select the number of the item you would like to buy, or type 0 to quit the application.");
		userIO.printMessage("No. \t Name \t Cost \t Items in stock");
		for (Item item : items) {
			userIO.printMessage(item.toString());
		}
	}

	public void displayWelcomeMessage() {
		userIO.printMessage("=== Welcome! ===");
	}

	public void displayGoodbyeMessage() {
		userIO.printMessage("=== Thank you for your input. Goodbye! ===");
	}

	public boolean runningState() {
		return userIO.getExit("Please type continue to proceed, or exit to quit the application.");
	}

	public void displayErrorMessage(Exception e) {
		userIO.printMessage(e.getMessage());
	}

}




