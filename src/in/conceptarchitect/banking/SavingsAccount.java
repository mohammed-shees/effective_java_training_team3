package in.conceptarchitect.banking;

import in.conceptarchitect.banking.exceptions.InsufficientBalanceException;
import in.conceptarchitect.banking.exceptions.InvalidCredentialsException;
import in.conceptarchitect.banking.exceptions.InvalidDenominationException;

public class SavingsAccount extends BankAccount {

	public SavingsAccount(String name, String password, double amount) {
		super(name, password, amount);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void withdraw(double amount, String password) throws InsufficientBalanceException, InvalidCredentialsException, InvalidDenominationException {
		// TODO Auto-generated method stub
		
		//write the additional logic that we need
		if(amount> getBalance()-5000)
			throw new InsufficientBalanceException(accountNumber,amount-getBalance()-5000);
		
		
		
		super.withdraw(amount, password); //let super class work
		
	}

}
