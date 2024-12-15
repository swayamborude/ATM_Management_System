package com.shaikh.atm.service;

import java.time.LocalDate;
import java.util.UUID;

import com.shaikh.atm.dao.AccountDao;
import com.shaikh.atm.dao.AccountDaoImpl;
import com.shaikh.atm.dao.CardDao;
import com.shaikh.atm.dao.CardDaoImpl;

import com.shaikh.atm.entity.Card;
import com.shaikh.atm.entity.Transaction;

public class AccountServiceImpl implements AccountService{

	private AccountDao accountDao=new AccountDaoImpl();
	private CardDao cardDao=new CardDaoImpl();
	private double balance;
	private LocalDate date=LocalDate.now();
	private String transactionId;

	
	@Override
	public String updateBalance(String cardNo, Double balance) {
		
		return accountDao.updateBalance(cardNo, balance);
	}

	@Override
	public Transaction withdrawlBalance(Card card, Double amount) {
		
		if(amount<=card.getAccount().getBalance())
		{
			balance=card.getAccount().getBalance()-amount;
			
			
			System.out.print("\n\t"+amount+" "+accountDao.updateBalance(card.getCardNo(), balance)+" WITHDRAW\n");
			transactionId=UUID.randomUUID().toString();
			cardDao.addTransaction(transactionId, amount, date, "Dr",card.getAccount());
			Transaction transaction=cardDao.getDataByTransactionId(transactionId);
		   return transaction;
	}
	
	return null;
}

	@Override
	public Transaction depositeBalance(Card card, Double amount) {
		balance=card.getAccount().getBalance()+amount;
		System.out.print("\n\t"+amount+" "+accountDao.updateBalance(card.getCardNo(), balance)+" DEPOSITED\n");
		transactionId=UUID.randomUUID().toString();
		cardDao.addTransaction(transactionId, amount, date, "Cr",card.getAccount());
		Transaction transaction=cardDao.getDataByTransactionId(transactionId);
		return transaction;
	}
}
