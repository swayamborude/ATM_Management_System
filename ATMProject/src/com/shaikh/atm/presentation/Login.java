package com.shaikh.atm.presentation;

import java.util.Scanner;

import com.shaikh.atm.entity.Account;
import com.shaikh.atm.entity.Card;
import com.shaikh.atm.entity.Customer;
import com.shaikh.atm.service.CardService;
import com.shaikh.atm.service.CardServiceImpl;
import com.shaikh.atm.validation.MyValidation;

import lombok.Data;

@Data
public class Login 
{
	private static Scanner scanner = new Scanner(System.in);
	private static String cardNo;
	private static String pinNo;
	private static CardService cardService = new CardServiceImpl();
	private static Card card;
	private static Boolean flagCardNo = true;
	private static Boolean flagPinNo = true;
	private static Integer countPin = 0;
  
	public static Card getLoginObject()
	{
		while (flagCardNo) 
		{
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.println("ENTER THE CARD NUMBER :");
			cardNo = scanner.next();
			if (MyValidation.checkCardNoData(cardNo))
			{ 
				if(MyValidation.checkCardNoLength(cardNo))
				{
					card=cardService.getDataByCardNo(cardNo);
					
					if (card != null) 
					{
						  if(MyValidation.checkCardStatus(card.getCardStatus()))
			                 {
								flagCardNo = false;
								while (flagPinNo)
								{
									System.out.println("ENTER PIN NUMBER :");
									pinNo = scanner.next();
									countPin++;
									if (countPin < 3) 
									{
										if (MyValidation.checkPinNoData(pinNo)) 
										{
											if(MyValidation.checkPinNoLength(pinNo))
											{
												if(MyValidation.checkPinNo(pinNo,card.getPinNo()))
												{
													flagPinNo = false;
												}
												else
												{
													System.out.println("Wrong pin number");
												}										
											}
											else
											{
												System.out.println("enter 4 digit pin no");
											}
										} else 
										{
											System.out.println("Pin no must be numeric");
										}
									}
									else 
									{
										String updateMsg = cardService.updateCardType(cardNo);
										System.out.println("Multiple attemp " + updateMsg);
										System.out.println("Contact to your bank");
										System.exit(0);
									}
								}
						}
			                 else
			             	{
			             		System.out.println("YOUR CARD IS BLOCKED YOU CANNOT PERFORM ANY OPERATION THROUGH THE CARD");
			             	     System.exit(0);
			             	}
					
					}
					else
					{
						System.out.println("Card No is not linked with any account.");
					}   
				}else
				{
					System.out.println("Enter 16 digit card no");
				}
				
			} 
			else
			{
				System.out.println("Only numeric values are allowed");
			}
		}	
	return card;
	}
}