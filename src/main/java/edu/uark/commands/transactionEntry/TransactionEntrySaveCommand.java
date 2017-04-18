package edu.uark.commands.transactionEntry;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import edu.uark.commands.ResultCommandInterface;
import edu.uark.models.api.TransactionEntry;
import edu.uark.models.api.enums.TransactionEntryApiRequestStatus;
import edu.uark.models.entities.TransactionEntryEntity;
import edu.uark.models.repositories.TransactionEntryRepository;
import edu.uark.models.repositories.interfaces.TransactionEntryRepositoryInterface;

public class TransactionEntrySaveCommand implements ResultCommandInterface<TransactionEntry> {
	@Override
	public TransactionEntry execute() {
		if (StringUtils.isBlank(this.apiTransactionEntry.getplu())) {
			return (new TransactionEntry()).setApiRequestStatus(TransactionEntryApiRequestStatus.INVALID_INPUT);
		}
		
		TransactionEntryEntity transactionEntryEntity = this.transactionEntryRepository.get(this.apiTransactionEntry.getentryId());
		if (transactionEntryEntity != null) {
			this.apiTransactionEntry = transactionEntryEntity.synchronize(this.apiTransactionEntry);
		} else {
			transactionEntryEntity = this.transactionEntryRepository.byLookupCode(this.apiTransactionEntry.getplu());
			if (transactionEntryEntity == null) {
				transactionEntryEntity = new TransactionEntryEntity(this.apiTransactionEntry);
			} else {
				return (new TransactionEntry()).setApiRequestStatus(TransactionEntryApiRequestStatus.LOOKUP_CODE_ALREADY_EXISTS);
			}
		}
		
		transactionEntryEntity.save();
		if ((new UUID(0, 0)).equals(this.apiTransactionEntry.getplu())) {
			this.apiTransactionEntry.setplu(transactionEntryEntity.getplu());
		}
		
		return this.apiTransactionEntry;
	}

	//Properties
	private TransactionEntry apiTransactionEntry;
	public TransactionEntry getApiTransactionEntry() {
		return this.apiTransactionEntry;
	}
	public TransactionEntrySaveCommand setApiTransactionEntry(TransactionEntry apiTransactionEntry) {
		this.apiTransactionEntry = apiTransactionEntry;
		return this;
	}
	
	private TransactionEntryRepositoryInterface transactionEntryRepository;
	public TransactionEntryRepositoryInterface getTransactionEntryRepository() {
		return this.transactionEntryRepository;
	}
	public TransactionEntrySaveCommand setTransactionEntryRepository(TransactionEntryRepositoryInterface transactionEntryRepository) {
		this.transactionEntryRepository = transactionEntryRepository;
		return this;
	}
	
	public TransactionEntrySaveCommand() {
		this.transactionEntryRepository = new TransactionEntryRepository();
	}
}
