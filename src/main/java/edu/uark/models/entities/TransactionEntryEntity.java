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
		this.plu = rs.getString(TransactionEntryFieldNames.PLU);
		this.amount = rs.getInt(TransactionEntryFieldNames.AMOUNT);
		this.soldAt = rs.getFloat(TransactionEntryFieldNames.SOLD_AT);
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		record.put(TransactionEntryFieldNames.ENTRY_ID, this.entryId);
		record.put(TransactionEntryFieldNames.FROM_TRANSACTION, this.fromTransaction);
		record.put(TransactionEntryFieldNames.PLU, this.plu);
		record.put(TransactionEntryFieldNames.AMOUNT, this.amount);
		record.put(TransactionEntryFieldNames.SOLD_AT, this.soldAt);
		
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
	
	private String plu;
	public String getplu() {
		return this.plu;
	}
	public TransactionEntryEntity setplu(String plu) {
		if (!StringUtils.equals(this.plu, plu)) {
			this.plu = plu;
			this.propertyChanged(TransactionEntryFieldNames.PLU);
		}
		
		return this;
	}
	
	private int amount;
	public int getamount() {
		return this.amount;
	}
	public TransactionEntryEntity setamount(int amount) {
		if (this.amount != amount) {
			this.amount = amount;
			this.propertyChanged(TransactionEntryFieldNames.AMOUNT);
		}
		
		return this;
	}

	private float soldAt;
	public float getsoldAt() {
		return this.soldAt;
	}
	public TransactionEntryEntity setsoldAt(float soldAt) {
		if (this.soldAt != soldAt) {
			this.soldAt = soldAt;
			this.propertyChanged(TransactionEntryFieldNames.SOLD_AT);
		}
		
		return this;
	}
	
	public TransactionEntry synchronize(TransactionEntry apiTransactionEntry) {
		this.setentryId(apiTransactionEntry.getentryId());
		this.setfromTransaction(apiTransactionEntry.getfromTransaction());
		this.setplu(apiTransactionEntry.getplu());
		this.setamount(apiTransactionEntry.getamount());
		this.setsoldAt(apiTransactionEntry.getsoldAt());
		
		return apiTransactionEntry;
	}
	
	public TransactionEntryEntity() {
		super(new TransactionEntryRepository());
		
		this.entryId = new UUID(0,0);
		this.fromTransaction = new UUID(0,0);
		this.plu = "";
		this.amount = 0;
		this.soldAt = 0;
	}
	
	public TransactionEntryEntity(TransactionEntry apiTransactionEntry){
		super(apiTransactionEntry.getentryId(), new TransactionEntryRepository());
		
		this.entryId = apiTransactionEntry.getentryId();
		this.fromTransaction = apiTransactionEntry.getfromTransaction();
		this.plu = apiTransactionEntry.getplu();
		this.amount = apiTransactionEntry.getamount();
		this.soldAt = apiTransactionEntry.getsoldAt();
	}
}
