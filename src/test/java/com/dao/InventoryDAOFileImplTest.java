package com.dao;

import com.dto.Item;
import com.service.InsufficientFundsException;
import com.service.InventoryPersistenceException;
import com.service.NoItemInventoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Inventory dao file impl test.
 */
class InventoryDAOFileImplTest {

	private InventoryDAO inventoryDAO;

	/**
	 * Sets up.
	 */
	@BeforeEach
	void setUp() {
		this.inventoryDAO = new InventoryDAOFileImpl();
	}

	/**
	 * Tests that the items in the file have the same ID, name and cost as expected.
	 *
	 * @throws InventoryPersistenceException The inventory persistence exception.
	 */
	@Test
	void getItems() throws InventoryPersistenceException {
		List<Item> itemList = new ArrayList<>();
		List<Item> fileItemList = this.inventoryDAO.getItems(false);
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
		for (int i = 0; i < itemList.size(); i++) {
			assertEquals(itemList.get(i).getID(), fileItemList.get(i).getID());
			assertEquals(itemList.get(i).getName(), fileItemList.get(i).getName());
			assertEquals(itemList.get(i).getCost(), fileItemList.get(i).getCost());
		}

	}

	/**
	 * Tests that all the items in the list to display to the user have a quantity greater than 0.
	 *
	 * @throws InventoryPersistenceException The inventory persistence exception.
	 */
	@Test
	void getNonNullItems() throws InventoryPersistenceException {
		List<Item> itemList = inventoryDAO.getItems(true);
		for (Item item: itemList){
			assertTrue(item.getQuantity()>0);
		}
	}


	/**
	 * Test that whenever an item is bought its stock decreases by one.
	 *
	 * @throws InventoryPersistenceException The inventory persistence exception.
	 * @throws NoItemInventoryException      The no item inventory exception.
	 * @throws InsufficientFundsException    The insufficient funds exception.
	 */
	@Test
	void buyItem() throws InventoryPersistenceException, NoItemInventoryException, InsufficientFundsException {
		Item itemToBuy = this.inventoryDAO.getItem(8);
		int quantityAfterPurchase = itemToBuy.getQuantity() - 1;
		this.inventoryDAO.buyItem(8, 1, new BigDecimal("10"));
		assertEquals(this.inventoryDAO.getItem(8).getQuantity(), quantityAfterPurchase);
		/*
			Put the item back in stock.
		 */
		this.inventoryDAO.buyItem(8, -1, new BigDecimal("10"));
	}

	/**
	 * Test that users can't buy items that aren't in stock.
	 */
	@Test
	void buyNoStockItem() {
		assertThrows(NoItemInventoryException.class, () -> this.inventoryDAO.buyItem(1, 1, new BigDecimal("10")));
	}

	/**
	 * Test that users can't buy items if they don't have enough funds.
	 */
	@Test
	void buyItemNoFunds() {
		assertThrows(InsufficientFundsException.class, () -> this.inventoryDAO.buyItem(2, 1, new BigDecimal("0")));
	}

}