package com.project0.bms.dao;

import java.util.List;

import com.project0.bms.model.Customer;

public interface EmployeeDAO {
	public boolean login(int employeeId,String password);
	public Customer viewCustomer(int customerId);
	public List<Customer> viewAllCustomer();
	public boolean approveAccount(int customerId);

}
