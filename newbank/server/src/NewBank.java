import java.util.HashMap;

public class NewBank {
	
	private static final NewBank bank = new NewBank();
	private HashMap<String,Customer> customers;

	
	private NewBank() {
		customers = new HashMap<>();
		addTestData();
	}
	
	private void addTestData() {
		Customer bhagy = new Customer("password01");
		bhagy.addAccount(new Account("Main", 1000.0));
		customers.put("Bhagy", bhagy);
		
		Customer christina = new Customer("password02");
		christina.addAccount(new Account("Savings", 1500.0));
		customers.put("Christina", christina);
		
		Customer john = new Customer("password03");
		john.addAccount(new Account("Checking", 250.0));
		customers.put("John", john);
	}
	
	public static NewBank getBank() {
		return bank;
	}
	
	public synchronized Customer checkLogInDetails(String userName) {
		return customers.get(userName);

	}

	public synchronized Boolean checkPassword (Customer c, String pwd) {
		return c.isValidPwd(pwd);
	}

	// commands from the NewBank customer are processed in this method
	public synchronized String processRequest(String username, String request) {
		if(customers.containsKey(username)) {
			switch(request) {
				case "SHOWMYACCOUNTS" : 
					return showMyAccounts(username);
			
				default : 
					return "FAIL";
			}
		}
		return "FAIL";
	}
	
	private String showMyAccounts(String username) {
		return (customers.get(username)).accountsToString();
	}

}
