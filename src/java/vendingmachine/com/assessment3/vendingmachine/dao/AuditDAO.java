package com.assessment3.vendingmachine.dao;

import com.assessment3.vendingmachine.service.InventoryPersistenceException;

/**
 * The interface AuditDAO.
 */
public interface AuditDAO {

	/**
	 * Method that updates the audit log.
	 *
	 * @param auditLog - The audit log.
	 * @throws InventoryPersistenceException - The inventory persistence exception.
	 */
	void updateAudit(String auditLog) throws InventoryPersistenceException;
}
