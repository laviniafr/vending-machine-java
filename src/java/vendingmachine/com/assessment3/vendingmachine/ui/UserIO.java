package com.assessment3.vendingmachine.ui;

import java.math.BigDecimal;

/**
 * The UserIO interface.
 */
public interface UserIO {
	/**
	 * Method that displays a given message to the user.
	 *
	 * @param message - The message to be displayed.
	 */
	void printMessage(String message);

	/**
	 * Method that displays a formatted message.
	 *
	 * @param string1 - The first string.
	 * @param string2 - The second string.
	 * @param string3 - The third string.
	 * @param string4 - The fourth string.
	 */
	void printFormattedMessage(String string1, String string2, String string3, String string4);

	/**
	 * Method that repeatedly prompts a user to enter a number until reaching a valid input.
	 *
	 * @param message - The message to be displayed to the user.
	 * @return The valid user input as a BigInteger object.
	 */
	BigDecimal readBigDecimal(String message);


	/**
	 * Method to get a user's item ofchoice.
	 *
	 * @param message   - The message to be displayed to the user.
	 * @param minChoice - The min choice.
	 * @param maxChoice - The max choice.
	 * @return - The item choice.
	 */
	int getItemChoice(String message, int minChoice, int maxChoice);

	/**
	 * Method that determines if to exit the application.
	 * @param message - The message to be displayed to the user.
	 * @return - A boolean variable indicating whether to exit the application.
	 */
	boolean getExit(String message);
}
