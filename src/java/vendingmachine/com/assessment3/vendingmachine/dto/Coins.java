package com.assessment3.vendingmachine.dto;

import java.math.BigDecimal;

public enum Coins {
	QUARTER(25), DIME(10), NICKEL(5), PENNY(1);

	private final int coinValue;

	Coins(int val) {
		this.coinValue = val;
	}

	public BigDecimal getVal() {
		return new BigDecimal(coinValue);
	}
}
