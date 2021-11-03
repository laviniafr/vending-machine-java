package com.assessment3.vendingmachine.dao;

import com.assessment3.vendingmachine.service.InventoryPersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AuditDAOFileImplTest {

	private AuditDAO auditDAO;
	@BeforeEach
	void setUp() {
		auditDAO = new AuditDAOFileImpl();
	}

	@Test
	void testConstructor() {
		assertNotNull(this.auditDAO);
	}
	@Test
	void testUpdateAudit() throws InventoryPersistenceException, IOException {
		this.auditDAO.updateAudit("Testing...");
		FileReader fr = new FileReader(AuditDAOFileImpl.AUDIT_FILE);
		BufferedReader bf = new BufferedReader(fr);
		String line;
		String lastLine = null;
		while((line=bf.readLine())!=null){
			lastLine = line;
		}
		bf.close();
		fr.close();
		assertEquals(lastLine.substring(0,10),"Testing..." );
	}

}