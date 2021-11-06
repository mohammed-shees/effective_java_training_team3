package in.conceptarchitect.banking;

import in.conceptarchitect.banking.exceptions.InsufficientBalanceException;

public class SavingsAccount extends BankAccount {

	public SavingsAccount(String name, String password, double amount) {
		super(name, password, amount);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public BankAccount withdraw(double amount, String password) {
		// TODO Auto-generated method stub
		
		//write the additional logic that we need
		if(amount> getBalance()-5000)
			throw new InsufficientBalanceException(accountNumber,amount-getBalance()-5000);
		
		
		
		super.withdraw(amount, password); //let super class work
		return this;
		
	}

}
