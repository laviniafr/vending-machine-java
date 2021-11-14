package com.dao;

import com.dto.Item;
import com.service.InsufficientFundsException;
import com.service.InventoryPersistenceException;
import com.service.NoItemInventoryException;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The file implementation of the InventoryDAO interface.
 */
public class InventoryDAOFileImpl implements InventoryDAO {
	/**
	 * A map object to store items and their ID.
	 */
	Map<Integer, Item> items ;
	/**
	 * A constant to hold the default csv file delimiter.
	 */
	public static final String DELIMITER = ",";
	/**
	 * The constant to hold the input csv file.
	 */
	public static final String CSV_FILE = "items.csv";

	/**
	 * Instantiates a new Inventory dao file.
	 */
	public InventoryDAOFileImpl() {
		this.items = new HashMap<>();
	}

	/**
	 * Method that gets the items from file and returns a list of type Item.
	 * @param display - Parameter that decides whether the method is used for displaying the items to the user or
	 *                for other purposes. If the method is used for display, a lambda stream is applied to sort out
	 *                the items that are out of stock.
	 * @return - The Item list.
	 * @throws InventoryPersistenceException - The inventory persistence exception.
	 */
	@Override
	public List<Item> getItems(boolean display) throws InventoryPersistenceException {
		this.loadDataFromFile();
		List<Item> listToReturn;
		if(display){
			listToReturn = new ArrayList<>(items.values().stream().filter((i) ->i.getQuantity()>0).collect(Collectors.toList()));
		}
		else {
			listToReturn = new ArrayList<>(items.values());
		}
		return listToReturn;
	}

	/**
	 * Method that loads the items from the file returns one item based on a given ID.
	 * @param itemID - The item ID.
	 * @return - The Item.
	 * @throws InventoryPersistenceException - The Inventory Persistence Exception.
	 */
	@Override
	public Item getItem(int itemID) throws InventoryPersistenceException {
		this.loadDataFromFile();
		return items.get(itemID);
	}

	/**
	 * Method that buys a given amount of a particular item based on its ID and the funds available.
	 * @param ID       - The ID of the item to be bought.
	 * @param quantity - The amount of items to be bought.
	 * @param funds    - The available funds.
	 * @throws InventoryPersistenceException - The Inventory Persistence Exception.
	 * @throws NoItemInventoryException - The No Item Inventory Exception.
	 * @throws InsufficientFundsException - The Insufficient Funds Exception.
	 */
	@Override
	public void buyItem(int ID, int quantity, BigDecimal funds) throws InventoryPersistenceException, NoItemInventoryException, InsufficientFundsException {
		this.loadDataFromFile();
		Item itemToBuy = items.get(ID);
		/*
			If the item cost is greater than the available funds, an InsufficientFundsException is thrown.
		 */
		if (itemToBuy.getCost().compareTo(funds) > 0) {
			throw new InsufficientFundsException("Insufficient funds to buy item.");
		} else {
			/*
				If the available item quantity is greater than 0, the item is bought and the quantity is updated
				on file, otherwise the NoItemInventoryException is thrown.
			 */
			if (itemToBuy.getQuantity() > 0) {
				itemToBuy.setQuantity(itemToBuy.getQuantity() - quantity);
				this.writeDataToFile();
			} else {
				throw new NoItemInventoryException("The item is out of stock!");
			}
		}

	}

	/**
	 * Method to read an Item object based on text from file.
	 * @param itemText - The text to be read from.
	 * @return - The Item object.
	 * @throws InventoryPersistenceException - The Inventory Persistence Exception.
	 */
	private Item readItemFromText(String itemText) throws InventoryPersistenceException {
		/*
			Split the text into tokens.
		 */
		String[] tokens = itemText.split(DELIMITER);
		Item newItem = new Item();
		/*
			Create a new Item object, assuming the order of the fields in the file is correct.
		 */
		try {
			newItem.setID(Integer.parseInt(tokens[0]));
			newItem.setName(tokens[1]);
			newItem.setCost(new BigDecimal(tokens[2]));
			newItem.setQuantity(Integer.parseInt(tokens[3]));
		} catch (NumberFormatException e) {
			throw new InventoryPersistenceException("Could not parse input.");
		}
		return newItem;
	}

	/**
	 * Method to load data from file.
	 * @throws InventoryPersistenceException - The Inventory Persistence Exception
	 */
	private void loadDataFromFile() throws InventoryPersistenceException {
		try {
			FileReader fileReader = new FileReader(CSV_FILE);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String itemText;
			while ((itemText = bufferedReader.readLine()) != null) {
				Item newItem = readItemFromText(itemText);
				this.items.put(newItem.getID(), newItem);
			}
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			throw new InventoryPersistenceException("Couldn't retrieve the input file.");
		}


	}

	/**
	 * Method to get the right format from an item's attributes to be written to file.
	 * @param item
	 * @return
	 */
	private String getFormat(Item item) {
		return item.getID() + "," + item.getName() + "," + item.getCost() + "," + item.getQuantity();
	}

	/**
	 * Method that writes existing data back to file.
	 * @throws InventoryPersistenceException - The Inventory Persistence Exception
	 */
	private void writeDataToFile() throws InventoryPersistenceException {
		try {
			FileWriter fileWriter = new FileWriter(CSV_FILE);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for (Item item : this.getItems(false)) {
				bufferedWriter.write(this.getFormat(item));
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			throw new InventoryPersistenceException("Couldn't write data back to file.");
		}
	}

}
