package edu.uark.models.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import edu.uark.dataaccess.entities.BaseEntity;
import edu.uark.models.api.Employee;
import edu.uark.models.entities.fieldnames.EmployeeFieldNames;
import edu.uark.models.enums.EmployeeClassification;
import edu.uark.models.repositories.EmployeeRepository;

public class EmployeeEntity extends BaseEntity<EmployeeEntity> {
	@Override
	protected void fillFromRecord(ResultSet rs) throws SQLException {
		this.active = rs.getBoolean(EmployeeFieldNames.ACTIVE);
		this.password = rs.getString(EmployeeFieldNames.PASSWORD);
		this.lastName = rs.getString(EmployeeFieldNames.LAST_NAME);
		this.firstName = rs.getString(EmployeeFieldNames.FIRST_NAME);
		this.employeeId = rs.getString(EmployeeFieldNames.EMPLOYEE_ID);
		this.managerId = ((UUID) rs.getObject(EmployeeFieldNames.MANAGER_ID));
		this.createdOn = rs.getTimestamp(EmployeeFieldNames.CREATED_ON).toLocalDateTime();
		this.classification = EmployeeClassification.map(rs.getInt(EmployeeFieldNames.CLASSIFICATION));
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		record.put(EmployeeFieldNames.ACTIVE, this.active);
		record.put(EmployeeFieldNames.PASSWORD, this.password);
		record.put(EmployeeFieldNames.LAST_NAME, this.lastName);
		record.put(EmployeeFieldNames.FIRST_NAME, this.firstName);
		record.put(EmployeeFieldNames.MANAGER_ID, this.managerId);
		record.put(EmployeeFieldNames.EMPLOYEE_ID, this.employeeId);
		record.put(EmployeeFieldNames.CREATED_ON, Timestamp.valueOf(this.createdOn));
		record.put(EmployeeFieldNames.CLASSIFICATION, this.classification.getValue());
		
		return record;
	}

	private String employeeId;
	public String getEmployeeId() {
		return this.employeeId;
	}
	public EmployeeEntity setEmployeeId(String employeeId) {
		if (!StringUtils.equals(this.employeeId, employeeId)) {
			this.employeeId = employeeId;
			this.propertyChanged(EmployeeFieldNames.EMPLOYEE_ID);
		}
		
		return this;
	}

	private String firstName;
	public String getFirstName() {
		return this.firstName;
	}
	public EmployeeEntity setFirstName(String firstName) {
		if (!StringUtils.equals(this.firstName, firstName)) {
			this.firstName = firstName;
			this.propertyChanged(EmployeeFieldNames.FIRST_NAME);
		}
		
		return this;
	}

	private String lastName;
	public String getLastName() {
		return this.lastName;
	}
	public EmployeeEntity setLastName(String lastName) {
		if (!StringUtils.equals(this.lastName, lastName)) {
			this.lastName = lastName;
			this.propertyChanged(EmployeeFieldNames.LAST_NAME);
		}
		
		return this;
	}

	private String password;
	public String getPassword() {
		return this.password;
	}
	public EmployeeEntity setPassword(String password) {
		if (!StringUtils.equals(this.password, password)) {
			this.password = password;
			this.propertyChanged(EmployeeFieldNames.PASSWORD);
		}
		
		return this;
	}

	private boolean active;
	public boolean getActive() {
		return this.active;
	}
	public EmployeeEntity setActive(boolean active) {
		if (this.active != active) {
			this.active = active;
			this.propertyChanged(EmployeeFieldNames.ACTIVE);
		}
		
		return this;
	}

	private EmployeeClassification classification;
	public EmployeeClassification getClassification() {
		return this.classification;
	}
	public EmployeeEntity setClassification(EmployeeClassification classification) {
		if (this.classification != classification) {
			this.classification = classification;
			this.propertyChanged(EmployeeFieldNames.CLASSIFICATION);
		}
		
		return this;
	}

	private UUID managerId;
	public UUID getManagerId() {
		return this.managerId;
	}
	public EmployeeEntity setManagerId(UUID managerId) {
		if (!this.managerId.equals(managerId)) {
			this.managerId = managerId;
			this.propertyChanged(EmployeeFieldNames.MANAGER_ID);
		}
		
		return this;
	}
	
	private LocalDateTime createdOn;
	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}
	
	public Employee synchronize(Employee apiEmployee) {
		this.setActive(apiEmployee.getActive());
		this.setLastName(apiEmployee.getLastName());
		this.setFirstName(apiEmployee.getFirstName());
		this.setManagerId(apiEmployee.getManagerId());
		this.setEmployeeId(apiEmployee.getEmployeeId());
		this.setClassification(apiEmployee.getClassification());
		
		apiEmployee.setCreatedOn(this.createdOn);
		
		return apiEmployee;
	}
	
	public EmployeeEntity() {
		super(new EmployeeRepository());
		
		this.active = false;
		this.managerId = new UUID(0, 0);
		this.lastName = StringUtils.EMPTY;
		this.password = StringUtils.EMPTY;
		this.firstName = StringUtils.EMPTY;
		this.employeeId = StringUtils.EMPTY;
		this.createdOn = LocalDateTime.now();
		this.classification = EmployeeClassification.NOT_DEFINED;
	}
	
	public EmployeeEntity(UUID id) {
		super(id, new EmployeeRepository());
		this.id = new UUID(0, 0);
		this.active = false;
		this.managerId = new UUID(0, 0);
		this.lastName = StringUtils.EMPTY;
		this.password = StringUtils.EMPTY;
		this.firstName = StringUtils.EMPTY;
		this.employeeId = StringUtils.EMPTY;
		this.createdOn = LocalDateTime.now();
		this.classification = EmployeeClassification.NOT_DEFINED;
	}

	public EmployeeEntity(Employee apiEmployee) {
		super(apiEmployee.getId(), new EmployeeRepository());
		this.id = apiEmployee.getId();
		this.active = apiEmployee.getActive();
		this.lastName = apiEmployee.getLastName();
		this.password = apiEmployee.getPassword();
		this.firstName = apiEmployee.getFirstName();
		this.managerId = apiEmployee.getManagerId();
		this.employeeId = apiEmployee.getEmployeeId();
		this.classification = apiEmployee.getClassification();

		this.createdOn = LocalDateTime.now();
	}
}
