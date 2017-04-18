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
import edu.uark.models.entities.fieldnames.TransactionFieldNames;
import edu.uark.models.repositories.TransactionRepository;

public class TransactionEntity extends BaseEntity<TransactionEntity> {

	//
	private UUID recordID;
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

	@Override
	protected void fillFromRecord(ResultSet rs) throws SQLException {
		this.recordID = rs.getObject(TransactionFieldNames.RECORD_ID); 
		this.cashierID = rs.getInt(TransactionFieldNames.CASHIER_ID);
		this.totalAmount = rs.getInt(TransactionFieldNames.TOTAL_AMOUNT;
		this.transactionType = rs.getInt(TransactionFieldNames.TRANSACTION_TYPE);
		this.referenceID = rs.getString(TransactionFieldNames.REFERENCE_ID;
		this.createdOn = rs.getTimestamp(TransactionFieldNames.CREATEDON).toLocalDateTime();
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


	private LocalDateTime createdOn;
	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}
	
	public Transaction synchronize(Transaction apiTransaction) {
		this.setCount(apiTransaction.getCount());
		this.setLookupCode(apiTransaction.getLookupCode());
		
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
