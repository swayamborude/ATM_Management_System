package com.shaikh.atm.test;


import  com.shaikh.atm.entity.Transaction;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.shaikh.atm.entity.Account;
import com.shaikh.atm.entity.Card;
import com.shaikh.atm.service.CardService;
import com.shaikh.atm.service.CardServiceImpl;
import com.shaikh.atm.validation.MyValidation;

public class TestApp {
   
	public static void main(String[] args) 
	{
		//EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("atmCenter");
		//EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		/*
		System.out.print("\n\t-------------------------------------------------------------------------------------------------");
		System.out.print("\n\t                                       RECEIPT                                                   ");
		System.out.print("\n\t-------------------------------------------------------------------------------------------------");

		
		
		System.out.println("\n\n\t\t\tTransaction Id:"+"\t\t\t\t233456788893445667778");
		System.out.println("\n\t\t\tTransaction Date:"+"\t\t\t2024-06-13");
		System.out.println("\n\t\t\tCard No:"+"\t\t\t\t12345797578");
		System.out.println("\n\t\t\tName:"+"\t\t\t\t\tRutuja Chavan");
		System.out.println("\n\t\t\tTransaction Type:"+"\t\t\tWithdraw");
		System.out.println("\n\t\t\tAmount:"+"\t\t\t\t\t1000");
		System.out.println("\n\t\t\tTotal Balance:"+"\t\t\t\t32456.89");
		
		System.out.print("\n\t-------------------------------------------------------------------------------------------------");
*/
		/*
		System.out.println("\n\t--------------------------------------TRANSACTION DETAILS----------------------------------------");
		System.out.println("\n\t Account No: "+"245678991234"+"\t\t\t\t\t\tName: "+"Kshitija Gurav");
		
		System.out.println("\n\t\t------------------------------------------------------------------------------");
		System.out.println("\t\t| Date     |\t\tReference                  |\tType       | Balance |");
		System.out.print("\t\t------------------------------------------------------------------------------");
		   
		System.out.println("\n\t\t|2024-02-28| 541f7b92-f623-49a2-8588-933abeb05fe3  |  withdraw     |   1000  |");
		System.out.println("\t\t------------------------------------------------------------------------------");
		System.out.println("\n\t\tYour Closing Balance is "+"334467.9");
		*/
		String cardNo="5678486215647532";
		System.out.println("\n\t\t\tCard No:"+"\t\t\t\t"+"***********"+cardNo.substring(12, 16));
		
		
		
	}
	
	
}
