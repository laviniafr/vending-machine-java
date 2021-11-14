package com.dto;

import java.math.BigDecimal;

/**
 * The enum Coins.
 */
public enum Coins {
	/**
	 * Quarter coins.
	 */
	QUARTER(25),
	/**
	 * Dime coins.
	 */
	DIME(10),
	/**
	 * Nickel coins.
	 */
	NICKEL(5),
	/**
	 * Penny coins.
	 */
	PENNY(1);


	private final int coinValue;

	Coins(int val) {
		this.coinValue = val;
	}

	/**
	 * Gets the coin value as a BigDecimal object.
	 *
	 * @return the val
	 */
	public BigDecimal getVal() {
		return new BigDecimal(coinValue);
	}
}
