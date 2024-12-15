package com.shaikh.atm.service;

import java.time.LocalDate;
import java.util.List;

import com.shaikh.atm.entity.Account;
import com.shaikh.atm.entity.Card;
import com.shaikh.atm.entity.Transaction;

public interface CardService 
{
	 Card getDataByCardNo(String cardNo);
	 
	 String updateCardType(String cardNo);
	 
	 String changePinNo(String cardNo,Integer pinNo);
	 
	 String addTransaction(String transactionId,Double amount,LocalDate date,String type,Account account);
	    
	Transaction getDataByTransactionId(String transactionId);
		  
	List<Transaction> getMiniStatement(String accountNo);
	 
}
