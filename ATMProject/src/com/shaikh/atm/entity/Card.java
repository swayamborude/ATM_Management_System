package com.shaikh.atm.entity;

import java.time.LocalDate;
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
public class Card 
{
	@Id
	@Column(length = 16)
	private String cardNo;
	
	@Column(length = 20)
	private String cardType;
	
	private LocalDate issueDate;
	
	private LocalDate expireDate;
	
	private Integer cvvNo;
	
	private Integer pinNo;
	
	@Column(length = 20)
	private String cardStatus;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="accountNo")
	private Account account;
}