package com.assessment3.vendingmachine.dto;

import java.math.BigDecimal;

public class Item {

	private String name;
	private BigDecimal cost;
	private int quantity;
	private int ID;

	public Item() {
	}

	public Item(int ID, String name, BigDecimal cost, int quantity) {
		this.ID = ID;
		this.name = name;
		this.cost = cost;
		this.quantity = quantity;
	}

	public int getID() {
		return this.ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getCost() {
		return this.cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return this.ID + "\t" + this.name + "\t\t" + this.cost + "\t\t\t" + this.quantity;
	}
}
