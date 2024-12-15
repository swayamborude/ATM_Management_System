package com.shaikh.atm.entity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;


@Entity
@Data
public class Bank 
{
	@Id
	@Column(length = 20)
	private  String ifscCode; 

	@Column(length = 50)
	private  String bankName;
	
	@Column(length = 20)
	private String branchCode;
	
	@OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
	private List<Customer> customer;
}