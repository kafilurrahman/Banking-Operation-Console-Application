package com.project0.bms.model;

public class Customer {
	private int customerId;
	private String customerName;
	private String customerCity;
	private String customerState;
	private int customerBalance;
	private String customerPassword;

	public Customer() {
		super();
	}

	public Customer(int customerId, String customerName, String customerCity, String customerState,
			int customerBalance, String customerPassword) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerCity = customerCity;
		this.customerState = customerState;
		this.customerBalance = customerBalance;
		this.customerPassword = customerPassword;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerState() {
		return customerState;
	}

	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}

	public int getCustomerBalance() {
		return customerBalance;
	}

	public void setCustomerBalance(int customerBalance) {
		this.customerBalance = customerBalance;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerCity="
				+ customerCity + ", customerState=" + customerState + ", customerBalance=" + customerBalance + ", customerPassword=" + customerPassword + "]";
	}
	
}
