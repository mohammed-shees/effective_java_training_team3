package in.conceptarchitect.banking;

public class CurrentAccount extends BankAccount {

	public CurrentAccount(String name, String password, double amount) {
		super(name, password, amount);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BankAccount creditInterest(double interestRate) {
		// DO NOTHING. THIS METHOD WILL HIDE THE SUPER CLASS METHOD
		// AND WILL NOT ADD ANY INTEREST
		
		return this;
		
	}
	
	
}
