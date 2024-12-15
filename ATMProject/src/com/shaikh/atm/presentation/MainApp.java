package com.shaikh.atm.presentation;

import java.util.Scanner;

import com.shaikh.atm.entity.Card;
import com.shaikh.atm.validation.MyValidation;

public class MainApp 
{
	private static Card card;
	private static App app=new AppImpl();
	
	public static void main(String[] args) 
	{		
		Scanner scanner = new Scanner(System.in);
		card=Login.getLoginObject();	
		while (true) 
		{
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.println("\n\t---------------------WELCOME TO THE ATM SYSTEM-------------------------\n");
			System.out.println("\n\t                      Bank: "+card.getAccount().getCustomer().getBank().getBankName());
			System.out.println("\n\tWelcome: "+card.getAccount().getCustomer().getCustomerName()+"                          Account No: "+ "*********"+card.getAccount().getAccountNo().substring(8,12));
			
			System.out.println("\n\t1:Withdraw                                           \t2:Deposite \n");
			System.out.println("\t3:Mini Statement                                      \t4:Check Balance \n");
			System.out.println("\t5:Change Pin                                           \t6:Exit \n");

			System.out.print("\n\tSelect Option :");
			String choice = scanner.next();
			if(MyValidation.checkSelectOption(choice))
			{
				switch (Integer.parseInt(choice))
				{
				case 1:
					app.withdrawAmount();
					break;
				case 2:
					app.depositeAmount();
					break;
				case 3:
					app.getMiniStatement();
					
					break;
				case 4:
					app.checkBalance();
					break;
				case 5:
					app.changePin();
					break;
				case 6:
					System.out.println("\n\tThanks For Visiting....");
					System.exit(0);
				default:
					System.out.println("\n\tEnter The Valid Option..");
					break;
				}
			}
			else
			{
				System.out.println("\n\tEnter only numeric data");
			}
			
		}
	}
}