package in.conceptarchitect.finance;

public class ATM {
	Bank bank=new Bank(null, 0);
	BankAccount bankAccount=new BankAccount(0, null, null, 0);
	
	public void deposit(int accountNumber,double amount) {

		bank.deposit(accountNumber, amount);
			
	}
	
	public void withdraw(int accountNumber,double amount,String password) {

		bank.withdraw(accountNumber, amount, password);
	}
	
	public void changePassword(String oldPassword,String newPassword) {

		bankAccount.changePassword(oldPassword, newPassword);		
	}
}