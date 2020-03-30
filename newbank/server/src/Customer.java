import java.util.ArrayList;

public class Customer {
	
	private ArrayList<Account> accounts;
	private String password;
	
   //Implements a Customer class
   //Each Customer object would consist of an ArrayList of Accounts owned by the Customer
	public Customer() {
		accounts = new ArrayList<>();
		this.password = "";
	}

	public Customer(String password) {
		accounts = new ArrayList<>();
		this.password = password;
	}

	
	//Converts all Accounts in the ArrayList to String and returns the result
	public String accountsToString() {
		StringBuilder s = new StringBuilder();
		for(Account a : accounts) {
			s.append(a.getDetailedString()+"\n");
		}
		return s.toString();
	}

	//Adds the specified Account to the Customer's ArrayList of Accounts
	public void addAccount(Account account) {
		accounts.add(account);		
	}

	public Boolean isValidPwd (String candidatePassword){
		return (candidatePassword == password);
	}
}
