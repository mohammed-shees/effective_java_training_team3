package in.conceptarchitect.banking.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import in.conceptarchitect.banking.CurrentAccount;

public class CurrentAccountTests {

	@Test
	public void creditInterest_doesntCreditInterestToCurrentAccount() {

		double amount=10000;
		CurrentAccount account=new CurrentAccount("","",amount);
		
		account.creditInterest(12);
		
		assertEquals(amount, account.getBalance(),0);  //balance shouldn't change after crediting interest 
				
	}

	
	@Test
	public void withdraw_canWithdrawUptoBalanceUsingRightPassword() {
		fail("TODO: implement the logic here");
	}
	
	@Test
	public void withdraw_failsForInsufficientBalance() {
		fail("TODO: implement the logic here");
	}
	
	@Test
	public void withdraw_failsForWrongPassword() {
		fail("TODO: implement the logic here");
	}
	
	@Test
	public void withdraw_failsForNegativeAmount() {
		fail("TODO: implement the logic here");
	}
}
