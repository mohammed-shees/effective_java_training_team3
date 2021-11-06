package in.conceptarchitect.banking.client;

import in.conceptarchitect.banking.Bank;
import in.conceptarchitect.banking.exceptions.BankingException;
import in.conceptarchitect.banking.exceptions.InsufficientBalanceException;
import in.conceptarchitect.banking.exceptions.InvalidAccountNumberException;
import in.conceptarchitect.banking.exceptions.InvalidCredentialsException;
import in.conceptarchitect.banking.exceptions.InvalidDenominationException;
import in.conceptarchitect.utils.Input;

public class ATM {

	Bank bank;
	Input keyboard = new Input();
	private int accountNumber;

	public void connectTo(Bank bank) {
		// TODO Auto-generated method stub
		this.bank = bank;
	}

	public void start() {

		displayWelcomeScreen();

	}

	private void displayWelcomeScreen() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				accountNumber = keyboard.readInt("accountNumber?");
				if (accountNumber == -999) { // hidden admin menu
					if (keyboard.readString("error:").equals("NIMDA"))
						displayAdminMenu();

				} else
					displayUserMenu();

			}
		} catch (RuntimeException ex) {
			//Ok we are out of our ATM
			System.out.println("ATM is shutdown");
		}
	}

	private void _displayUserMenu() {
		// TODO Auto-generated method stub
		System.out.println("Welcome " + accountNumber);

		int choice = 0;

		do {
			try {
				choice = keyboard.readInt("1. Deposit  2. Widthraw  3. Transfer  4. Show 5. Close Account  0. Exit: ");
				switch (choice) {
				case 1:
					doDeposit();
					break;
				case 2:
					doWithdraw();
					break;
				case 3:
					doTransfer();
					break;
				case 4:
					doShow();
					break;
				case 5:
					doCloseAccount();
					return;

				case 0:
					break;
				default:
					System.out.println("invalid input. retry");

				}

			} catch (InvalidCredentialsException ex) {
				System.out.println("Invalid Credentials for account number " + ex.getAccountNumber());
			} catch (InvalidAccountNumberException ex) {
				System.out.println("Invalid Account Number " + ex.getAccountNumber());
			} catch (InvalidDenominationException ex) {
				System.out.println("Invalid Amount " + ex.getMessage());
			} catch (InsufficientBalanceException ex) {
				System.out.println(
						"Insufficient Balance in account " + ex.getAccountNumber() + ". Deficit:" + ex.getDeficit());
			}

			System.out.println();
		} while (choice != 0);

	}

		private void doCloseAccount() {
		// TODO Auto-generated method stub
		String pin = keyboard.readString("pin?");
		bank.closeAccount(accountNumber, pin);
		printSlip("Your account has been closed");

	}

	private void doShow() {
		// TODO Auto-generated method stub
		String pin = keyboard.readString("pin?");
		String accountInfo = bank.getAccountInfo(accountNumber, pin);
		printSlip(accountInfo);

	}

	private void doTransfer() {
		// TODO Auto-generated method stub
		int amount = keyboard.readInt("Amount?");
		String pin = keyboard.readString("pin?");
		int toAccount = keyboard.readInt("To?");
		bank.transfer(accountNumber, amount, pin, toAccount);
		printSlip(amount + " transferred to " + toAccount);

	}

	private void doWithdraw() {
		// TODO Auto-generated method stub
		int amount = keyboard.readInt("Amount? ");
		String pin = keyboard.readString("pin?");

		if (amount % 100 != 0)
			throw new InvalidDenominationException("Amount Must be a mulitple of 100");

		bank.withdraw(accountNumber, amount, pin);
		dispenseCash(amount); // don't worry. we reach here only when withdraw succeeed

	}

	private void dispenseCash(int amount) {

		System.out.println("Please collect your cash :" + amount);

	}

	private void doDeposit() {
		// TODO Auto-generated method stub
		int amount = keyboard.readInt("Amount? ");
		bank.deposit(accountNumber, amount);
		printSlip("Amount Deposited");
		// no need to handle error here

	}

	private void printSlip(String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
	}

	private void displayAdminMenu() {
		// TODO Auto-generated method stub
		int choice;
		do {
			choice = keyboard.readInt("1. Open Account  2. Credit Interest  3. Print Accounts  4. Shutdown  0. Exit: ");
			switch (choice) {
			case 1:
				doOpenAccount();
				break;
			case 2:
				doCreditInterest();
				break;
			case 3:
				doPrintAccounts();
				break;
			case 4:
				throw new RuntimeException("QUIT SIGNAL");
			case 0:
				break;
			default:
				System.out.println("invalid input. retry");

			}
			System.out.println();
		} while (choice != 0);

	}

	private void doPrintAccounts() {
		// TODO Auto-generated method stub
		bank.printAccountList();
	}

	private void doCreditInterest() {
		// TODO Auto-generated method stub
		bank.creditInterests();
	}

	private void doOpenAccount() {
		// TODO Auto-generated method stub
		String type = keyboard.readString("account type [savings/current/overdraft] ? ");
		String name = keyboard.readString("name?");
		String pin = keyboard.readString("pin?");
		int amount = keyboard.readInt("Initial Balance?");
		int accountNumber = bank.openAccount(type, name, pin, amount);
		printSlip("Your account number is " + accountNumber);

	}

}
