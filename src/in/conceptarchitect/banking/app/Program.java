package in.conceptarchitect.banking.app;

import in.conceptarchitect.banking.Bank;
import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.user.ATM;
import in.conceptarchitect.app.Input;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Bank icici=new Bank("ICICI", 12); //bank will have bank accounts
		
		seedDummyData(icici); //add some dummy data for test
		
		runApp(icici);
		
		
	

	}

		

	private static void runApp(Bank icici) {
		ATM atm=new ATM(); //should be connected to some bank
			
		atm.connectTo(icici); //assoicate the hardware with the Bank		
		
		atm.start();  //switch on the ATML machine
	}

	private static void seedDummyData(Bank bank) {
		// TODO Auto-generated method stub		
		bank.openAccount("savings","Vivek", "1111", 50000);
		bank.openAccount("current","Sanjay", "2222", 50000);
		bank.openAccount("overdraft","Shivanshi", "3333", 50000);
		
	}
}
