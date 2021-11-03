package com.assessment3.vendingmachine.dao;

import com.assessment3.vendingmachine.service.InventoryPersistenceException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * The file implementation of the Audit DAO interface.
 */
public class AuditDAOFileImpl implements AuditDAO {

	/**
	 * The constant AUDIT_FILE representing the file to write logs to.
	 */
	public static final String AUDIT_FILE = "auditlogs.txt";

	/**
	 * Instantiates a new Audit dao file.
	 */
	public AuditDAOFileImpl() {
	}

	/**
	 * Method to update the audit log with a given message and the date and time the action was performed.
	 * @param message - The message to be added to the audit logs.
	 * @throws InventoryPersistenceException - The Inventory Persistence Exception.
	 */
	@Override
	public void updateAudit(String message) throws InventoryPersistenceException {
		try {
			FileWriter fileWriter = new FileWriter(AUDIT_FILE, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			LocalDateTime dateTime = LocalDateTime.now();
			bufferedWriter.append(message).append("\t").append(String.valueOf(dateTime)).append("\n");
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			throw new InventoryPersistenceException("Couldn't log to file.");
		}
	}
}
