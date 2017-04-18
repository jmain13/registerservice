package edu.uark.commands.transactionEntry;

import java.util.stream.Collectors;

import edu.uark.commands.ResultCommandInterface;
import edu.uark.models.api.TransactionEntry;
import edu.uark.models.api.TransactionEntryListing;
import edu.uark.models.repositories.TransactionEntryRepository;
import edu.uark.models.repositories.interfaces.TransactionEntryRepositoryInterface;

public class TransactionEntrysQuery implements ResultCommandInterface<TransactionEntryListing> {
	@Override
	public TransactionEntryListing execute() {
		return (new TransactionEntryListing()).
			setTransactionEntrys(
				this.transactionEntryRepository.
					all().
					stream().
					map(mp -> (new TransactionEntry(mp))).
					collect(Collectors.toList()
			)
		);
	}

	//Properties
	private TransactionEntryRepositoryInterface transactionEntryRepository;
	public TransactionEntryRepositoryInterface getTransactionEnrtyRepository() {
		return this.transactionEntryRepository;
	}
	public TransactionEntrysQuery setTransactionEntry(TransactionEntryRepositoryInterface transactionEntryRepository) {
		this.transactionEntryRepository = transactionEntryRepository;
		return this;
	}
	
	public TransactionEntrysQuery() {
		this.transactionEntryRepository = new TransactionEntryRepository();
	}
}
