package com.project0.bms.dao;

import com.project0.bms.model.Customer;

public interface CustomerDAO {
	public boolean createAccount(Customer customer);
	public boolean login(int customerId,String customerPassword);
	public int viewBalance(int customerId,String customerPassword);
	public boolean withdraw(int customerId,String customerPassword,int withdraw_amount);
	public boolean deposit(int customerId,String customerPassword,int deposit_amount);
	public void depositToAnotherAccount(int senderId,int recieverId, int amount);
	public boolean isUserexists(int customerId);

}
