package com.project0;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.project0.bms.dao.EmployeeDAO;
import com.project0.bms.dao.EmployeeDAOImplementation;
import com.project0.bms.model.Customer;
import com.project0.bms.model.Employee;

public class EmployeeApp {
	private static Logger logger=Logger.getLogger("EmployeeApp");
	EmployeeDAO employeeDAO = new EmployeeDAOImplementation();
	Employee employee = new Employee();
	boolean result;

	public void runEmployee() {
		logger.info("WILCOME TO EMPLOYEE SECTION HERE YOU CAN DO YOUR WORK");
		Scanner sc = new Scanner(System.in);
		int choice1 = 0;
		System.out.println("please enter login details");
		System.out.println();
		System.out.println("enter Employee ID:");
		int employeeid = sc.nextInt();
		System.out.println("enter password");
		String password = sc.next();
		result = employeeDAO.login(employeeid, password);
		if (result) {
			while (true) {
				System.out.println("WELCOME BACK TO BANKING");
				System.out.println("Enter your choice");
				System.out.println("1. View customers");
				System.out.println("2. View customer BY ID");
				System.out.println("3. Validate Customer");
				System.out.println("9. Logout");
				choice1 = sc.nextInt();
				switch (choice1) {
				case 1: {
					List<Customer> customer;
					customer = employeeDAO.viewAllCustomer();
					System.out.println("All Customer Details Are");
					Iterator<Customer> iter = customer.iterator();
					while (iter.hasNext()) {
						System.out.println(iter.next());
					}

					break;
				}
				case 2: {
					System.out.println("Enter CustomerID");
					int customerid = sc.nextInt();
					Customer employee1 = employeeDAO.viewCustomer(customerid);
					System.out.println(employee1);
					break;

				}
				case 3: {
					System.out.println("Enter customer ID to validate");
					int customerid1 = sc.nextInt();
					boolean result = employeeDAO.approveAccount(customerid1);
					if (result) {
						System.out.println("Customer Approved Successfully");
					} else
						System.out.println("Approval fail");
					break;
				}
				case 9: {
					System.out.println("Loggedout Successfully");
					System.exit(0);
					break;
				}
				}

			}
		} else {
			System.out.println("invalid credential");
		}
	}
}