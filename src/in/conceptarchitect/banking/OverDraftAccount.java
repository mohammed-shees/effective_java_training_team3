package in.conceptarchitect.banking;

import in.conceptarchitect.banking.exceptions.InsufficientBalanceException;
import in.conceptarchitect.banking.exceptions.InvalidCredentialsException;
import in.conceptarchitect.banking.exceptions.InvalidDenominationException;

public class OverDraftAccount extends BankAccount{

	double odLimit;
	public OverDraftAccount(String name, String password, double amount) {
		super(name, password, amount);
		adjustOdLimit();
		// TODO Auto-generated constructor stub
	}
	
	
	
	@Override
	public void withdraw(double amount, String password) throws InvalidCredentialsException, InvalidDenominationException, InsufficientBalanceException {
		// TODO Auto-generated method stub
		authenticate(password);		
			
		checkDenomination(amount);
		
		if(amount> balance+odLimit) 			
			throw new InsufficientBalanceException(accountNumber,amount-balance+odLimit);
			
		balance-=amount;
			
		if(balance<0) {
			double odFee= balance/10;
			balance+=odFee; //odfee is calculated negate
		}
				
	}
	
	
	@Override
	public void deposit(double amount) throws InvalidDenominationException {
		// TODO Auto-generated method stub
		super.deposit(amount);
		adjustOdLimit();
		
	}

	@Override
	public void creditInterest(double interestRate) {
		// TODO Auto-generated method stub
		super.creditInterest(interestRate);
		adjustOdLimit();
	}


	private void adjustOdLimit() {
		double newOdLimit=getBalance()*0.1;
		
		if(odLimit< newOdLimit)
			odLimit=newOdLimit;
	}

	public double getOdLimit() {
		// TODO Auto-generated method stub
		return odLimit;
	}

}
