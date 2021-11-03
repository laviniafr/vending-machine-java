package com.assessment3.vendingmachine.dto;

import java.math.BigDecimal;

/**
 * The type Item.
 */
public class Item {

	private String name;
	private BigDecimal cost;
	private int quantity;
	private int ID;

	/**
	 * Instantiates a new Item.
	 */
	public Item() {
	}

	/**
	 * Instantiates a new Item.
	 *
	 * @param ID       the id
	 * @param name     the name
	 * @param cost     the cost
	 * @param quantity the quantity
	 */
	public Item(int ID, String name, BigDecimal cost, int quantity) {
		this.ID = ID;
		this.name = name;
		this.cost = cost;
		this.quantity = quantity;
	}

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public int getID() {
		return this.ID;
	}

	/**
	 * Sets id.
	 *
	 * @param ID the id
	 */
	public void setID(int ID) {
		this.ID = ID;
	}

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets name.
	 *
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets cost.
	 *
	 * @return the cost
	 */
	public BigDecimal getCost() {
		return this.cost;
	}

	/**
	 * Sets cost.
	 *
	 * @param cost the cost
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	/**
	 * Gets quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * Sets quantity.
	 *
	 * @param quantity the quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return this.ID + "," + this.name + "," + this.cost;
	}
}
