package com.project0;

import java.util.Scanner;

public class bankingApp {
	//to call both employeeApp and customerapp
	public void runBankingApp()
	{
		System.out.println("WELCOME TO KAFILUR RAHMAN BANKING APP");
		int choice=0;
		Scanner sc=new Scanner(System.in);
		while (true)
		{
			System.out.println();
			System.out.println("ENTER YOUR CHOICE");
			System.out.println("1. If you are Employee Select 1");
			System.out.println("2. If you are User Select 2");
			choice=sc.nextInt();
			switch(choice)
			{
			case 1:
			{
				//calling employeeApp
				EmployeeApp employeeapp=new EmployeeApp();
				employeeapp.runEmployee();
				break;
			}
			case 2:
			{
				//calling customerApp
				CustomerApp customerApp=new CustomerApp();
		    	customerApp.runCustomer();
		    	break;
			}
			}
		}
	}

}
