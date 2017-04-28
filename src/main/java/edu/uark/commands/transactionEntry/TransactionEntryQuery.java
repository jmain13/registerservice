package edu.uark.commands.transactionEntry;

import java.util.UUID;

import edu.uark.commands.ResultCommandInterface;
import edu.uark.models.api.TransactionEntry;
import edu.uark.models.repositories.TransactionEntryRepository;
import edu.uark.models.repositories.interfaces.TransactionEntryRepositoryInterface;

public class TransactionEntryQuery implements ResultCommandInterface<TransactionEntry>{
	@Override
	public TransactionEntry execute() {
		return new TransactionEntry(this.transactionEntryRepository.get(this.transactionEntryId)
		);
	}

	//Properties
	private UUID transactionEntryId;
	public UUID getTransactionEntryId() {
		return this.transactionEntryId;
	}
	public TransactionEntryQuery setProductId(UUID productId) {
		this.transactionEntryId = productId;
		return this;
	}
	
	private TransactionEntryRepositoryInterface transactionEntryRepository;
	public TransactionEntryRepositoryInterface getProductRepository() {
		return this.transactionEntryRepository;
	}
	public TransactionEntryQuery setTransactionEntryRepository(TransactionEntryRepositoryInterface transactionEntryRepository) {
		this.transactionEntryRepository = transactionEntryRepository;
		return this;
	}
	
	public TransactionEntryQuery() {
		this.transactionEntryRepository = new TransactionEntryRepository();
	
	}
}
