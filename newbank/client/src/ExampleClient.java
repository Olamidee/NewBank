//Imports the project package on the client side


//Java imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class ExampleClient extends Thread{
	
	private Socket server;
	private PrintWriter bankServerOut;	
	private BufferedReader userInput;
	private Thread bankServerResponceThread;
	
 // Implement an example client class with IP address and port required for connection
 // Throws exception if the host is not part of the server's IP table, or if the input is incorrect 


	public ExampleClient(String ip, int port) throws UnknownHostException, IOException {
		server = new Socket(ip,port);

		userInput = new BufferedReader(new InputStreamReader(System.in)); 
		bankServerOut = new PrintWriter(server.getOutputStream(), true); 
		
		bankServerResponceThread = new Thread() {
			private BufferedReader bankServerIn = new BufferedReader(new InputStreamReader(server.getInputStream())); 
      
       //The method sends a "responce" from the server to the client
			public void run() {
				try {
					while(true) {
						String responce = bankServerIn.readLine();
						System.out.println(responce);
					}
				} catch (IOException e) {
          //prints the error message
					e.printStackTrace();
					return;
				}
			}
		};
		bankServerResponceThread.start();
	}
	
//This methods sends a client requets to the server, to execute a user-defined command	

	public void run() {
		while(true) {
			try {
				while(true) {
					String command = userInput.readLine();
					bankServerOut.println(command);
				}				
			} catch (IOException e) {
        //prints the error message

				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
  //instantiate a client, connect on IP localhost (127.0.0.1) on port 14002 and start the connection
  
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		new ExampleClient("localhost",14002).start();

	}
}
