package com.project0.bms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.project0.bms.model.Customer;
import com.project0.bms.util.DBConnection;

public class EmployeeDAOImplementation implements EmployeeDAO{
	private static Logger logger=Logger.getLogger("EmployeeDAOImplementation");
	//database connection
	Connection connection=DBConnection.dbConnection();

	//queries for Employee
	public final String QUERY_FOR_LOGIN="select * from bms.employee where employeeId=? and Password=?";
	public final String QUERY_FOR_view_CUSTOMER_BY_ID="select * from bms.customer where customerId=?";
	public final String QUERY_FOR_VIEW_ALL_CUSTOMER="select * from bms.customer";
	public final String QUERY_FOR_APPROVE_ACCOUNT="update bms.customer set status='Approved' where customerId=?";
	public boolean login(int employeeId, String password) {
		logger.info("you are successfully login"+" "+new  Date());
		PreparedStatement statement;
		try {	
			//validating the user
			statement = connection.prepareStatement(QUERY_FOR_LOGIN);
			statement.setInt(1,employeeId);
			statement.setString(2,password);
			ResultSet rs=statement.executeQuery();
			if(rs.next()) //Employee is valid
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		//employee is invalid
		return false;
	}

	public Customer viewCustomer(int customerId) {
		Customer customer=new Customer();
		PreparedStatement statement;
		try {	
			statement =connection.prepareStatement(QUERY_FOR_view_CUSTOMER_BY_ID);
			statement.setInt(1, customerId);
			ResultSet rs= statement.executeQuery();
			while(rs.next())
			{
				customer.setCustomerId(rs.getInt(1));
				customer.setCustomerName(rs.getString(2));
				customer.setCustomerCity(rs.getString(3));
				customer.setCustomerState(rs.getString(4));
				customer.setCustomerBalance(rs.getInt(5));
				customer.setCustomerPassword(rs.getString(6));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	public List<Customer> viewAllCustomer() {
		List<Customer> customer =new ArrayList<Customer>();
		PreparedStatement statement;
		try {	
			statement =connection.prepareStatement(QUERY_FOR_VIEW_ALL_CUSTOMER);
			ResultSet rs= statement.executeQuery();
			while(rs.next())
			{
				Customer customer1 = new Customer();
				//adding fields to the array
				customer1.setCustomerId(rs.getInt(1));
				customer1.setCustomerName(rs.getString(2));
				customer1.setCustomerCity(rs.getString(3));
				customer1.setCustomerState(rs.getString(4));
				customer1.setCustomerBalance(rs.getInt(5));
				customer1.setCustomerPassword(rs.getString(6));
				//add users to Account users list
				customer.add(customer1);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	public boolean approveAccount(int customerId) {
		PreparedStatement statement;
		try {	
			//validating the user
			statement = connection.prepareStatement(QUERY_FOR_APPROVE_ACCOUNT);
			statement.setInt(1,customerId);
			int rs=statement.executeUpdate();
			if(rs==0) 
				return false;		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
