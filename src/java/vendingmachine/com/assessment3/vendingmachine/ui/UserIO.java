package com.assessment3.vendingmachine.ui;

import java.math.BigDecimal;

public interface UserIO {
	/**
	 * Method that displays a given message to the user.
	 *
	 * @param message - The message to be displayed.
	 */
	void printMessage(String message);

	void printFormattedMessage(String string1, String string2, String string3, String string4);

	/**
	 * Method that repeatedly prompts a user to enter a number until reaching a valid input.
	 *
	 * @return The valid user input as a BigInteger object.
	 */
	BigDecimal readBigDecimal(String message);

	int getItemChoice(String message, int minChoice, int maxChoice);

	boolean getExit(String message);
}
