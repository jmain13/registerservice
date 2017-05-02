package edu.uark.models.api;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import edu.uark.models.api.enums.TransactionEntryApiRequestStatus;
import edu.uark.models.entities.TransactionEntryEntity;

public class TransactionEntry {
	private UUID entryId;
	public UUID getentryId() {
		return this.entryId;
	}
	public TransactionEntry setentryId(UUID entryId) {
		this.entryId = entryId;
		return this;
	}
	
	private UUID fromTransaction;
	public UUID getfromTransaction() {
		return this.fromTransaction;
	}
	public TransactionEntry setfromtransaction(UUID fromTransaction) {
		this.fromTransaction = fromTransaction;
		return this;
	}
	
	private String lookupCode;
	public String getlookupCode() {
		return this.lookupCode;
	}
	public TransactionEntry setlookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
		return this;
	}
	
	private int quantity;
	public int getQuantity() {
		return this.quantity;
	}
	public TransactionEntry setQuantity(int quantity) {
		this.quantity = quantity;
		return this;
	}

	private float price;
	public float getPrice() {
		return this.price;
	}
	public TransactionEntry setPrice(float price) {
		this.price = price;
		return this;
	}
	
	private TransactionEntryApiRequestStatus apiRequestStatus;
	public TransactionEntryApiRequestStatus getApiRequestStatus() {
		return this.apiRequestStatus;
	}
	public TransactionEntry setApiRequestStatus(TransactionEntryApiRequestStatus apiRequestStatus) {
		if (this.apiRequestStatus != apiRequestStatus) {
			this.apiRequestStatus = apiRequestStatus;
		}
		
		return this;
	}
	
	private String apiRequestMessage;
	public String getApiRequestMessage() {
		return this.apiRequestMessage;
	}
	public TransactionEntry setApiRequestMessage(String apiRequestMessage) {
		if (!StringUtils.equalsIgnoreCase(this.apiRequestMessage, apiRequestMessage)) {
			this.apiRequestMessage = apiRequestMessage;
		}
		
		return this;
	}
	
	public TransactionEntry() {
		this.entryId = new UUID(0,0);
		this.fromTransaction = new UUID(0,0);
		this.lookupCode = "";
		this.quantity = 0;
		this.price = 0;
	}
	
	public TransactionEntry(TransactionEntryEntity transactionEntryEntity) {
		this.entryId = transactionEntryEntity.getentryId();
		this.fromTransaction = transactionEntryEntity.getfromTransaction();
		this.lookupCode = transactionEntryEntity.getlookupCode();
		this.quantity = transactionEntryEntity.getQuantity();
		this.price = transactionEntryEntity.getPrice();

		this.apiRequestMessage = StringUtils.EMPTY;
		this.apiRequestStatus = TransactionEntryApiRequestStatus.OK;
	}
}
