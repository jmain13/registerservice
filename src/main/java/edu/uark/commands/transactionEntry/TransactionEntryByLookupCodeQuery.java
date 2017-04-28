package edu.uark.commands.transactionEntry;

import org.apache.commons.lang3.StringUtils;

import edu.uark.commands.ResultCommandInterface;
import edu.uark.models.api.TransactionEntry;
import edu.uark.models.api.enums.TransactionEntryApiRequestStatus;
import edu.uark.models.entities.TransactionEntryEntity;
import edu.uark.models.repositories.TransactionEntryRepository;
import edu.uark.models.repositories.interfaces.TransactionEntryRepositoryInterface;

public class TransactionEntryByLookupCodeQuery implements ResultCommandInterface<TransactionEntry> {
	@Override
	public TransactionEntry execute() {
		if (StringUtils.isBlank(this.lookupCode)) {
			return new TransactionEntry().setApiRequestStatus(TransactionEntryApiRequestStatus.INVALID_INPUT);
		}
		
		TransactionEntryEntity transactionEntryEntity = this.transactionEntryRepository.byLookupCode(this.lookupCode);
		if (transactionEntryEntity != null) {
			return new TransactionEntry(transactionEntryEntity);
		} else {
			return new TransactionEntry().setApiRequestStatus(TransactionEntryApiRequestStatus.NOT_FOUND);
		}
	}

	//Properties
	private String lookupCode;
	public String getLookupCode() {
		return this.lookupCode;
	}
	public TransactionEntryByLookupCodeQuery setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
		return this;
	}
	
	private TransactionEntryRepositoryInterface transactionEntryRepository;
	public TransactionEntryRepositoryInterface getTransactionEntryRepository() {
		return this.transactionEntryRepository;
	}
	public TransactionEntryByLookupCodeQuery setProductRepository(TransactionEntryRepositoryInterface transactionEntryRepository) {
		this.transactionEntryRepository = transactionEntryRepository;
		return this;
	}
	
	public TransactionEntryByLookupCodeQuery() {
		this.transactionEntryRepository = new TransactionEntryRepository();
	}
}
