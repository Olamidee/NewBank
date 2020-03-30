import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NewBankClientHandler extends Thread{
	
	private NewBank bank;
	private BufferedReader in;
	private PrintWriter out;
	
	
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
			Customer customer = bank.checkLogInDetails(userName);
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

			Boolean validPassword = bank.checkPassword(customer, password);
			// if the user is authenticated then get requests from the user and process them 
			if(validPassword == true) {
				out.println("Log In Successful. What do you want to do?");
				while(true) {
					String request = in.readLine();
					System.out.println("Request from " + userName);
					String responce = bank.processRequest(userName, request);
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
