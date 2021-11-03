package com.assessment3.vendingmachine.dao;

import com.assessment3.vendingmachine.dto.Item;
import com.assessment3.vendingmachine.service.InsufficentFundsException;
import com.assessment3.vendingmachine.service.InventoryPersistenceException;
import com.assessment3.vendingmachine.service.NoItemInventoryException;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InventoryDAOFileImpl implements InventoryDAO {
	Map<Integer, Item> items ;
	public static final String DELIMITER = ",";
	public static final String CSV_FILE = "items.csv";

	public InventoryDAOFileImpl() {
		this.items = new HashMap<>();
	}

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

	@Override
	public Item getItem(int itemID) throws InventoryPersistenceException {
		this.loadDataFromFile();
		return items.get(itemID);
	}

	@Override
	public void buyItem(int ID, int quantity, BigDecimal funds) throws InventoryPersistenceException, NoItemInventoryException, InsufficentFundsException {
		this.loadDataFromFile();
		Item itemToBuy = items.get(ID);
		if (itemToBuy.getCost().compareTo(funds) > 0) {
			throw new InsufficentFundsException("Insufficient funds to buy item.");
		} else {
			if (itemToBuy.getQuantity() > 0) {
				itemToBuy.setQuantity(itemToBuy.getQuantity() - quantity);
				this.writeDataToFile();
			} else {
				throw new NoItemInventoryException("The item is out of stock!");
			}
		}

	}

	private Item readItemFromText(String itemText) throws InventoryPersistenceException {
		String[] tokens = itemText.split(DELIMITER);
		Item newItem = new Item();
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

	private String getFormat(Item item) {
		return item.getID() + "," + item.getName() + "," + item.getCost() + "," + item.getQuantity();
	}

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
