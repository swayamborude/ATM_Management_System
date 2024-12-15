package com.shaikh.atm.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Transaction 
{
	@Id
	@Column(length = 50)
	private String transactionId;
	
	@Column(length = 20)
	private String transactionType;
	
	private Double amount;
	
	private LocalDate transactionDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="accountNo")
	private Account account;	
}
