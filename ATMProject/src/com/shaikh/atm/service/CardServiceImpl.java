package com.shaikh.atm.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.shaikh.atm.dao.AccountDao;
import com.shaikh.atm.dao.AccountDaoImpl;
import com.shaikh.atm.dao.CardDao;
import com.shaikh.atm.dao.CardDaoImpl;

import com.shaikh.atm.entity.Account;
import com.shaikh.atm.entity.Card;
import com.shaikh.atm.entity.Transaction;

public class CardServiceImpl implements CardService
{
	private CardDao cardDao = new CardDaoImpl();

	
	
	private double balance;
	private LocalDate date=LocalDate.now();
	private String transactionId;

	@Override
	public Card getDataByCardNo(String cardNo)
	{
		return cardDao.getDataByCardNo(cardNo);
	}

	@Override
	public String updateCardType(String cardNo) 
	{
		return cardDao.updateCardType(cardNo);
	}



	@Override
	public String changePinNo(String cardNo, Integer pinNo) {
		
		return cardDao.changePinNo(cardNo, pinNo);
	}

	@Override
	public String addTransaction(String transactionId,Double amount, LocalDate date, String type,Account account) {
		
		return cardDao.addTransaction(transactionId,amount, date, type,account);
	}
	
	@Override
	public Transaction getDataByTransactionId(String transactionId) {
		
		return cardDao.getDataByTransactionId(transactionId);
	}

	@Override
	public List<Transaction> getMiniStatement(String accountNo) {
		
		return cardDao.getMiniStatement(accountNo);
	}
}
