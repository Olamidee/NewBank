public class CustomerID {
	private String key;
	
	//Implements CustomerID class with a specified 'key' (in this case, it
	//would always be the Customers username)
	public CustomerID(String key) {
		this.key = key;
	}
	
	//Returns the Customer ID token
	public String getKey() {
		return key;
	}
}
