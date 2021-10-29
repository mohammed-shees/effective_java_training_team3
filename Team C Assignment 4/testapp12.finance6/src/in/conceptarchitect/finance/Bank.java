package in.conceptarchitect.finance;

public class Bank {
	
	String name; //name of the bank
	double interestRate;
	int lastId=0;
		
	public void creditInterst() {
		// interest to all accounts
		for(int i=1;i<=lastId;i++) {
			
			accounts[i].creditInterest(interestRate);
		}
	}
	
	public Bank(String name, double interestRate) {
		super();
		this.name = name;
		this.interestRate = interestRate;
	}
	
	BankAccount [] accounts= new BankAccount[100];
	
	public int openAccount(String name, String password, double amount) {
		int accountNumber= ++ lastId;
		BankAccount account= new BankAccount(accountNumber, name, password,amount);
		accounts[accountNumber] = account; 
		return accountNumber;
	}
	
	private BankAccount getAccountByNumber(int accountNumber) {
		if(accountNumber>0 && accountNumber<=lastId)
			return accounts[accountNumber];
		else
			return null;
	}
	
	public boolean deposit(int accountNumber, double amount) {
		
		BankAccount account = getAccountByNumber(accountNumber);
		
		return account.deposit(amount);
	}

	public boolean transferTo(int accountNumber,double amount,String password,BankAccount target) {
		BankAccount account=getAccountByNumber(accountNumber);
		
		account.withdraw(amount, password);
		
		return  target.deposit(amount);
		
		
	}

	public boolean withdraw(int accountNumber,double amount,String password) {
		BankAccount account=getAccountByNumber(accountNumber);
		
		return account.withdraw(amount, password);
	}
	
	

}
