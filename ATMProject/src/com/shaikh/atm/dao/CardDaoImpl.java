package com.shaikh.atm.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.shaikh.atm.entity.Account;
import com.shaikh.atm.entity.Card;
import com.shaikh.atm.entity.Transaction;

public class CardDaoImpl implements CardDao
{
	private Card card;
	private Transaction transaction;
	private String jpql;
	private Query query;
	Integer count;
	private EntityTransaction entityTransaction;
	private EntityManager entityManager;

	public CardDaoImpl() 
	{
		entityManager = MyConnection.getEntityManagerObject();
		entityTransaction = entityManager.getTransaction();
	}

	@Override
	public Card getDataByCardNo(String cardNo) 
	{
		card = entityManager.find(Card.class, cardNo);
		return card;
	}

	@Override
	public String updateCardType(String cardNo) 
	{
		card = getDataByCardNo(cardNo);
		if (card != null) 
		{
			card.setCardStatus("deactive");
			entityTransaction.begin();
			entityManager.persist(card);
			entityTransaction.commit();
		}
		return "\n\t YOUR CARD IS BLOCKED";
	}

	@Override
	public String changePinNo(String cardNo, Integer pinNo) 
	{
		card = getDataByCardNo(cardNo);
		if (card != null) 
		{
			card.setPinNo(pinNo);
			entityTransaction.begin();
			entityManager.persist(card);
			entityTransaction.commit();
		}
		return "\n\tPin Changed";
	}

	@Override
	public String addTransaction(String transactionId, Double amount, LocalDate date, String type, Account account) 
	{
		Transaction transaction = new Transaction();
		transaction.setTransactionId(transactionId);
		transaction.setAmount(amount);
		transaction.setTransactionDate(date);
		transaction.setTransactionType(type);
		transaction.setAccount(account);
		entityTransaction.begin();
		entityManager.persist(transaction);
		entityTransaction.commit();
		return "Added transaction";
	}

	@Override
	public Transaction getDataByTransactionId(String transactionId) 
	{
		transaction = entityManager.find(Transaction.class, transactionId);
		return transaction;
	}

	@Override
	public List<Transaction> getMiniStatement(String accountNo) 
	{
		String jpql = "select t from Transaction t where t.account.accountNo=?1 order by t.transactionDate desc";
		Query query = entityManager.createQuery(jpql, Transaction.class);
		query.setParameter(1, accountNo);
		query.setMaxResults(10);
		List<Transaction> list = query.getResultList();

		return list;
	}
}