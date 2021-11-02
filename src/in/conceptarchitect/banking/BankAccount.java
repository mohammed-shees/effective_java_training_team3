package in.conceptarchitect.banking;

import in.conceptarchitect.banking.exceptions.InsufficientBalanceException;
import in.conceptarchitect.banking.exceptions.InvalidCredentialsException;
import in.conceptarchitect.banking.exceptions.InvalidDenominationException;
import in.conceptarchitect.app.Input;

public class BankAccount {
	
	
	int accountNumber;
	private String name;
	private String password;
	protected double balance;
	
		
	public  BankAccount( String name, String password, double amount) {
		
		//this.accountNumber= ++accountCount; //will be supplied by the bank
		this.name=name;  
		this.password=salt(password); 		
		
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
	
	public void authenticate(String password) throws InvalidCredentialsException {		
		
		if (!salt(password).equals(this.password))
			throw new InvalidCredentialsException(accountNumber);	
		
		//if I reach here. then authentication was successful
	}
	
	public void changePassword(String oldPassword, String newPassword) throws InvalidCredentialsException {
		
		authenticate(oldPassword);//throws exception if authentication fails

		password=salt(newPassword);//If I reach here, authentication was successful
		
		
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
	
	public void checkDenomination(double amount) throws InvalidDenominationException {
		if(amount<=0)
			throw new InvalidDenominationException(accountNumber,"Amount must be a positive value");
			
	}
	
	public void deposit(double amount) throws InvalidDenominationException {
		
		checkDenomination(amount);
		balance+=amount;
		
	}

	
	public void withdraw(double amount, String password) throws InvalidCredentialsException, InvalidDenominationException, InsufficientBalanceException {
		// TODO Auto-generated method stub
		
		authenticate(password);
		checkDenomination(amount);		
		
		if(amount> balance)			
			throw new InsufficientBalanceException(accountNumber, amount-balance);
			
			
		balance-=amount;	
		
	}

	

	public void creditInterest(double interestRate) {
		// TODO Auto-generated method stub
		balance+=(balance*interestRate)/1200; //one month interest at a time.
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s %d\t%f\t%s", this.getClass().getSimpleName(), accountNumber,balance,name);
	}
	
	
	
}
