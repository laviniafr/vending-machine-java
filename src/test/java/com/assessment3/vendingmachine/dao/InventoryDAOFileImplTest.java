package com.assessment3.vendingmachine.dao;

import com.assessment3.vendingmachine.dto.Item;
import com.assessment3.vendingmachine.service.InsufficentFundsException;
import com.assessment3.vendingmachine.service.InventoryPersistenceException;
import com.assessment3.vendingmachine.service.NoItemInventoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryDAOFileImplTest {

	private InventoryDAO inventoryDAO;

	@BeforeEach
	void setUp() {
		this.inventoryDAO = new InventoryDAOFileImpl();
	}

	@Test
	void getItems() throws InventoryPersistenceException {
		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item(1, "Brownie", new BigDecimal("4.25"), 0));
		itemList.add(new Item(2, "Cookie", new BigDecimal("3.53"), 4));
		itemList.add(new Item(3, "Crisps", new BigDecimal("3.66"), 5));
		itemList.add(new Item(4, "Energy Drink", new BigDecimal("6.78"), 0));
		itemList.add(new Item(5, "Iced tea", new BigDecimal(4), 3));
		itemList.add(new Item(6, "Chocolate Bar", new BigDecimal("4.98"), 8));
		itemList.add(new Item(7, "Cereal Bar", new BigDecimal("5.20"), 7));
		itemList.add(new Item(8, "Pretzels", new BigDecimal("3.35"), 5));
		itemList.add(new Item(9, "Cold Coffee", new BigDecimal("5.66"), 4));
		itemList.add(new Item(10, "Cheese Puffs", new BigDecimal("3.77"), 6));
		assertEquals(itemList.toString(), this.inventoryDAO.getItems(false).toString());

	}

	@Test
	void getNonNullItems() throws InventoryPersistenceException {
		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item(2, "Cookie", new BigDecimal("3.53"), 4));
		itemList.add(new Item(3, "Crisps", new BigDecimal("3.66"), 5));
		itemList.add(new Item(5, "Iced tea", new BigDecimal(4), 3));
		itemList.add(new Item(6, "Chocolate Bar", new BigDecimal("4.98"), 8));
		itemList.add(new Item(7, "Cereal Bar", new BigDecimal("5.20"), 7));
		itemList.add(new Item(8, "Pretzels", new BigDecimal("3.35"), 5));
		itemList.add(new Item(9, "Cold Coffee", new BigDecimal("5.66"), 4));
		itemList.add(new Item(10, "Cheese Puffs", new BigDecimal("3.77"), 6));
		assertEquals(itemList.toString(), this.inventoryDAO.getItems(true).toString());

	}

	@Test
	void getItem() throws InventoryPersistenceException {
		Item itemToGet = new Item(8, "Pretzels", new BigDecimal("3.35"), 5);
		assertEquals(itemToGet.toString(), this.inventoryDAO.getItem(8).toString());
	}

	@Test
	void buyItem() throws InventoryPersistenceException, NoItemInventoryException, InsufficentFundsException {
		Item itemToBuy = this.inventoryDAO.getItem(8);
		int quantityAfterPurchase = itemToBuy.getQuantity() - 1;
		this.inventoryDAO.buyItem(8, 1, new BigDecimal("10"));
		assertEquals(this.inventoryDAO.getItem(8).getQuantity(), quantityAfterPurchase);
		// put the item back!
		this.inventoryDAO.buyItem(8, -1, new BigDecimal("10"));
	}

	@Test
	void buyNoStockItem() {
		assertThrows(NoItemInventoryException.class, () -> this.inventoryDAO.buyItem(1, 1, new BigDecimal("10")));
	}

	@Test
	void buyItemNoFunds() {
		assertThrows(InsufficentFundsException.class, () -> this.inventoryDAO.buyItem(2,1,new BigDecimal("0")));
	}

}