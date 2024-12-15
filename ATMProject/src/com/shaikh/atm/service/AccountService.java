package com.shaikh.atm.service;

import com.shaikh.atm.entity.Card;
import com.shaikh.atm.entity.Transaction;

public interface AccountService {

	String updateBalance(String cardNo,Double balance);
	
	 Transaction withdrawlBalance(Card card,Double amount);
	 
	 Transaction depositeBalance(Card card,Double amount);
}
