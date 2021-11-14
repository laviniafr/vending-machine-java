package com.dto;

import java.math.BigDecimal;

import java.util.*;

/**
 * The type Change.
 */
public class Change {

	/*
		Map object that maps a corresponding coin value as a BigDecimal along with its String description.
	 */
	private final Map<BigDecimal, String> coinsList;

	/**
	 * Instantiates a new Change object.
	 */
	public Change() {
		this.coinsList = new LinkedHashMap<>();
		/*
			The enum values from Coins are added to a map.
		 */
		for (Coins coin : Coins.values()) {
			coinsList.put(coin.getVal(), coin.toString());
		}
	}

	/**
	 * Method to get the decimal point of a given number.
	 * @param number - The number.
	 * @return - The decimal point as a BigDecimal object.
	 */
	private BigDecimal getDecimalPoint(BigDecimal number) {
		return number.subtract(new BigDecimal(number.toBigInteger().toString()));
	}

	/**
	 * Method to return change as coins from a given sum.
	 *
	 * @param sum The sum to return change as coins from.
	 * @return A map object mapping the respective coin name string value to the amount of coins of that type.
	 */
	public Map<String, Integer> getChange(BigDecimal sum) {
		Map<String, Integer> listOfCoins = new LinkedHashMap<>();
		BigDecimal decimalPoint = getDecimalPoint(sum);
		listOfCoins.put("DOLLAR", (int) Double.parseDouble(sum.subtract(decimalPoint).toString()));

		decimalPoint = getDecimalPoint(sum).multiply(BigDecimal.valueOf(100));
		int count;
		for (BigDecimal coinValue : this.coinsList.keySet()) {
			count = 0;
			while (decimalPoint.subtract(coinValue).compareTo(BigDecimal.valueOf(0)) >= 0) {
				decimalPoint = decimalPoint.subtract(coinValue);
				listOfCoins.put(coinsList.get(coinValue), ++count);
			}
		}
		return listOfCoins;
	}

}
