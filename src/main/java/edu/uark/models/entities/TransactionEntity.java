package edu.uark.models.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import edu.uark.dataaccess.entities.BaseEntity;
import edu.uark.models.api.Transaction;
import edu.uark.models.entities.fieldnames.ProductFieldNames;
import edu.uark.models.entities.fieldnames.TransactionFieldNames;
import edu.uark.models.enums.TransactionClassification;
import edu.uark.models.repositories.TransactionRepository;

public class TransactionEntity extends BaseEntity<TransactionEntity> {

	//
	private UUID recordID;
	private int cashierID;
	private int totalAmount;
	private TransactionClassification transactionType;
	private UUID referenceID;
	private LocalDateTime createdOn;
	
	public int getCashierID(){
		return this.cashierID;
	}
	
	public TransactionEntity setCashierID(int ID){
		if (this.cashierID != ID) {
			this.cashierID = ID;
			this.propertyChanged(TransactionFieldNames.CASHIER_ID);
		}
		return this;
	}
	
	
	public int getTotalAmount(){
		return this.totalAmount;
	}
	
	public TransactionEntity setTotalAmount(int newTotalAmount){
		if (this.totalAmount != newTotalAmount) {
			this.totalAmount = newTotalAmount;
			this.propertyChanged(TransactionFieldNames.TOTAL_AMOUNT);
		}
		return this;
	}
	
	public TransactionClassification getTransactionType(){
		return this.transactionType;
	}
	
	public TransactionEntity setTransactionType(TransactionClassification newTransactionType){
		if (!this.transactionType.equals(newTransactionType)) {
			this.transactionType = newTransactionType;
			this.propertyChanged(TransactionFieldNames.TRANSACTION_TYPE);
		}
		return this;
	}
	
	public UUID getReferenceID(){
		return this.referenceID;
	}
	
	public TransactionEntity setReferenceID(UUID newReferenceID){
		if (!this.referenceID.equals(newReferenceID)) {
			this.referenceID = newReferenceID;
			this.propertyChanged(TransactionFieldNames.REFERENCE_ID);
		}
		return this;
	}
	
	public UUID getID(){
		return this.recordID;
	}
	
	public TransactionEntity setID(UUID ID){
		if (!this.recordID.equals(ID)) {
			this.recordID = ID;
			this.propertyChanged(TransactionFieldNames.RECORD_ID);
		}
		return this;
	}
	
	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}
	
	public TransactionEntity setCreatedOn(LocalDateTime createdOn) {
		if (!this.createdOn.equals(createdOn)) {
			this.createdOn = createdOn;
			this.propertyChanged(TransactionFieldNames.CREATED_ON);
		}
		return this;
	}

	@Override
	protected void fillFromRecord(ResultSet rs) throws SQLException {
		this.recordID = (UUID) rs.getObject(TransactionFieldNames.RECORD_ID); 
		this.cashierID = rs.getInt(TransactionFieldNames.CASHIER_ID);
		this.totalAmount = rs.getInt(TransactionFieldNames.TOTAL_AMOUNT);
		this.transactionType = (TransactionClassification) rs.getObject(TransactionFieldNames.TRANSACTION_TYPE);
		this.referenceID = (UUID) rs.getObject(TransactionFieldNames.REFERENCE_ID);
		this.createdOn = rs.getTimestamp(TransactionFieldNames.CREATED_ON).toLocalDateTime();
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		record.put(TransactionFieldNames.RECORD_ID, this.recordID);
		record.put(TransactionFieldNames.CASHIER_ID, this.cashierID);
		record.put(TransactionFieldNames.TOTAL_AMOUNT, this.totalAmount);
		record.put(TransactionFieldNames.TRANSACTION_TYPE, this.transactionType);
		record.put(TransactionFieldNames.REFERENCE_ID, this.referenceID);
		record.put(TransactionFieldNames.CREATED_ON, Timestamp.valueOf(this.createdOn));
		
		return record;
	}

	public Transaction synchronize(Transaction apiTransaction) {
		this.setCashierID(apiTransaction.getCashierID());
		this.setID(apiTransaction.getRecordID());
		this.setReferenceID(apiTransaction.getReferenceID());
		this.setTotalAmount(apiTransaction.getTotalAmount());
		this.setTransactionType(apiTransaction.getTransactionType());
		
		apiTransaction.setCreatedOn(this.createdOn);
		
		return apiTransaction;
	}
	
	public TransactionEntity() {
		super(new TransactionRepository());
		this.totalAmount = 0;
		this.transactionType = TransactionClassification.NOT_DEFINED;
		this.referenceID = new UUID(0,0);
		this.recordID = new UUID(0, 0);
		this.createdOn = LocalDateTime.now();
	}
	
	public TransactionEntity(UUID id) {
		super(id, new TransactionRepository());
		this.totalAmount = 0;
		this.transactionType = TransactionClassification.NOT_DEFINED;
		this.referenceID = new UUID(0,0);
		this.recordID = id;
		this.createdOn = LocalDateTime.now();
	}

	public TransactionEntity(Transaction apiTransaction) {
		super(apiTransaction.getRecordID(), new TransactionRepository());
		this.totalAmount = apiTransaction.getTotalAmount();
		this.transactionType = apiTransaction.getTransactionType();
		this.referenceID = apiTransaction.getReferenceID();
		this.recordID = apiTransaction.getRecordID();
		this.createdOn = LocalDateTime.now();
	}
}
