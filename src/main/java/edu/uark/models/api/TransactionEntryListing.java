package edu.uark.models.api;

import java.util.LinkedList;
import java.util.List;

public class TransactionEntryListing {
	private List<TransactionEntry> transactionEntrys;
	public List<TransactionEntry> getTransactionEntry() {
		return this.transactionEntrys;
	}
	public TransactionEntryListing setTransactionEntrys(List<TransactionEntry> transactionEntry) {
		this.transactionEntrys = transactionEntry;
		return this;
	}
	public TransactionEntryListing addTransactionEntry(TransactionEntry transactionEntry) {
		this.transactionEntrys.add(transactionEntry);
		return this;
	}
	
	public TransactionEntryListing() {
		this.transactionEntrys = new LinkedList<TransactionEntry>();
	}
}
