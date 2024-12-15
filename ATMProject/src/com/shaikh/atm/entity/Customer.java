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

import org.hibernate.annotations.ManyToAny;

import lombok.Data;

@Entity
@Data
public class Customer
{
	@Id
	private Integer customerId;
	
	@Column(length = 40)
	private String customerName;
	
	@Column(length = 60)
	private String address;
	
	@Column(length = 30)
	private String email;
	
	@Column(length = 12)
	private String mobileNo;
	
	private LocalDate dob;
	
	@Column(length = 12)
	private String adharNo;
	
	@Column(length = 10)
	private String panNo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ifscCode")
	private Bank bank;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private List<Account> account;
}