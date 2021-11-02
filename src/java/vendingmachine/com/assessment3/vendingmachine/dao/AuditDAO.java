package com.assessment3.vendingmachine.dao;

import com.assessment3.vendingmachine.service.InventoryPersistenceException;

public interface AuditDAO {

	public void updateAudit(String auditLog) throws InventoryPersistenceException;
}
