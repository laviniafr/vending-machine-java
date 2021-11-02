package com.assessment3.vendingmachine.ui;

import com.assessment3.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InventoryView {
	private final UserIO userIO;

	public InventoryView(UserIO userIO) {
		this.userIO = userIO;
	}

	public BigDecimal getWallet() {
		return userIO.readBigDecimal("=== Please enter a sum of money you'd like to spend. ===");
	}

	public void displaySum(BigDecimal sum) {
		userIO.printMessage("=== Here is your current sum. ===\n $" + sum);
	}

	public int getUserChoice(int maxItems) {
		return userIO.getItemChoice("=== Please choose a number corresponding to an item from the list. ===", 0, maxItems);
	}

	public void displayItems(List<Item> items) {
		userIO.printMessage("=== Here are the available items. ===\n" +
				"=== Please select the number of the item you would like to buy, or type 0 to quit the application. ===");
		userIO.printFormattedMessage("No.", "Name", "Cost", "Stock");
		for (Item item : items) {
			userIO.printFormattedMessage(String.valueOf(item.getID()), item.getName(), String.valueOf(item.getCost()), String.valueOf(item.getQuantity()));
		}
	}

	public void displayWelcomeMessage() {
		userIO.printMessage("=== Welcome! ===");
	}

	public void displayGoodbyeMessage() {
		userIO.printMessage("=== Thank you for your input. Goodbye! ===");
	}

	public boolean runningState() {
		return userIO.getExit("=== Please type continue to proceed, or exit to quit the application. ===");
	}

	public void displayErrorMessage(Exception e) {
		userIO.printMessage(e.getMessage());
	}

	public void displayCoins(Map<String, Integer> coinsList) {
		userIO.printMessage("=== Purchase Successful! Here is your change... ===");
		for (String coinName : coinsList.keySet()) {
			if (coinsList.get(coinName) > 0) {
				if (coinsList.get(coinName) > 1) {
					userIO.printMessage(coinsList.get(coinName) + " " + coinName.toLowerCase() + "s");
				} else {
					userIO.printMessage(coinsList.get(coinName) + " " + coinName.toLowerCase());
				}
			}
		}
	}

	public void displayBoughtItem(String itemName) {
		userIO.printMessage("=== Buying " + itemName +"... ===");
	}
}




