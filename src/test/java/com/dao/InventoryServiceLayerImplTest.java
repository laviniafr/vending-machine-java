package com.dao;

import com.dto.Item;
import com.service.*;
import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the InventoryServiceLayer class using Spring DI.
 */
public class InventoryServiceLayerImplTest {

	private final InventoryServiceLayer serviceLayer;
	private final Item testItem;

	public InventoryServiceLayerImplTest() {
//		InventoryDAO inventoryDAO = new InventoryDAOStubImpl();
//		AuditDAO auditDAO = new AuditDAOStubImpl();
//		serviceLayer = new InventoryServiceLayerImpl(inventoryDAO, auditDAO);
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		serviceLayer = context.getBean("serviceLayer", InventoryServiceLayer.class);
		testItem = new Item(1,"Crisps", new BigDecimal("4.5"),3);
	}

	/**
	 * Testing that the service layer returns the right item.
	 * @throws InventoryPersistenceException
	 */
	@Test
	public void testGetItem() throws InventoryPersistenceException {
		Item testItem = new Item();
		testItem.setName("Crisps");
		testItem.setID(1);
		testItem.setQuantity(3);
		testItem.setCost(new BigDecimal("4.5"));

		Item shouldBeCrisps = serviceLayer.getItem(1);
		/*
			Testing that the item is not null.
		 */
		assertNotNull(shouldBeCrisps, "Getting item with ID 1 should be not null");
		/*
			Testing that the item name is Crisps.
		 */
		assertEquals(shouldBeCrisps.getName(),testItem.getName(), "Item under ID 1 should be Crisps.");
		/*
			Testing that the ID is the same as the local test item ID.
		 */
		assertEquals(shouldBeCrisps.getID(),testItem.getID(), "Item ID should be 1.");
		/*
			Testing that the item quantity is the same as the local test item quantity.
		 */
		assertEquals(shouldBeCrisps.getQuantity(),testItem.getQuantity(), "Item quantity should be 3.");
		/*
			Testing that item cost is the same as the local test item cost.
		 */
		assertEquals(shouldBeCrisps.getCost().compareTo(testItem.getCost()),0, "Item cost should be 4.5 .");

	}

	/*
		Testing that the service layer successfully returns the desired list of items.
	 */
	@Test
	public void testGetAllItems() throws InventoryPersistenceException {
		List<Item> items = serviceLayer.getItems(false);
		/*
			Testing that the length of the list is 1.
		 */
		assertEquals(1, items.size(), "Item list should only have one item.");
		/*
			Testing that the first and only item of the list has the same name as the local test item.
		 */
		assertEquals(items.get(0).getName(), testItem.getName(), "Item under ID 1 should be Crisps.");
		/*
			Testing that the item in the list has the same ID as the local test item.
		 */
		assertEquals(items.get(0).getID(), testItem.getID(), "Item ID should be 1.");
		/*
			Testing that the item in the list has the same quantity as the local test item.
		 */
		assertEquals(items.get(0).getQuantity(), testItem.getQuantity(),"Item quantity should be 3.");
		/*
			Testing that item in the list has the same cost as the local test item.
		 */
		assertEquals(items.get(0).getCost().compareTo(testItem.getCost()),0,"Item cost should be 4.5");
	}

	/**
	 * Testing that the quantity of the item changes upon buying an item.
	 * @throws NoItemInventoryException
	 * @throws InventoryPersistenceException
	 * @throws InsufficientFundsException
	 */
	@Test
	public void testBuyItem() throws NoItemInventoryException, InventoryPersistenceException, InsufficientFundsException {
		testItem.setQuantity(2);
		serviceLayer.buyItem(1,1,new BigDecimal("10"));
		assertEquals(testItem.getQuantity(),serviceLayer.getItem(1).getQuantity());
	}


}
