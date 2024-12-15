package com.shaikh.atm.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Account 
{
	@Id
	private String accountNo;
	
	@Column(length = 20)
	private String accountType;
	
	private Double balance;
	
	@Column(length = 20)
	private String status;
	

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="customerId")
	private Customer customer;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<Card> card;
	
	@OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
	private List<Transaction> transaction;
}
