package edu.uark.models.repositories.interfaces;

import edu.uark.dataaccess.repository.BaseRepositoryInterface;
import edu.uark.models.entities.TransactionEntryEntity;

public interface TransactionEntryRepositoryInterface extends BaseRepositoryInterface<TransactionEntryEntity> {
	TransactionEntryEntity byLookupCode(String lookupCode);
}
