package com.project0.bms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.project0.bms.model.Customer;
import com.project0.bms.util.DBConnection;

public class CustomerDAOImplementation implements CustomerDAO{
	private static Logger logger=Logger.getLogger("CustomerDAOImplementation");
	//database connection
	Connection connection=DBConnection.dbConnection();
	//queries for customer
	public final String QUERY_FOR_CREATE_ACCOUNT="insert into bms.customer values(?,?,?,?,?,?)";
	public final String QUERY_FOR_LOGIN="select * from bms.customer where customerId=? and customerPassword=?";
	public final String QUERY_FOR_VIEW_BALANCE="select customerBalance from bms.customer where customerId=? and customerPassword=?";
	public final String QUERY_FOR_WITHDRAW_AMOUNT="update bms.customer set customerBalance=customerBalance-? where customerId=? and customerPassword=?";
	public final String QUERY_FOR_DEPOSIT_AMOUNT="update bms.customer set customerBalance=customerBalance+? where customerId=? and customerPassword=?";
	public final String QUERY_FOR_VALIDATE_CUSTOMER="select * from bms.customer where customerId=?";
	         
	public boolean createAccount(Customer customer) {
		   int rs=0;
		   logger.info("#Account created for :"+" "+customer.getCustomerName());
		try {
			//Adding value to the customer table
			PreparedStatement st=connection.prepareStatement(QUERY_FOR_CREATE_ACCOUNT);
			st.setInt(1,customer.getCustomerId());
			st.setString(2,customer.getCustomerName());
			st.setString(3,customer.getCustomerCity());
			st.setString(4,customer.getCustomerState());
			st.setInt(5,customer.getCustomerBalance());
			st.setString(6,customer.getCustomerPassword());
			
			System.out.println();
		    rs=st.executeUpdate();
		  
		    if(rs==0) //if value not inserted
				return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("there is some error please check it carefully");
		}
		return true;//when value inserted successfully
		
	}
	public boolean login(int customerId, String customerPassword) {
	PreparedStatement statement;
		try {			
			//validating the user
			statement = connection.prepareStatement(QUERY_FOR_LOGIN);
			statement.setInt(1,customerId);
			statement.setString(2,customerPassword);
			
			ResultSet rs=statement.executeQuery();
			
			if(rs.next()) //if customer exist
				return true;
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//if customer not exist
		return false;	
	}
	public int viewBalance(int customerId, String customerPassword) {
		PreparedStatement statement;
		ResultSet rs;
		try {	
			//query for retrieving the balance
			statement = connection.prepareStatement(QUERY_FOR_VIEW_BALANCE);
			statement.setInt(1,customerId);
			statement.setString(2,customerPassword);
			
			rs=statement.executeQuery();
			
			if(rs.next())
				//displays balance when customerID and customerpassword is correct
				return rs.getInt(1);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public boolean withdraw(int customerId, String customerPassword, int withdraw_amount) {
		PreparedStatement statement;
		int rs=0;
		try {
			//validating the user
			statement = connection.prepareStatement(QUERY_FOR_WITHDRAW_AMOUNT);
			statement.setInt(1,withdraw_amount);
			statement.setInt(2,customerId);
			statement.setString(3,customerPassword);
			
			rs=statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(rs==0) //withdraw failed
			return false;
		else 
	        //withdraw successful
			return true;
		
	}
	public boolean deposit(int customerId, String customerPassword, int deposit_amount) {
          PreparedStatement statement;
		
		try {
			//validating the user
			statement = connection.prepareStatement(QUERY_FOR_DEPOSIT_AMOUNT);
			statement.setInt(1,deposit_amount);
			statement.setInt(2,customerId);
			statement.setString(3,customerPassword);
			
			int rs=statement.executeUpdate();
			
			if(rs==0) //deposit failed
				return false;
			else 
				//deposit successful
				return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public void depositToAnotherAccount(int senderId, int recieverId, int amount) {
		try {
			CallableStatement statement=connection.prepareCall("call bms.transaction(?,?,?)");
			statement.setInt(1,senderId);
			statement.setInt(2, recieverId);
			statement.setInt(3, amount);
			
			statement.execute();
	
			System.out.println("Amount  Transferd Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean isUserexists(int customerId) {
		boolean result = false;

		try {
			PreparedStatement statement = connection.prepareStatement(QUERY_FOR_VALIDATE_CUSTOMER);
			statement.setInt(1,customerId);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

}

