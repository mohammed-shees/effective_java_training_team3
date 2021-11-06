package in.conceptarchitect.banking.Jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.CurrentAccount;
import in.conceptarchitect.banking.OverDraftAccount;
import in.conceptarchitect.banking.SavingsAccount;

public class JdbcAccountRepository implements AccountRepository {


	Properties prop= new Properties();
	String url;
	String user;
	String password;



	Connection connection=null;


	public JdbcAccountRepository() {
		super();
		// TODO Auto-generated constructor stub
		try {
			prop.load(new FileReader("src/config/app.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.url = prop.getProperty("DB_URL");
		this.user = prop.getProperty("DB_USER");
		this.password = prop.getProperty("DB_PASSWORD");
	}

	@Override
	public int addAccount(BankAccount account) {
		try {
			connection = DriverManager.getConnection(url, user, password);
			Statement statement= connection.createStatement();
			ResultSet rs= statement.executeQuery("INSERT INTO bankaccounts"
					+ "(account_type, name, password, balance) VALUES ("+account.getClass().getSimpleName()+","
					+ account.getName()+", "+account.getEncryptedPassword()+", "+account.getBalance());

			while(rs.next()) { //moves to the next result and return if there is a next result. first call takes to first result

				System.out.printf("Account has been added successfully to DB");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return account.getAccountNumber();
	}

	
	@Override
	public void removeAccount(int accountNumber) {
		// TODO Auto-generated method stub
		try {
			connection = DriverManager.getConnection(url, user, password);
			Statement statement= connection.createStatement();
			ResultSet rs= statement.executeQuery("DELETE from bankaccounts where account_number="+accountNumber);

			while(rs.next()) { //moves to the next result and return if there is a next result. first call takes to first result

				System.out.printf("Account has been deleted successfully from DB");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		//return account.getAccountNumber();

	}

	@Override
	public Collection<BankAccount> getAllAccounts() {
		ArrayList<BankAccount> accounts = new ArrayList<>();
		// TODO Auto-generated method stub
		BankAccount account=null;
		String accountType="";
		String name="";
		double balance=0.0;
		try {
			connection = DriverManager.getConnection(url, user, password);
			Statement statement= connection.createStatement();
			ResultSet rs= statement.executeQuery("select * from BankAccounts");
			while(rs.next()) { //moves to the next result and return if there is a next result. first call takes to first result


				accountType= rs.getString("account_type");
				name=rs.getString("name");
				//int accountNumber=rs.getInt("account_number");
				balance=rs.getDouble("balance");

				//System.out.printf("%s %d\t%f\t%s\n", accountType, accountNumber,balance, name);
				switch(accountType) {
				case "SavingsAccount": 
					account = new SavingsAccount(name, accountType, balance);
					account.setAccountNumber(rs.getInt("account_number"));
					break;
				case "CurrentAccount": 
					account = new CurrentAccount(name, accountType, balance);
					account.setAccountNumber(rs.getInt("account_number"));
					break;
				case "OverDraftAccount": 
					account = new OverDraftAccount(name, accountType, balance);
					account.setAccountNumber(rs.getInt("account_number"));
					break;
				default:
					account = new BankAccount(name, accountType, balance);
					account.setAccountNumber(rs.getInt("account_number"));

				}
				accounts.add(account);
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return accounts;
	}

}
