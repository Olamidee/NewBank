import java.util.UUID;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {


	//declares string variable called accountName
	private String accountName;
	//declares double variable called spot
	private double spot;
	//declares a UUID variable that contains a unique identifier for the bank account
	private UUID uniqueId;
	//declare a Date variable containing the time of the creation of hte bank account
	private Date creationDate;

	//declares a list of transactions
	private List<Transaction> transactions;


	/*
	* defines public method called Account which accepts parameters defined above (accountName/spot)
	* sets local variables (right) to the value of instance variables (left)
	*  */
	public Account(String accountName, double openingBalance) {
		this.accountName = accountName;
		this.spot = openingBalance;
		this.uniqueId = UUID.randomUUID();
		this.creationDate = Date.from(Instant.now());
		this.transactions = new ArrayList<Transaction>();
	}

	public void Deposit(Double amount){
		this.ModifyAccount(amount);
	}

	public void Withdraw(Double amount){
		this.ModifyAccount(-amount);
	}

	private void ModifyAccount(Double amount) {
		this.spot += amount;
		this.transactions.add(new Transaction(amount));
	}

	/*
	Defines a public method called toString which returns a String value of accountName and
	openeingBalance, separated by a semi colon
	 */
	public String toString() {
		return (accountName + ": " + spot);
	}

	public String getDetailedString() {
		return (accountName + ", " + creationDate + ", " + uniqueId + ": " + spot);
	}

	public String getUniqueId(){
		return uniqueId.toString();
	}

	public void getHistoryOfTransactions(){
		for (Transaction t : transactions) System.out.println(t.toString());
	}

}
