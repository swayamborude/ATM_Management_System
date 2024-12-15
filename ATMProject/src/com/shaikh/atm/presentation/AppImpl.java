package com.shaikh.atm.presentation;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.Consumer;

import org.hibernate.internal.build.AllowSysOut;

import com.shaikh.atm.entity.Account;
import com.shaikh.atm.entity.Card;
import com.shaikh.atm.entity.Customer;
import com.shaikh.atm.entity.Transaction;
import com.shaikh.atm.service.AccountService;
import com.shaikh.atm.service.AccountServiceImpl;
import com.shaikh.atm.service.CardService;
import com.shaikh.atm.service.CardServiceImpl;

import com.shaikh.atm.validation.MyValidation;

public class AppImpl implements App 
{
	private Card card = Login.getLoginObject();
	private Transaction transaction;
	private Double balance;
	private LocalDate date = LocalDate.now();
	private CardService cardService = new CardServiceImpl();
	private AccountService accountService = new AccountServiceImpl();
	Scanner scanner = new Scanner(System.in);
	private String transactionId;
	private static Double dayLimit = 20000.00;
	private static Boolean flagOldPinNo = true;
	private static Boolean flagNewPinNo = true;
	private static Boolean flagConfirmPinNo = true;

	@Override
	public void withdrawAmount() 
	{
		Boolean flagAmount = true;
		while (flagAmount) 
		{
			System.out.print("\n\tEnter the amount: ");
			Double amount = scanner.nextDouble();
			if (MyValidation.checkAmountMultiple(amount)) 
			{
				if (MyValidation.checkAmountLimit(amount)) 
				{
					flagAmount = false;
					if (MyValidation.checkDayLimit(amount, dayLimit)) 
					{
						dayLimit = dayLimit - amount;
						Transaction transaction = accountService.withdrawlBalance(card, amount);
						if (transaction != null) 
						{
							System.out.print("\n\tDo you want payment redeipt[y/n]:");
							String choice = scanner.next();
							if (choice.equalsIgnoreCase("y")) 
							{
								System.out.print("\n\t-------------------------------------------------------------------------------------------------");
								System.out.print("\n\t                                       RECEIPT                                                   ");
								System.out.print("\n\t-------------------------------------------------------------------------------------------------");
								System.out.println("\n\n\t\t\tTransaction Id:" + "\t\t\t\t" + transaction.getTransactionId());
								System.out.println("\n\t\t\tTransaction Date:" + "\t\t\t" + transaction.getTransactionDate());
								System.out.println("\n\t\t\tCard No:" + "\t\t\t\t" + "***********"+ card.getCardNo().substring(12, 16));
								System.out.println("\n\t\t\tName:" + "\t\t\t\t\t"+ transaction.getAccount().getCustomer().getCustomerName());
								System.out.println("\n\t\t\tTransaction Type:" + "\t\t\t" + transaction.getTransactionType());
								System.out.println("\n\t\t\tAmount:" + "\t\t\t\t\t" + transaction.getAmount());
								System.out.println("\n\t\t\tTotal Balance:" + "\t\t\t\t" + card.getAccount().getBalance());
								System.out.print("\n\t-------------------------------------------------------------------------------------------------");
							}
						} 
						else 
						{
							System.out.println("\n\tInsufficient balance ");
						}
					} 
					else 
					{
						System.out.println("\n\tYour account limit has been exceed");
					}
				} 
				else 
				{
					System.out.println("\n\tYou can withdraw only upto 10000 amount at a one time");
				}
			} else
			{
				System.out.println("\n\tEnter amount in multiple of 100");
			}
		}
	}

	@Override
	public void depositeAmount() 
	{
		Boolean flagDepAmount = true;
		while (flagDepAmount) 
		{
			System.out.print("\n\tEnter the amount: ");
			Double amount = scanner.nextDouble();
			if (MyValidation.checkAmountMultiple(amount)) 
			{
				if (MyValidation.checkAmountLimit(amount)) 
				{
					flagDepAmount = false;
					Transaction transaction = accountService.depositeBalance(card, amount);
					System.out.print("\n\tDo you want payment redeipt[y/n]:");
					String choice = scanner.next();
					if (choice.equalsIgnoreCase("y")) 
					{
						System.out.print("\n\t-------------------------------------------------------------------------------------------------");
						System.out.print("\n\t                                       RECEIPT                                                   ");
						System.out.print("\n\t-------------------------------------------------------------------------------------------------");
						System.out.println("\n\n\t\t\tTransaction Id:" + "\t\t\t\t" + transaction.getTransactionId());
						System.out.println("\n\t\t\tTransaction Date:" + "\t\t\t" + transaction.getTransactionDate());
						System.out.println("\n\t\t\tCard No:" + "\t\t\t\t" + "***********" + card.getCardNo().substring(12, 16));
						System.out.println("\n\t\t\tName:" + "\t\t\t\t\t"+ transaction.getAccount().getCustomer().getCustomerName());
						System.out.println("\n\t\t\tTransaction Type:" + "\t\t\t" + transaction.getTransactionType());
						System.out.println("\n\t\t\tAmount:" + "\t\t\t\t\t" + transaction.getAmount());
						System.out.println("\n\t\t\tTotal Balance:" + "\t\t\t\t" + card.getAccount().getBalance());
						System.out.print("\n\t-------------------------------------------------------------------------------------------------");
					}
				} 
				else
				{
					System.out.println("\n\tYou can deposite only upto 10000 amount at a one time");
				}
			} 
			else
			{
				System.out.println("\n\tEnter amount in multiple of 100");
			}
		}
	}

