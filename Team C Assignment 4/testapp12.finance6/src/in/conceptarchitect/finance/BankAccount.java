package in.conceptarchitect.finance;

import java.util.Scanner;

public class BankAccount {
	
	int accountNumber;
	String name;
	String password;
	double balance;
	static double interestRate = 15;
	
	public static double getInterestRate() {
		return interestRate;
	}

	public static void setInterestRate(double interestRate) {
		
		BankAccount.interestRate = interestRate;
	}
	
	
	public BankAccount(int accountNumber, String name, String password, double amount) {
		
		balance=amount; //this is optional here as there is a single balance in the context	
		this.name=name;
		setPassword(password);		
		this.accountNumber=accountNumber;
		
	}
	
	
	
	public int getAccountNumber() {
		return accountNumber;
	}

	//account number can't be changed
	//public void setAccountNumber(int accountNumber) {this.accountNumber = accountNumber;}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public double getBalance() {
		return balance;
	}

	
	
	

	//should there be a getPassword and setPassword
	
//	public String getPassword() {
//		return password;
//	}
	
	public boolean authenticate(String password) {
		return this.password.equals(salt(password));
	}

	void setPassword(String password) {
		
		this.password = salt(password);
	}
	
	
	public void changePassword(String oldPassword, String newPassword) {
		if(authenticate(oldPassword))
			setPassword(newPassword);
	}
	

	

	private String salt(String password) {
		
		String salted="";
		
		for(int i=0;i<password.length();i++) {
			int ch= (int) password.charAt(i);
			salted+=Integer.toHexString(ch);
		}
		return salted;
	}


	
	public void show() {
		System.out.println("account number\t"+accountNumber);
		System.out.println("name         \t"+name);
		System.out.println("password     \t"+password);
		System.out.println("balance      \t"+balance);
		//System.out.println("interest rate\t"+Bank.getInterestRate());
		System.out.println();
		
	}

	public boolean deposit(double amount) {
		// TODO Auto-generated method stub
		if(amount>0) {
			balance+=amount;
			return false;
		} else {
			return true;
		}
	}

	public boolean withdraw(double amount, String password) {
		// TODO Auto-generated method stub
		if(amount<=0)
			return false;
		if (amount>balance) {
			return false;
		} 
		//if (!this.password.equals(password))
		if(!authenticate(password))
			return false;
		else {
			
			
			return true;
		}
	}
	
	
	public void creditInterest(double interestRate) {
		balance+=(balance*interestRate/1200);
	}


}






