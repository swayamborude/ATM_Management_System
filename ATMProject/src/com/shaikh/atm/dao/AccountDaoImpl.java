package com.shaikh.atm.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.shaikh.atm.entity.Card;

public class AccountDaoImpl implements AccountDao 
{
	
	private Card card;
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	public AccountDaoImpl() 
	{
		entityManager = MyConnection.getEntityManagerObject();
		entityTransaction = entityManager.getTransaction();
	}

	@Override
	public String updateBalance(String cardNo, Double balance) 
	{
		card = entityManager.find(Card.class, cardNo);
		if (card != null) 
		{
			card.getAccount().setBalance(balance);
			entityTransaction.begin();
			entityManager.persist(card);
			entityTransaction.commit();
			return "\tAMOUNT IS";
		} 
		else 
		{
			return "Limit exceed";
		}
	}
}