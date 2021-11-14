package com.main;

import com.controller.InventoryController;
import com.dao.AuditDAO;
import com.dao.AuditDAOFileImpl;
import com.dao.InventoryDAO;
import com.dao.InventoryDAOFileImpl;
import com.service.*;
import com.ui.InventoryView;
import com.ui.UserIO;
import com.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The type Application.
 */
public class Application {
	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 * @throws InventoryPersistenceException - The inventory persistence exception.
	 * @throws NoItemInventoryException      - The no item inventory exception.
	 * @throws InsufficientFundsException    - The insufficient funds exception.
	 */
	public static void main(String[] args) throws InventoryPersistenceException, NoItemInventoryException, InsufficientFundsException {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		InventoryController controller = context.getBean("inventoryController", InventoryController.class);
		controller.run();
	}
}
