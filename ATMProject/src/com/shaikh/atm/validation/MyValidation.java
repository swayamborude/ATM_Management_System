package com.shaikh.atm.validation;

import com.shaikh.atm.entity.Card;
import com.shaikh.atm.presentation.Login;
import com.shaikh.atm.service.CardService;
import com.shaikh.atm.service.CardServiceImpl;

public class MyValidation 
{	
	public static Boolean checkCardNoLength(String cardNo)
	{
		return cardNo.length()==16;
	}
	public static Boolean checkCardNoData(String cardNo)
	{
		return cardNo.matches("\\d+");
	}
	public static Boolean checkPinNoLength(String pinNo)
	{
		return pinNo.length()==4;
	}
	public static Boolean checkPinNoData(String pinNo)
	{   
		return pinNo.matches("\\d+");
	}
	public static Boolean checkPinNo(String pinNo,Integer pin)
	{
		return pinNo.equals(pin.toString());
	}
	public static Boolean checkCardStatus(String cardStatus)
	{
		return cardStatus.equals("active");
	}
	public static Boolean checkSelectOption(String choice)
	{
		return choice.toString().matches("\\d+");
	}
	public static Boolean checkAmountMultiple(Double amount)
	{
		return amount%100==0;
	}
	public static Boolean checkAmountLimit(Double amount)
	{
		return amount<=10000.00;
	}
	public static Boolean checkDayLimit(Double amount,Double dayLimit)
	{
		return amount<=dayLimit;
	}
}