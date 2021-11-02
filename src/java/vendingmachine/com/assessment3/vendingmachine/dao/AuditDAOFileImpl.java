package com.assessment3.vendingmachine.dao;

import com.assessment3.vendingmachine.service.InventoryPersistenceException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class AuditDAOFileImpl implements AuditDAO {

	public static final String AUDIT_FILE = "auditlogs.txt";

	public AuditDAOFileImpl() {
	}

	@Override
	public void updateAudit(String auditLog) throws InventoryPersistenceException {
		try {
			FileWriter fileWriter = new FileWriter(AUDIT_FILE, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			LocalDateTime dateTime = LocalDateTime.now();
			bufferedWriter.append(auditLog+"\t" + dateTime.toString()+"\n");
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
