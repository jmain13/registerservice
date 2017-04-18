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
	
	private String plu;
	public String getplu() {
		return this.plu;
	}
	public TransactionEntry setplu(String plu) {
		this.plu = plu;
		return this;
	}
	
	private int amount;
	public int getamount() {
		return this.amount;
	}
	public TransactionEntry setamount(int amount) {
		this.amount = amount;
		return this;
	}

	private float soldAt;
	public float getsoldAt() {
		return this.soldAt;
	}
	public TransactionEntry setsoldAt(float soldAt) {
		this.soldAt = soldAt;
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
		this.plu = "";
		this.amount = 0;
		this.soldAt = 0;
	}
	
	public TransactionEntry(TransactionEntryEntity transactionEntryEntity) {
		this.entryId = transactionEntryEntity.getentryId();
		this.fromTransaction = transactionEntryEntity.getfromTransaction();
		this.plu = transactionEntryEntity.getplu();
		this.amount = transactionEntryEntity.getamount();
		this.soldAt = transactionEntryEntity.getsoldAt();

		this.apiRequestMessage = StringUtils.EMPTY;
		this.apiRequestStatus = TransactionEntryApiRequestStatus.OK;
	}
}
