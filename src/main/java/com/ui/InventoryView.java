package com.ui;

import com.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * The View component corresponding to the MVC design pattern.
 */
public class InventoryView {
	private final UserIO userIO;

	/**
	 * Instantiates a new Inventory view.
	 *
	 * @param userIO the user io
	 */
	public InventoryView(UserIO userIO) {
		this.userIO = userIO;
	}

	/**
	 * Gets the user's input amount of money.
	 *
	 * @return The wallet as a BigDecimal object.
	 */
	public BigDecimal getWallet() {
		return userIO.readBigDecimal("=== Please enter a sum of money you'd like to spend. ===");
	}

	/**
	 * Displays their remaining sum to the user.
	 *
	 * @param sum - The user's sum.
	 */
	public void displaySum(BigDecimal sum) {
		userIO.printMessage("=== Here is your current sum. ===\n $" + sum);
	}

	/**
	 * Displays a message and then gets the user's choice.
	 *
	 * @param maxItems - The max number of items.
	 * @return The user's choice.
	 */
	public int getUserChoice(int maxItems) {
		return userIO.getItemChoice("=== Please choose a number corresponding to an item from the list. ===", 0, maxItems);
	}

	/**
	 * Display the available items to the user.
	 *
	 * @param items - The item list.
	 */
	public void displayItems(List<Item> items) {
		userIO.printMessage("=== Here are the available items. ===\n" +
				"=== Please select the number of the item you would like to buy, or type 0 to quit the application. ===");
		userIO.printFormattedMessage("No.", "Name", "Cost", "Stock");
		for (Item item : items) {
			userIO.printFormattedMessage(String.valueOf(item.getID()), item.getName(), String.valueOf(item.getCost()), String.valueOf(item.getQuantity()));
		}
	}

	/**
	 * Display welcome message.
	 */
	public void displayWelcomeMessage() {
		userIO.printMessage("=== Welcome! ===");
	}

	/**
	 * Display goodbye message.
	 */
	public void displayGoodbyeMessage() {
		userIO.printMessage("=== Thank you for your input. Goodbye! ===");
	}

	/**
	 * Method to either continue or exit the application based on the user's choice.
	 *
	 * @return The user's choice as a boolean variable.
	 */
	public boolean runningState() {
		return userIO.getExit("=== Please type continue to proceed, or exit to quit the application. ===");
	}

	/**
	 * Display error message.
	 *
	 * @param e the e
	 */
	public void displayErrorMessage(Exception e) {
		userIO.printMessage(e.getMessage());
	}

	/**
	 * Display the user's change in coins.
	 *
	 * @param coinsList - The coins list.
	 */
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

	/**
	 * Display bought item.
	 *
	 * @param itemName - The item name.
	 */
	public void displayBoughtItem(String itemName) {
		userIO.printMessage("=== Buying " + itemName +"... ===");
	}
}




