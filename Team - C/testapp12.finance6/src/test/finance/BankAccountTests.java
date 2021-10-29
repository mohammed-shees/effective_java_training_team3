package test.finance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.finance.BankAccount;

public class BankAccountTests {

	String password="password";
	double amount=30000;
	
	BankAccount account;
	
	@Before
	public void init() {
		account =new BankAccount(1, "Vivek", password, amount);
		System.out.println("@Before called");
	}
	
	@Test public void withdrawShouldFailForInvalidPassword() {
		boolean result=account.withdraw(1, "wrongpassword");
		assertFalse(result);
	}

	@Test
	public void withdrawShouldFailForNegativeAmount() {
		boolean result=account.withdraw(-1, password);		
		assertEquals(false, result);
	}

	@Test 
	public void withdrawShouldPassForValidAmountAndPassword() {
		String password="password";
		double amount=20000;
		BankAccount account=new BankAccount(1,"Vivek",password, amount);
		boolean result=account.withdraw(amount, password);
		assertTrue(result);
		assertEquals(0, account.getBalance(),0.001);
	}

	@Test 
	public void withdrawShouldFailForExcessAmount() {
		boolean result=account.withdraw(amount+1, password);
		assertEquals(false,result);
	}
	
	@Test
	public void depositShoudFailForInvalidAccount() {
		boolean result = acc.deposit(123, 2000);
		assertEquals(false,result);
	}

	@Test
	public void depositShoudFailForInvalidAccountAndInvalidAmount() {
		boolean result = acc.deposit(-121, -500);
		assertEquals(false,result);
	}

	@Test
	public void depositShouldFailForInvalidValue() {
		boolean result =account.deposit(150);
		assertEquals(true,result);
		
	}

	@Test
    	public void transferShouldFailForInvalidfromaccount() {
	       boolean result=account.transfer(0, password);
               assertEquals(false, result);
    }

   	@Test
    	public void transferShouldFailForInvalidtoaccount() {
	       boolean result=account.transfer(-1, password);
	       assertEquals(false, result);
     }

     	@Test  
     	public void transferShouldFailForInvalidAmount() {
	        boolean result=account.transfer(0, password);
	        assertEquals(false,result);
     }
     	@Test  
     	public void transferShouldFailForInvalidPassword() {
	        boolean result=account.transfer(1, "worngpassword");
	        assertFalse(result);
     }
 
     	@Test 
     	public void transferShouldFailForExcessAmount() {
		   boolean result=account.transfer(amount+1, password);
                   assertEquals(false,result);
    }

    	@Test 
    	public void transferShouldPassForAccountHavingValidAmountAndPassword() {
              int accountNumber=1;
              double amount=30000;
              String password="password";
              BankAccount account=new BankAccount(1,"Vivek",password, amount);
              boolean result=account.withdraw(accountNumber,amount, password);
              assertTrue(result);
              assertEquals(0, account.getBalance(),0.001);
       
     }
	
	
}
