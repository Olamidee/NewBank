import jdk.dynalink.beans.StaticClass;

import java.util.HashMap;

import java.util.Scanner;

public class NewBank {
	
	private static final NewBank bank = new NewBank();
	private HashMap<String,Customer> customers;

	
	private NewBank() {
		customers = new HashMap<>();
		addTestData();
	}
	
	private void addTestData() {
		Customer bhagy = new Customer();
		bhagy.addAccount(new Account("Main", 1000.0/*, "password01"*/));
		customers.put("Bhagy", bhagy);
		
		Customer christina = new Customer();
		christina.addAccount(new Account("Savings", 1500.0 /*, "password02"*/));
		customers.put("Christina", christina);
		
		Customer john = new Customer();
		john.addAccount(new Account("Checking", 250.0/*, "password03"*/));
		customers.put("John", john);
	}
	
	public static NewBank getBank() {
		return bank;
	}
	
	public synchronized CustomerID checkLogInDetails(String userName) {
		if(customers.containsKey(userName)) {
			return new CustomerID(userName);
		}
		return null;
	}

	public synchronized CustomerID checkPassword (String password, String username) {
		if (customers.containsKey(username)){
			return new CustomerID(password);
		}
		return null;
	}

	// commands from the NewBank customer are processed in this method
	public synchronized String processRequest(CustomerID customer, String request) {
		if(customers.containsKey(customer.getKey())) {
			switch(request) {
			case "V" : return showMyAccounts(customer);

			case "?" : return " In order to choose an option you must enter its assigned letter.\n The assigned letter is located left of the colon that precedes each option.\n" +
						"\n As an example...\n To choose the option 'View your account' you would enter 'V'\n Enter the assigned letter of your choice: ";

			case "W" : //TODO
			case "D" : //TODO

			default : return "FAIL";
			}
		}
		return "FAIL";
	}
	
	private String showMyAccounts(CustomerID customer) {
		return (customers.get(customer.getKey())).accountsToString();
	}



}
