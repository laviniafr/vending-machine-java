package com.assessment3.vendingmachine.ui;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {

	Scanner scanner = new Scanner(System.in);

	/**
	 * Method that displays a given message to the user.
	 *
	 * @param message - The message to be displayed.
	 */
	@Override
	public void printMessage(String message) {
		System.out.println(message);
	}

	@Override
	public void printFormattedMessage(String string1, String string2, String string3, String string4) {
		System.out.format("%4s%16s%8s%8s",string1,string2,string3,string4+"\n");
	}

	/**
	 * Method that repeatedly prompts a user to enter a number until reaching a valid input.
	 *
	 * @return The valid user input as a BigInteger object.
	 */
	@Override
	public BigDecimal readBigDecimal(String message) {
		this.printMessage(message);
		boolean isValid = false;
		BigDecimal value = null;
		String input;
		while (!isValid) {
			try {
				input = scanner.nextLine();
				value = new BigDecimal(input);
				isValid = true;
			} catch (NumberFormatException e) {
				this.printMessage("=== Not a valid number. ===");
				this.printMessage(message);
			}
		}
		return value;
	}

	/**
	 * Method that displays a message to the user and prompts the user to enter an integer, looping until the
	 * value entered is valid.
	 *
	 * @param message   - The message to display to the user.
	 * @param minChoice - The minimum valid integer value.
	 * @param maxChoice - The maximum valid integer value.
	 * @return - The valid integer value.
	 */
	@Override
	public int getItemChoice(String message, int minChoice, int maxChoice) {
		this.printMessage(message);
		boolean isValid = false;
		int userChoice = 0;
		String input = scanner.nextLine();
		while (!isValid) {
			try {
				userChoice = Integer.parseInt(input);
				if (userChoice < minChoice || userChoice > maxChoice) {
					throw new IllegalArgumentException();
				} else {
					isValid = true;
				}
			} catch (IllegalArgumentException e) {
				this.printMessage("=== Please choose a number from the list. ===");
				input = scanner.nextLine();
			}
		}
		return userChoice;
	}

	/**
	 * Method that displays a message to the user and determines whether the user wants to continue or exit the
	 * application, looping until one of the two available options is chosen.
	 *
	 * @param message - The message to be displayed
	 * @return - A boolean variable returning true if the user wants to continue the application, and false otherwise.
	 */
	@Override
	public boolean getExit(String message) {
		this.printMessage(message);
		boolean isValid = false;
		boolean proceed = false;
		String userChoice = scanner.nextLine().toLowerCase();
		while (!isValid) {
			if (userChoice.equals("exit")) {
				isValid = true;
			} else if (userChoice.equals("continue")) {
				proceed = true;
				isValid = true;
			} else {
				this.printMessage("=== Not a valid choice. ===");
				this.printMessage(message);
				userChoice = scanner.nextLine().toLowerCase();
			}
		}
		return proceed;
	}

}
