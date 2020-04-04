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
			if (userName.equals("ADMIN")) {

				out.println("welcome to Admin log in..");
				out.println("please enter the number of the option you would like to select");

				out.println("1:  Enter new user ");
				out.println("2:  adjust new user details");
				out.println("3:  delete user details");

				String adminInput = in.readLine();

				switch (adminInput) {
					case "1":
						out.println("you have selected 1 to enter a new user");
						out.println("enter a new username...");
						String newUsernameInput = in.readLine();

						out.println("enter a new password..");
						String newUserPassword = in.readLine();

						out.println("enter a new security question");
						String newUserSecurityQuestion = in.readLine();

						a.adminEnterNewUser(newUsernameInput, newUserPassword,newUserSecurityQuestion);

						out.println("new user has now been entered ");
						break;
					case "2":
						//change user details method
						break;
					case "3":
						//delete user method
						break;
					default:
						out.println("default");
				}


			}else{
				out.println("nonadmin");
			}



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
