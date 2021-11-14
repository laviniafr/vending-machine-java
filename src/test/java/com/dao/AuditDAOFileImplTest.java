package com.dao;

import com.service.InventoryPersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Audit dao file impl test.
 */
class AuditDAOFileImplTest {

	private AuditDAO auditDAO;

	/**
	 * Sets up.
	 */
	@BeforeEach
	void setUp() {
		auditDAO = new AuditDAOFileImpl();
	}

	/**
	 * Test constructor.
	 */
	@Test
	void testConstructor() {
		assertNotNull(this.auditDAO);
	}

	/**
	 * Test update audit by checking that a specific message is written to the file.
	 *
	 * @throws InventoryPersistenceException the inventory persistence exception
	 * @throws IOException                   the io exception
	 */
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