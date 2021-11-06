package in.conceptarchitect.banking.exceptions;

public class InvalidAccountNumberException extends BankingException {

	public InvalidAccountNumberException(int accountNumber) {
		super(accountNumber,"Invalid Account Number");
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountNumberException(int accountNumber, String message) {
		super(accountNumber, message);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountNumberException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountNumberException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountNumberException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
