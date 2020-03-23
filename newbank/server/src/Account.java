public class Account {


	//declares string variable called accountName
	private String accountName;
	//declares double variable called opening balance
	private double openingBalance;


	/*
	* defines public method called Account which accepts parameters defined above (accountName/openingBalance)
	* sets local variables (right) to the value of instance variables (left)
	*  */
	public Account(String accountName, double openingBalance) {
		this.accountName = accountName;
		this.openingBalance = openingBalance;
	}

	/*
	Defines a public method called toString which returns a String value of accountName and
	openeingBalance, separated by a semi colon
	 */
	public String toString() {
		return (accountName + ": " + openingBalance);
	}

}
