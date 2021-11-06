package in.conceptarchitect.banking.tests;

import static org.junit.Assert.*;

import in.conceptarchitect.banking.BankAccount;

public class BankingAssertions {

	BankAccount account;

	public BankingAssertions(BankAccount account) {
		this.account = account;
	}
	
	public void assertBalance(double expectedBalance) {		
		assertEquals(expectedBalance, account.getBalance(),0.01);
	}
	
	public void assertBalanceLessThan(double expectedMaxBalance) {
		if(expectedMaxBalance<=account.getBalance())
			fail("expectedBalance:"+expectedMaxBalance+" is not less than actual balance"+account.getBalance());
	}
	

}
