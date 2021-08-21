package com.project0;

import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.project0.bms.dao.CustomerDAO;
import com.project0.bms.dao.CustomerDAOImplementation;
import com.project0.bms.model.Customer;

public class CustomerApp {
	private static Logger logger=Logger.getLogger("CustomerApp");
	CustomerDAO customerDAO = new CustomerDAOImplementation();
	Customer customer = new Customer();
	boolean result;
	String customerPassword;

	public void runCustomer()
	{
		logger.info("WELCOME TO CUSTOMER SECTION YOU OPENED IT AT"+" "+new Date());
		int choice=0;
		int choice1=0;
		Scanner sc=new Scanner(System.in);
		while(true)
		{
			System.out.println("1. Create Account");
			System.out.println("2. Login");
	        System.out.println("enter your choice");
			choice=sc.nextInt();
			
			switch(choice)
			{
			case 1:
				{
					System.out.println("PLEASE ENTER DETAIL FOR CREATING ACCOUNT");
					customer=takeInput();
					if(customerDAO.isUserexists(customer.getCustomerId()))
					{
						System.out.println("User with User ID : " + customer.getCustomerId() + " already  exists");
					}
					else {
						result = customerDAO.createAccount(customer);
						if (result)
							System.out.println("Customer Account for : " + customer.getCustomerName() + " is successful");
						else
							System.out.println("Customer Account for : " + customer.getCustomerName()+ "is  Not Done");
					}
				break;
				}
			case 2:
			{
				System.out.println("PLEASE ENTER LOGIN DETAILS");
				System.out.println("enter customer ID:");
				int customerid=sc.nextInt();
				System.out.println("enter password");
				String password=sc.next();
				result=customerDAO.login(customerid, password);
				if(result)
				{
					while(true)
					{
					     System.out.println("WELCOME BACK TO BANKING");
					     System.out.println("Enter your choice");
					     System.out.println("1. View Balance");
					     System.out.println("2. Deposit");
					     System.out.println("3. Withraw");
					     System.out.println("4. Transfer Amount");
					     System.out.println("9. Exit");
					     choice1=sc.nextInt();
					     switch(choice1)
					     {
					         case 1:
					         {
					    	      System.out.println("Enter Password to view balance");
					    	      String customerpassword=sc.next();
								  int balance = customerDAO.viewBalance(customerid,customerpassword);
								  System.out.println("Your total balance is" +" "+ balance);
								  break;
					          }
					          case 2:
					          {
								   System.out.println("Enter Amount to Deposit");
								   int amount= sc.nextInt();
								   System.out.println("enter password");
								   String password2=sc.next();
								   boolean result = customerDAO.deposit(customerid,password2,amount);
								   if(result)
								   {
									   System.out.println("Amount"+" "+amount+" " +"deposited successfully");
								   }
								   else
								    {
									   System.out.println(" please enter correct password");
								     }
								    break;
					           }
					          case 3:
					          {
									System.out.println("Enter Amount to withdraw");
									int amount= sc.nextInt();
									System.out.println("enter password");
									String password1=sc.next();
									boolean result1 = customerDAO.withdraw(customerid,password1,amount);
									if(result1)
									{
										System.out.println("Amount"+ " "+amount+"withrawl successfully");
									}
									else
									{
										System.out.println("please enter correct password");
									}
									break;
					          }
					          case 4:
					          {
									System.out.println("Enter customerID to CREDIT the amount");
									int reciever= sc.nextInt();
									System.out.println("Enter Amount");
									int amount2= sc.nextInt();
									customerDAO.depositToAnotherAccount(customerid,reciever,amount2);
									break;
					          }
					           case 9:
					           {
					    	       System.out.println("thanks for using my banking");
								   System.exit(0);
							        break;
					            }
					     }
					}
				}   
		   else {
					System.out.println("please enter coprrect id or password");
				}
	       }
      }
   }
}
	public Customer takeInput() {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter customerId");
		int customerId = sc.nextInt();
		System.out.println("enter customerName");
		String customerName = sc.next();
		System.out.println("enter password");
		String customerPassword = sc.next();
		System.out.println("enter customere city");
		String customerCity = sc.next();
		System.out.println("enter customer state");
		String customerState = sc.next();
		System.out.println("enter customer balance");
		int customerBalance = sc.nextInt();

		Customer customer = new Customer(customerId, customerName, customerCity, customerState, customerBalance,
				customerPassword);
		return customer;
	}

}
