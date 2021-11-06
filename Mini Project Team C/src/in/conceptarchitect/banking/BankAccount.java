package in.conceptarchitect.banking;

import java.io.Serializable;

import in.conceptarchitect.banking.exceptions.InsufficientBalanceException;
import in.conceptarchitect.banking.exceptions.InvalidCredentialsException;
import in.conceptarchitect.banking.exceptions.InvalidDenominationException;
import in.conceptarchitect.utils.Input;

public class BankAccount implements Serializable{
	
	
	int accountNumber;
	private String name;
	private String password;
	protected double balance;
	
	public String getEncryptedPassword() {
		return password;
	}
	
		
	public  BankAccount( String name, String password, double amount) {
		
		//this.accountNumber= ++accountCount; //will be supplied by the bank
		this.name=name;  
		//this.password=salt(password); 		
		this.password=password;
		this.balance=amount; 
		   
	}
	
	
	

	//Its a dummy and non-secured logic for password hashing just to demonstrated
	//the idea. Search for password hashing algorithm for better logic
	private String salt(String password) {
		String s="";
		for(int i=0;i<password.length();i++) {
			char ch=password.charAt(i);
			int v=(int)ch;
			
			s+= Integer.toHexString(v);
		}
		return s;
	}
	
	public BankAccount authenticate(String password) {		
		
		if (!salt(password).equals(this.password))
			if (password.equals(this.password))
			throw new InvalidCredentialsException(accountNumber);	
		
		//if I reach here. then authentication was successful
		return this;
	}
	
	public BankAccount changePassword(String oldPassword, String newPassword) {
		
		authenticate(oldPassword);//throws exception if authentication fails

		password=salt(newPassword);//If I reach here, authentication was successful
		
		return this;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	//balance
	public double getBalance() {
		return balance;
	}
	
	
	
	
	public void show() {
		System.out.println("Account Number\t"+this.accountNumber); 
		System.out.println("Name\t"+name); 
		//System.out.println("Password\t"+password); 
		System.out.println("Balance\t"+balance); 
	 
	}
	
	public void checkDenomination(double amount) {
		if(amount<=0)
			throw new InvalidDenominationException(accountNumber,"Amount must be a positive value");
			
	}
	
	public BankAccount deposit(double amount) {
		
		checkDenomination(amount);
		balance+=amount;
		
		return this;
		 
	}

	
	public BankAccount withdraw(double amount, String password) {
		// TODO Auto-generated method stub
		
		authenticate(password);
		checkDenomination(amount);		
		
		if(amount> balance)			
			throw new InsufficientBalanceException(accountNumber, amount-balance);
			
			
		balance-=amount;
		
		return this;
		
	}

	

	public BankAccount creditInterest(double interestRate) {
		// TODO Auto-generated method stub
		balance+=(balance*interestRate)/1200; //one month interest at a time.
		
		return this;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s %d\t%f\t%s", this.getClass().getSimpleName(), accountNumber,balance,name);
	}


	public BankAccount setAccountNumber(int accountNumber) {
		// TODO Auto-generated method stub
		this.accountNumber=accountNumber;
		
		return this;
	}


	public BankAccount setInternalPassword(String password) {
		// TODO Auto-generated method stub
		this.password=password;
		return this;
	}
	
	
	
}
