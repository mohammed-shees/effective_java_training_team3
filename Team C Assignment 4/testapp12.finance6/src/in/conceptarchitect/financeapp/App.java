package in.conceptarchitect.financeapp;

import in.conceptarchitect.finance.ATM;
import in.conceptarchitect.finance.Bank;

public class App {

	public static void main(String[] args) {
		
		Bank bank=new Bank("ABC Bank", 10);
		
		ATM XYZATM=new ATM();
		
		bank.openAccount("Shivani","password", 8000);
			
		XYZATM.withdraw(1, 3000, "password");
		
		XYZATM.changePassword("password", "wordpass");
		
		XYZATM.deposit(10, 8000);
		
		bank.withdraw(10, 100, "password");
	   
		bank.deposit(10,100);
		}

}
