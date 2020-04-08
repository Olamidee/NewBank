import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NewBankClientHandler extends Thread{
	
	private NewBank bank;
	private BufferedReader in;
	private PrintWriter out;
	Admin a = new Admin();

	private void adminLogin (String input) {

		try {

			if (input.equals("ADMIN")) {

				out.println("welcome to Admin log in...");
				out.println("Please enter ADMIN password...");

				String adminPass = in.readLine();
				if (a.adminPasswordCheck(adminPass)) {
					out.println("ADMIN log in successful...");
					out.println("please enter the number of the option you would like to select");

					out.println("1:  Enter new user ");
					out.println("2:  adjust new user details");
					out.println("3:  delete user details");
					out.println("4:  Logout");

					String adminInput = in.readLine();

					switch (adminInput) {
						case "1":
							adminCaseOne();
							break;
						case "2":
							//change user details method
							adminCaseTwo();
							break;
						case "3":
							//delete user method
							 adminCaseThree();
							break;
						case "4":
							out.println("ADMIN log out successful..");
							run();
							break;

						default:
							out.println("default");
					}


				}


			} else if (a.userNameCheck(input)){

				out.println("welcome " + input + " please enter your password");
				String pWord = in.readLine();
				if (a.customerPasswordCheck(input, pWord)) {
					out.println("you have successfully logged in what would you like to do");

					out.println("1:  logout");
					out.println("2:  transfer money to another account");
					out.println("3:  loan to another customer");
					String whatToDo = in.readLine();

					switch (whatToDo) {
						case "1":
							run();
							break;
						case "2":
							out.println("method to transfer money to another account");
							break;
						case "3":
							out.println("method to loan amount to another account");
							break;
						default:
							out.println("please select a valid option");
					}

				}else {
					out.println("that password does not match the one we have for you, please try again or answer a security");
					out.println("1: try again");
					out.println("2:  answer security question");
					String caseInput = in.readLine();

					switch (caseInput) {
						case "1":
							run();
							break;
						case "2":
							out.println("what country do you live in?");
							String country = in.readLine();

							if (a.securityQCheck(input, country)) {
								out.println("your details are as follows...");
								printDB();
							}
							run();
							break;
						default:
							out.println("please enter a valid input");
							run();

					}

				}


			} else {
				out.println("we do not have that username in our database, please try again");
				run();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	String usernameToUpdate;

	private void adminCaseOne() {

		try {
			out.println("you have selected 1 to enter a new user");
			out.println("enter a new username...");
			String newUsernameInput = in.readLine();

			out.println("enter a new password..");
			String newUserPassword = in.readLine();

			out.println("enter a new security question");
			String newUserSecurityQuestion = in.readLine();

			a.adminEnterNewUser(newUsernameInput, newUserPassword, newUserSecurityQuestion);

			out.println("new user has now been entered ");

		}catch (IOException e){
			e.printStackTrace();
		}

	}



	private void adminCaseTwo() {

		try{
			out.println("please enter the username of the user you would like to update..");
			usernameToUpdate = in.readLine();

			String[] arr = a.displayCurrentDB(usernameToUpdate);

			for (int i = 0; i <= arr.length-1; i++) {
				out.println(arr[i]);

			}

			out.println("");

			adminCaseTwoa();


		}catch (IOException e){
			e.printStackTrace();
		}

	}


	private void adminCaseTwoa() {

		try {
			out.println("what would you like to change...");
			out.println("1:  Username");
			out.println("2:  Password");
			out.println("3:  Security question answer");
			String caseTwoInput = in.readLine();
			switch (caseTwoInput) {
				case "1":
					//update username method
					out.println("what would you like to change " + usernameToUpdate + " to...");
					String usernameUpdate = in.readLine();
					a.changeUserName(usernameToUpdate, usernameUpdate);

					printDB();
					break;
				case "2":
					//update password method
					out.println("what would you like to change " + usernameToUpdate +"'s password to...");
					String passwordUpdate = in.readLine();
					a.changePassword(usernameToUpdate, passwordUpdate);

					printDB();
					break;
				case "3":
					//update security question answer
					out.println("what would you like to change " + usernameToUpdate + "'s security question answer to...");
					String securityQAnswer = in.readLine();
					a.changeSecurityAnswer(usernameToUpdate, securityQAnswer);

					printDB();
					break;
				default:
					out.println("invalid input,, please try again...");
					adminCaseTwoa();

			}
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	private void adminCaseThree() {

		try {

			out.println("Please enter the name of the user you want to delete...");
			String username = in.readLine();
			out.println(" are you sure you want to delete " + username + "from the new bank system?");
			out.println("1:  Yes");
			out.println("2:  No");
			String confirm = in.readLine();

			switch (confirm) {
				case "1":
					a.deleteUser(username);
					break;
				case "2":
					adminCaseTwoa();
					break;
				default:
					out.println("please enter a valid option..");
					adminCaseTwoa();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void printDB () {
		String[] arr = a.displayCurrentDB(usernameToUpdate);

		for (int i = 0; i <= arr.length-1; i++) {
			out.println(arr[i]);
		}
	}
	
	
	public NewBankClientHandler(Socket s) throws IOException {
		bank = NewBank.getBank();
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		out = new PrintWriter(s.getOutputStream(), true);
	}
	
	public void run() {




		// keep getting requests from the client and processing them
		try {


			// ask for user name
			out.println(" welcome to NewBank please enter your username");


			String userName = in.readLine();


			adminLogin(userName);



			CustomerID customer = bank.checkLogInDetails(userName);
			if (customer != null) {
				out.println("welcome " + userName);

			}else {
				out.println("we cannot find that username, please try again");
				out.println("");
				run();
			}

			// ask for password
			out.println("Please enter your Password");
			String password = in.readLine();
			out.println("Checking Details...");
			// authenticate user and get customer ID token from bank for use in subsequent requests

			CustomerID passWord = bank.checkPassword(password, userName);
			// if the user is authenticated then get requests from the user and process them 
			if(passWord != null) {
				out.println("Log In Successful.");
				out.println("V:  VIEW YOUR ACCOUNTS\n W:  MAKE A WITHDRAWAL\n D:  MAKE A DEPOSIT\n T:  MAKE A TRANSFER");
				out.println("Please enter the letter assigned to the option you would like to select (or enter ? for more instructions): ");

				while(true) {
					String request = in.readLine();
					assert customer != null;
					System.out.println("Request from " + customer.getKey());
					System.out.println(" This is request: " + request);
					String responce = bank.processRequest(customer, request.toUpperCase());
					out.println(responce);
				}
			}
			else {
				out.println("Log In Failed");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}

}
