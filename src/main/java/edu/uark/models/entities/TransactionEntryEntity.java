package edu.uark.models.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import edu.uark.dataaccess.entities.BaseEntity;
import edu.uark.models.api.TransactionEntry;
import edu.uark.models.entities.fieldnames.TransactionEntryFieldNames;
import edu.uark.models.repositories.TransactionEntryRepository;

public class TransactionEntryEntity extends BaseEntity<TransactionEntryEntity> {
	@Override
	protected void fillFromRecord(ResultSet rs) throws SQLException {
		this.entryId = ((UUID)rs.getObject(TransactionEntryFieldNames.ENTRY_ID));
		this.fromTransaction = ((UUID)rs.getObject(TransactionEntryFieldNames.FROM_TRANSACTION));
		this.lookupCode = rs.getString(TransactionEntryFieldNames.LOOKUP_CODE);
		this.quantity = rs.getInt(TransactionEntryFieldNames.QUANTITY);
		this.price = rs.getFloat(TransactionEntryFieldNames.PRICE);
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		record.put(TransactionEntryFieldNames.ENTRY_ID, this.entryId);
		record.put(TransactionEntryFieldNames.FROM_TRANSACTION, this.fromTransaction);
		record.put(TransactionEntryFieldNames.LOOKUP_CODE, this.lookupCode);
		record.put(TransactionEntryFieldNames.QUANTITY, this.quantity);
		record.put(TransactionEntryFieldNames.PRICE, this.price);
		
		return record;
	}

	private UUID fromTransaction;
	public UUID getfromTransaction() {
		return this.fromTransaction;
	}
	public TransactionEntryEntity setfromTransaction(UUID fromTransaction) {
		if (!this.fromTransaction.equals(fromTransaction)) {
			this.fromTransaction = fromTransaction;
			this.propertyChanged(TransactionEntryFieldNames.FROM_TRANSACTION);
		}
		
		return this;
	}

	private UUID entryId;
	public UUID getentryId() {
		return this.entryId;
	}
	public TransactionEntryEntity setentryId(UUID entryId) {
		if (!this.entryId.equals(entryId)) {
			this.entryId = entryId;
			this.propertyChanged(TransactionEntryFieldNames.ENTRY_ID);
		}
		
		return this;
	}
	
	private String lookupCode;
	public String getlookupCode() {
		return this.lookupCode;
	}
	public TransactionEntryEntity setlookupCode(String lookupCode) {
		if (!StringUtils.equals(this.lookupCode, lookupCode)) {
			this.lookupCode = lookupCode;
			this.propertyChanged(TransactionEntryFieldNames.LOOKUP_CODE);
		}
		
		return this;
	}
	
	private int quantity;
	public int getQuantity() {
		return this.quantity;
	}
	public TransactionEntryEntity setQuantity(int quantity) {
		if (this.quantity != quantity) {
			this.quantity = quantity;
			this.propertyChanged(TransactionEntryFieldNames.QUANTITY);
		}
		
		return this;
	}

	private float price;
	public float getPrice() {
		return this.price;
	}
	public TransactionEntryEntity setPrice(float price) {
		if (this.price != price) {
			this.price = price;
			this.propertyChanged(TransactionEntryFieldNames.PRICE);
		}
		
		return this;
	}
	
	public TransactionEntry synchronize(TransactionEntry apiTransactionEntry) {
		this.setentryId(apiTransactionEntry.getentryId());
		this.setfromTransaction(apiTransactionEntry.getfromTransaction());
		this.setlookupCode(apiTransactionEntry.getlookupCode());
		this.setQuantity(apiTransactionEntry.getQuantity());
		this.setPrice(apiTransactionEntry.getPrice());
		
		return apiTransactionEntry;
	}
	
	public TransactionEntryEntity() {
		super(new TransactionEntryRepository());
		
		this.entryId = new UUID(0,0);
		this.fromTransaction = new UUID(0,0);
		this.lookupCode = StringUtils.EMPTY;
		this.quantity = 0;
		this.price = 0;
	}
	
	public TransactionEntryEntity(TransactionEntry apiTransactionEntry){
		super(apiTransactionEntry.getentryId(), new TransactionEntryRepository());
		this.entryId = apiTransactionEntry.getentryId();
		this.fromTransaction = apiTransactionEntry.getfromTransaction();
		this.lookupCode = apiTransactionEntry.getlookupCode();
		this.quantity = apiTransactionEntry.getQuantity();
		this.price = apiTransactionEntry.getPrice();
	}
}
