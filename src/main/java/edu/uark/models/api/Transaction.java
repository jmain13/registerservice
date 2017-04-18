package edu.uark.models.api;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import edu.uark.models.api.enums.TransactionApiRequestStatus;
import edu.uark.models.entities.TransactionEntity;
import edu.uark.models.enums.TransactionClassification;

public class Transaction {
	private UUID recordID;
	
//	
	private int cashierID;
	private int totalAmount;
	private int transactionType;
	private UUID referenceID;
	private LocalDateTime createdOn;
	
	public int getCashierID(){
		return this.cashierID;
	}
	
	public Transaction setCashierID(int ID){
		this.cashierID = ID;
		return this;
	}
	
	public int  getTotal(){
		return this.total;
	}
	
	public Transaction setTotal(int newTotal){
		this.total = newTotal;
		return this;
	}
	
	public int getTotalAmount(){
		return this.totalAmount;
	}
	
	public Transaction setTotalAmount(int newTotalAmount){
		this.totalAmount = newTotalAmount;
		return this;
	}
	
	public int getTransactionType(){
		return this.transactionType;
	}
	
	public Transaction setTransactionType(int newTransactionType){
		this.transactionType = newTransactionType;
		return this;
	}
	
	public UUID getReferenceID(){
		return this.referenceID;
	}
	
	public Transaction setReferenceID(UUID newReferenceID){
		this.referenceID = newReferenceID;
		return this;
	}
	//	
	
	public UUID getRecordId() {
		return this.recordID;
	}
	public Transaction setRecordId(UUID id) {
		this.recordID = id;
		return this;
	}
	
	private TransactionApiRequestStatus apiRequestStatus;
	public TransactionApiRequestStatus getApiRequestStatus() {
		return this.apiRequestStatus;
	}
	public Transaction setApiRequestStatus(TransactionApiRequestStatus apiRequestStatus) {
		if (this.apiRequestStatus != apiRequestStatus) {
			this.apiRequestStatus = apiRequestStatus;
		}
		
		return this;
	}
	
	private String apiRequestMessage;
	public String getApiRequestMessage() {
		return this.apiRequestMessage;
	}
	public Transaction setApiRequestMessage(String apiRequestMessage) {
		if (!StringUtils.equalsIgnoreCase(this.apiRequestMessage, apiRequestMessage)) {
			this.apiRequestMessage = apiRequestMessage;
		}
		
		return this;
	}
	
	public Transaction() {
		this.totalAmount = 0;
		this.transactionType = TransactionClassification.NOT_DEFINED;
		this.referenceID = new UUID(0,0);
		this.recordID = new UUID(0, 0);
		this.createdOn = LocalDateTime.now();
		this.apiRequestMessage = StringUtils.EMPTY;
		this.apiRequestStatus = TransactionApiRequestStatus.OK;
	}
	
	public Transaction(TransactionEntity transactionEntity) {
		this.recordID = transactionEntity.getRecordID();
		this.totalAmount = transactionEntity.getTotalAmount();
		this.transactionType = transactionEntity.getTransactionType();
		this.referenceID = transactionEntity.getReferenceID();
		this.createdOn = transactionEntity.getCreatedOn();
		this.apiRequestMessage = StringUtils.EMPTY;
		this.apiRequestStatus = TransactionApiRequestStatus.OK;
	}
	
}