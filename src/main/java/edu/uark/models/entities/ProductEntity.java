package edu.uark.models.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import edu.uark.dataaccess.entities.BaseEntity;
import edu.uark.models.api.Product;
import edu.uark.models.entities.fieldnames.ProductFieldNames;
import edu.uark.models.repositories.ProductRepository;

public class ProductEntity extends BaseEntity<ProductEntity> {
	@Override
	protected void fillFromRecord(ResultSet rs) throws SQLException {
		this.id = rs.getObject(ProductFieldNames.ID);
		this.lookupCode = rs.getString(ProductFieldNames.LOOKUP_CODE);
		this.quantity = rs.getInt(ProductFieldNames.QUANTITY);
		this.price = rs.getDouble(ProductFieldNames.PRICE);
		this.active = rs.getBoolean(ProductFieldNames.ACTIVE);
		this.createdOn = rs.getTimestamp(ProductFieldNames.CREATED_ON).toLocalDateTime();
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		record.put(ProductFieldNames.ID, this.id);
		record.put(ProductFieldNames.LOOKUP_CODE, this.lookupCode);
		record.put(ProductFieldNames.QUANTITY, this.quantity);
		record.put(ProductFieldNames.PRICE, this.price);
		record.put(ProductFieldNames.ACTIVE, this.active);
		record.put(ProductFieldNames.CREATED_ON, Timestamp.valueOf(this.createdOn));
		
		return record;
	}

	private UUID id;
	public UUID getID() {
		return this.id;
	}
	public ProductEntity setID(UUID id) {
		if (this.id.compareTo(id) == 0) {
			this.id = id;
			this.propertyChanged(ProductFieldNames.ID);
		}
		
		return this;
	}
	
	private String lookupCode;
	public String getLookupCode() {
		return this.lookupCode;
	}
	public ProductEntity setLookupCode(String lookupCode) {
		if (!StringUtils.equals(this.lookupCode, lookupCode)) {
			this.lookupCode = lookupCode;
			this.propertyChanged(ProductFieldNames.LOOKUP_CODE);
		}
		
		return this;
	}

	private int quantity;
	public int getQuantity() {
		return this.quantity;
	}
	public ProductEntity setQuantity(int quantity) {
		if (this.quantity != quantity) {
			this.quantity = quantity;
			this.propertyChanged(ProductFieldNames.QUANTITY);
		}
		
		return this;
	}
	
	private double price;
	public double getPrice() {
		return this.price;
	}
	public ProductEntity setPrice(int price) {
		if (this.price != price) {
			this.price = price;
			this.propertyChanged(ProductFieldNames.PRICE);
		}
		
		return this;
	}
	
	private boolean active;
	public boolean getActive() {
		return this.active;
	}
	public ProductEntity setActive(boolean active) {
		if (this.active != active) {
			this.active = active;
			this.propertyChanged(ProductFieldNames.ACTIVE);
		}
		
		return this;
	}

	private LocalDateTime createdOn;
	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}
	public ProductEntity setCreatedOn(LocalDateTime createdOn) {
		if (this.createdOn != createdOn) {
			this.createdOn = createdOn;
			this.propertyChanged(ProductFieldNames.CREATED_ON);
		}
		
		return this;
	}

	public Product synchronize(Product apiProduct) {
		this.setQuantity(apiProduct.getQuantity());
		this.setLookupCode(apiProduct.getLookupCode());
		
		apiProduct.setCreatedOn(this.createdOn);
		
		return apiProduct;
	}
	
	public ProductEntity() {
		super(new ProductRepository());
		
		this.quantity = 0;
		this.price = 0.00;
		this.active = false;
		this.lookupCode = StringUtils.EMPTY;
		this.createdOn = LocalDateTime.now();
	}
	
	public ProductEntity(UUID id) {
		super(id, new ProductRepository());
		
		this.quantity = 0;
		this.price = 0.00;
		this.active = false;
		this.lookupCode = StringUtils.EMPTY;
		this.createdOn = LocalDateTime.now();
	}

	public ProductEntity(Product apiProduct) {
		super(apiProduct.getId(), new ProductRepository());
		
		this.quantity = apiProduct.getQuantity();
		this.price = apiProduct.getPrice();
		this.active = apiProduct.getActive();
		this.lookupCode = apiProduct.getLookupCode();

		this.createdOn = LocalDateTime.now();
	}
}
