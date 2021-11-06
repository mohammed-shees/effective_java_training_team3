package in.conceptarchitect.banking;

import in.conceptarchitect.banking.exceptions.InsufficientBalanceException;

public class OverDraftAccount extends BankAccount{

	double odLimit;
	public OverDraftAccount(String name, String password, double amount) {
		super(name, password, amount);
		adjustOdLimit();
		// TODO Auto-generated constructor stub
	}
	
	
	
	@Override
	public BankAccount withdraw(double amount, String password) {
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
				
		return this;
	}
	
	
	@Override
	public BankAccount deposit(double amount) {
		// TODO Auto-generated method stub
		super.deposit(amount);
		adjustOdLimit();
		
		return this;
		
	}

	@Override
	public BankAccount creditInterest(double interestRate) {
		// TODO Auto-generated method stub
		super.creditInterest(interestRate);
		adjustOdLimit();
		return this;
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



	public void setOdLimit(double odLimit) {
		// TODO Auto-generated method stub
		this.odLimit=odLimit;
		
	}

}
