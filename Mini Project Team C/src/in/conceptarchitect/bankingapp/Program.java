package in.conceptarchitect.bankingapp;

import in.conceptarchitect.banking.Bank;
import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.Machine.ATM;

import in.conceptarchitect.utils.Input;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//ArrayListAccountRepository accounts=new ArrayListAccountRepository();
		//CsvAccountRepository accounts=new CsvAccountRepository("data/accounts.csv");
		//BinaryAccountRepository accounts= new BinaryAccountRepository("data/accounts.db");
		JdbcAccountRepository accounts = new JdbcAccountRepository();
		
		Bank icici=new Bank("ICICI", 12,accounts); //bank will have bank accounts
		
		//seedDummyData(icici); //add some dummy data for test
		
		runApp(icici);

	}

		

	private static void runApp(Bank icici) {
		ATM atm=new ATM(); //should be connected to some bank
			
		atm.connectTo(icici); //assoicate the hardware with the Bank		
		
		atm.start();  //switch on the ATML machine
	}

	private static void seedDummyData(Bank bank) {
		// TODO Auto-generated method stub		
		bank.openAccount("savings","Sivani", "1111", 50000);
		bank.openAccount("current","Prince", "2222", 50000);
		bank.openAccount("overdraft","Shees", "3333", 50000);
		
	}
}
