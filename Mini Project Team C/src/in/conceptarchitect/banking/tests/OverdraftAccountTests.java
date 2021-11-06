package in.conceptarchitect.banking.tests;

import static in.conceptarchitect.utils.CustomAsserts.assertLessThan;
import static in.conceptarchitect.utils.CustomAsserts.assertType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.OverDraftAccount;

public class OverdraftAccountTests {

	String password="pass";
	double balance=10000;
	OverDraftAccount account;
	BankingAssertions assertions;
	
	@Before
	public void init() {
		account=new OverDraftAccount("some name",password, balance);
		
		assertions=new BankingAssertions(account);
		
	}
	
	@Test
	public void account_isATypeOfSavingAccount() {
		assertType(BankAccount.class, account);
		
	}
	
	private void assertOdLimit(double expectedOdLimit) {
		assertEquals(expectedOdLimit, account.getOdLimit(),0.01);
	}
	
	
	@Test
	public void account_hasOdLimitAs10PercentOfInitialDeposit() {
		
		double expectedOdLimit= balance*0.1;
		
		
		//assertEquals(expectedOdLimit,account.getOdLimit(),0.01);
		
		assertOdLimit(expectedOdLimit);
		
		
		
	}
	
	//@Ignore 
	@Test
	public void odLimit_increasesOdLimitIfBalanceIncreasesToHistoricHightestValue() {
		double extra=10000;
		account.deposit(extra); //now balance becomes balance+extra
		
		double newOdLimit=(extra+balance)*0.1;
		
		assertOdLimit(newOdLimit);
	}
	
	//@Ignore
	@Test
	public void odLimit_doesntChangeOnWithdrawal() {
		
		double odLimitBeforeWithdrawal=account.getOdLimit();
		
		account.withdraw(5000, password);
		
		
		assertOdLimit(odLimitBeforeWithdrawal);
	}
	
	
	@Test
	public void odLimit_canIncreaseIfCreditInterestIncreasesAccountBalanceUptoHistoricMax() {
		
		account.creditInterest(12);
		double newBalance= account.getBalance();
		double expectedOdLimit=newBalance*0.1;
		
		assertOdLimit(expectedOdLimit);
		
	}
	
	//@Ignore
	@Test
	public void odLimit_doesntChangeIfNewBalanceIsLessThanHistoricMaxBalance() {
		//arrange
		double historicOdLimit=account.getOdLimit(); //this was odLimt at hightest point
				
		account.withdraw(2000, password); //lets withdraw some money
		
		
		//now add money less that withdrawal
		account.deposit(1000); 
		
		//my currentBalance < historicBalance
		
		//but my odLimit should be based on highestHistoricBalance
		assertOdLimit(historicOdLimit);
		
	}
	

	//@Ignore
	@Test
	public void withdraw_canWithdrawUptoBalancePlusOdLimit() {
		double odLimit=account.getOdLimit();
		double maxAmount=balance+odLimit;
		
		account.withdraw(maxAmount, password);
	}

	//@Ignore
	@Test
	public void withdraw_canPushMyBalanceToNegative() {
		
		double odLimit=account.getOdLimit();
		
		account.withdraw(balance+odLimit/2, password);
		
		assertions.assertBalanceLessThan(0);
		
		
	}
	
	
	
}