	@Override
	public void checkBalance()
	{
		System.out.println("\n\tBalance is: " + card.getAccount().getBalance());
	}

	@Override
	public void changePin() 
	{
		flagOldPinNo=true;
		while (flagOldPinNo) 
		{
			System.out.print("\n\tEnter the Old Pin:");
			String oldPin = scanner.next();
			if (MyValidation.checkPinNoData(oldPin)) 
			{
				if (MyValidation.checkPinNoLength(oldPin)) 
				{
					if (MyValidation.checkPinNo(oldPin, card.getPinNo())) 
					{
						flagOldPinNo = false;
						flagNewPinNo=true;
						while (flagNewPinNo) 
						{
							System.out.print("\n\tEnter the New Pin:");
							String newPin = scanner.next();
							if(Integer.parseInt(newPin)==0)
							{
								System.out.println("\n\tPin No does not contains 0's");
							}
							else
							{
								if (newPin.equals(oldPin)) 
								{
									System.out.println("\n\tYour new Pin No can not match with old Pin No.");
								}
								else 
								{
									if (MyValidation.checkPinNoData(newPin)) 
									{
										if (MyValidation.checkPinNoLength(newPin)) 
										{
											flagNewPinNo = false;
											flagConfirmPinNo=true;
											while (flagConfirmPinNo) 
											{
												System.out.print("\n\tEnter Confirm Pin:");
												String confirmPin = scanner.next();
												if (MyValidation.checkPinNoData(confirmPin)) 
												{
													if (MyValidation.checkPinNoLength(confirmPin)) 
													{
														if (confirmPin.equals(newPin)) 
														{
															flagConfirmPinNo = false;
															System.out.println(cardService.changePinNo(card.getCardNo(),
																	Integer.parseInt(newPin)));
														}
														else 
														{
															System.out.println("\n\tNew Pin and Confirm Pin Does Not Match");
														}
													} 
													else 
													{
														System.out.println("\n\tEnter 4 digit pin no");
													}
												} 
												else 
												{
													System.out.println("\n\tPin no must be numeric");
												}
											}
										} 
										else 
										{
											System.out.println("\n\tEnter 4 digit pin no");
										}
									} 
									else
									{
										System.out.println("\n\tPin no must be numeric");
									}
								}
							}
							
						}
					} 
					else
					{
						System.out.println("\n\tEnter Correct Pin Number");
					}
				} 
				else
				{
					System.out.println("\n\tEnter 4 digit pin no");
				}
			} 
			else 
			{
				System.out.println("\n\tPin no must be numeric");
			}
		}
	}

	@Override
	public void getMiniStatement() 
	{

		List<Transaction> miniList = cardService.getMiniStatement(card.getAccount().getAccountNo());
		System.out.println("\n\t--------------------------------------TRANSACTION DETAILS----------------------------------------");
		System.out.println("\n\t Account No: " + "*********"+card.getAccount().getAccountNo().substring(8,12) + "\t\t\t\t\t\tName: "+ card.getAccount().getCustomer().getCustomerName());
		System.out.println("\n\t\t------------------------------------------------------------------------------");
		System.out.println("\t\t| Date     |\t\tReference                  |\tType       | Balance |");
		System.out.print("\t\t------------------------------------------------------------------------------");
		Consumer<Transaction> consumerList = (Transaction transaction) ->
		{
			System.out.println("\n\t\t|" + transaction.getTransactionDate() + "|" + transaction.getTransactionId()+ "   |" + transaction.getTransactionType() + "       | " + transaction.getAmount() + " |");
			System.out.print("\t\t------------------------------------------------------------------------------");
		};
		miniList.forEach(consumerList);
		System.out.println("\n\n\t\tYour Closing Balance is " + card.getAccount().getBalance() + "\n");
	}
}