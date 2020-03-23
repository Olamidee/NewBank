import java.util.ArrayList;

public class Customer {
	
	private ArrayList<Account> accounts;
	
   //Implements a Customer class
   //Each Customer object would consist of an ArrayList of Accounts owned by the Customer
	public Customer() {
		accounts = new ArrayList<>();
	}
	
	//Converts all Accounts in the ArrayList to String and returns the result
	public String accountsToString() {
		String s = "";
		for(Account a : accounts) {
			s += a.toString();
		}
		return s;
	}

	//Adds the specified Account to the Customer's ArrayList of Accounts
	public void addAccount(Account account) {
		accounts.add(account);		
	}
}
