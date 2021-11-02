package com.assessment3.vendingmachine.dto;

import java.math.BigDecimal;

import java.util.*;

public class Change {

	private final Map<BigDecimal, String> coinsList;

	public Change() {
		this.coinsList = new LinkedHashMap<>();
		for (Coins coin : Coins.values()){
			coinsList.put(coin.getVal(), coin.toString());
		}
		System.out.println(coinsList);
	}

	private BigDecimal getDecimalPoint(BigDecimal number){
		return number.subtract(new BigDecimal(number.toBigInteger().toString()));
	}
	public Map<String, Integer> getChange(BigDecimal sum){
		Map<String, Integer> listOfCoins = new LinkedHashMap<>();
		BigDecimal decimalPoint = getDecimalPoint(sum);
		listOfCoins.put("DOLLARS", (int) Double.parseDouble(sum.subtract(decimalPoint).toString()));
		System.out.println(decimalPoint);
		decimalPoint = getDecimalPoint(sum).multiply(BigDecimal.valueOf(100));
		int count;
		for(BigDecimal coinValue : this.coinsList.keySet()){
			count = 0;
			while (decimalPoint.subtract(coinValue).compareTo(BigDecimal.valueOf(0))>=0){
				decimalPoint = decimalPoint.subtract(coinValue);
				listOfCoins.put(coinsList.get(coinValue),++count);
			}
		}
		return listOfCoins;
	}

	public static void main(String[] args) {

		Change change = new Change();
		change.getChange(BigDecimal.valueOf(4.9));
	}

}
