package edu.uark.commands.employees;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import edu.uark.commands.ResultCommandInterface;
import edu.uark.models.api.Employee;
import edu.uark.models.api.enums.EmployeeApiRequestStatus;
import edu.uark.models.entities.EmployeeEntity;
import edu.uark.models.repositories.EmployeeRepository;
import edu.uark.models.repositories.interfaces.EmployeeRepositoryInterface;

public class EmployeeSaveCommand implements ResultCommandInterface<Employee> {
	@Override
	public Employee execute() {
		if (StringUtils.isBlank(this.apiEmployee.getFirstName()) || StringUtils.isBlank(this.apiEmployee.getLastName())) {
			(new Employee()).setApiRequestStatus(EmployeeApiRequestStatus.INVALID_INPUT).
				setApiRequestMessage("Name fields may not be empty.");
		}
		
		EmployeeEntity employeeEntity;
		if (StringUtils.isBlank(this.apiEmployee.getEmployeeId())) {
			String newEmployeeId;
			do {
				newEmployeeId = RandomStringUtils.randomNumeric(EMPLOYEE_ID_LENGTH);
			} while (this.employeeRepository.employeeIdExists(newEmployeeId));

			this.apiEmployee.setEmployeeId(newEmployeeId);
			employeeEntity = new EmployeeEntity(this.apiEmployee);
		} else {
			employeeEntity = this.employeeRepository.get(this.apiEmployee.getId());
			if (employeeEntity != null) {
				if (this.apiEmployee.getEmployeeId().equals(employeeEntity.getEmployeeId())) {
					this.apiEmployee = employeeEntity.synchronize(this.apiEmployee);
				} else {
					return (new Employee()).setApiRequestStatus(EmployeeApiRequestStatus.INVALID_EMPLOYEE_ID_CHANGE);
				}
			} else {
				employeeEntity = this.employeeRepository.byEmployeeId(this.apiEmployee.getEmployeeId());
				if (employeeEntity == null) {
					employeeEntity = new EmployeeEntity(this.apiEmployee);
				} else {
					return (new Employee()).setApiRequestStatus(EmployeeApiRequestStatus.EMPLOYEE_ID_ALREADY_EXISTS);
				}
			}
		}
		
		employeeEntity.save();
		if ((new UUID(0, 0)).equals(this.apiEmployee.getId())) {
			this.apiEmployee.setId(employeeEntity.getID());
		}
		
		return this.apiEmployee;
	}
	
	//Properties
	private Employee apiEmployee;
	public Employee getApiEmployee() {
		return this.apiEmployee;
	}
	public EmployeeSaveCommand setApiEmployee(Employee apiEmployee) {
		this.apiEmployee = apiEmployee;
		return this;
	}
	
	private EmployeeRepositoryInterface employeeRepository;
	public EmployeeRepositoryInterface getEmployeeRepository() {
		return this.employeeRepository;
	}
	public EmployeeSaveCommand setEmployeeRepository(EmployeeRepositoryInterface employeeRepository) {
		this.employeeRepository = employeeRepository;
		return this;
	}
	
	private static final int EMPLOYEE_ID_LENGTH = 4;
	
	public EmployeeSaveCommand() {
		this.employeeRepository = new EmployeeRepository();
	}
}
