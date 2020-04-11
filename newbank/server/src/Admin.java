import java.sql.*;

public class Admin {

    /*
    this class contains all of the methods which query the database that holds the information of username, password and
    security question.
    A mySQL database option was used as one of the requirements was to persist the data for the users, so this appeared
    to be the best option.
    unfortunately there can be comm issues when using certain wifi networks (usually more secure networks/company networks etc)
    as they dont allow a response from the server therefore can disrupt the abillity to talk to the database
     */

    //private string called dburl which holds the address of the database that contains the newbank details
    private String dbURL = "jdbc:mysql://mysql.stackcp.com:54129/newbank.db-3133310665";
    //private string that holds the username for the newbank database
    private String dbUsername = "newbank.db-3133310665";
    //pricate string tha holds the password for the newbank database
    private String dbpassword = "Password#01";

    //private Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbpassword );
    //private Statement statement = conn.createStatement();


    /*
    a public method that returns a string array that collects the data from the field by using the username parameter to query the database, this is then put
    into an array which can then be returned
     */
    public String[] displayCurrentDB (String userName) {

        String[] user = new String[3];

        try{
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbpassword);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery( " SELECT * FROM newBankTable WHERE username = '" + userName + "'   ");
            // "SELECT * FROM newBankTable WHERE username = ?");
            // result = statement.setString(1, input);
            // ResultSet result = statement.executeQuery();
            // ', ", " or 1=1--, " UNION 1=1, 
            
            

            while(result.next()) {

                user[0] = result.getString("username");
                user[1] = result.getString("password");
                user[2] = result.getString("securityQ");

            }
        }catch (SQLException e) {
            System.out.println("something went wrong" + e.getMessage());
        }
        return user;
        
    }


    /*
    a public method that takes the parameters of username, password and securityQuestion and enters them as a new
    customer into the database
     */

    public void adminEnterNewUser (String username, String password, String securityQuestion) {


        try {

            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbpassword);
            Statement statement = conn.createStatement();


            statement.execute(" INSERT INTO newBankTable (username, password, securityQ)"
                    + " VALUES ( '" + username + "' ,  '" + password + "' , '" + securityQuestion + "' )");


            ResultSet results = statement.executeQuery("SELECT * FROM newBankTable");
            while (results.next()) {
                System.out.println(results.getString("username"));
                System.out.println(results.getString("password"));
                System.out.println(results.getString("securityQ"));
            }


            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("something went wrong.. " + e.getMessage());
        }
    }

    /*
    a public method that returns a boolean value. the parameter "in" queries the database to select the user and compares
    their respective password to the parameter "pass". if it matches then the boolean "enter" is made true and then returned
     */

    public boolean customerPasswordCheck (String in, String pass) {

        boolean enter = false;

        try{

            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbpassword);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(" SELECT password FROM newBankTable WHERE username =  '" + in + "'  ");

            while(result.next()) {
                String pWord = result.getString( "password");
                if (pWord.equals(pass)) {
                    enter = true;
                }
            }

        }catch (SQLException e) {
            System.out.println("something went wrong... " + e.getMessage() );
        }

        return enter;
    }

    /*
    a public method that returns a boolean. there is only one instance of admin in the database so "ADMIN" is hard coded
    into the method to select the field and then the password is compared to the parameter "in". if this matches then
    the boolean variable "enter" is changed to true and returned
     */

    public boolean adminPasswordCheck (String in) {


        boolean enter = false;

        try{
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbpassword);
            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery( "SELECT password FROM newBankTable WHERE username = 'ADMIN' ");

            while(result.next()){
                String pWord = result.getString("password");

                if (pWord.equals(in)) {
                    enter = true;
                }
            }
        }catch (SQLException e) {
            System.out.println("something went wrong ..." + e.getMessage());
        }
        return enter;
    }

    /*
    a public method that returns a boolean. the parameter "un" is used to query the database and then is compared to the
    username within the newbank database and if it matches then returns true
     */

    public boolean userNameCheck (String un) {

        boolean check = false;

        try{
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbpassword);
            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery( " SELECT username FROM newBankTable WHERE username = '" + un + "' ");


            while (result.next()){

                String userN = result.getString("username");

                if  (userN.equals(un)) {
                    check = true;
                }



            }

        }catch (SQLException e) {

            System.out.println(" something went wrong.. "  + e.getMessage());
        }

        return check;

    }

    /*
    method that returns a boolean. selects the user by the parameter "user" and checks to see if the parameter "password"
    matches the password on the database and if it does then returns check as true.
     */

    public boolean securityQCheck (String user, String password) {
        boolean check = false;

        try{
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbpassword);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery( "SELECT password FROM newBankTable WHERE username =  '" + user + "' ");

            while(result.next()) {
                String pass = result.getString( "password");

                if (pass.equals(password)) {
                    check = true;
                }
            }

        }catch (SQLException e) {
            System.out.println("something went wrong.. " + e.getMessage());
        }
        return check;
    }

    /*
    public method that allows the admin to change the users username. the first parameter "username" selects the user from
    the database then the second parameter "userNameTo" replaces the username value
     */

    public void changeUserName (String username, String userNameTo) {

        try{
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbpassword);
            Statement statement = conn.createStatement();

            statement.execute("UPDATE newBankTable SET username =  '" + userNameTo + "' WHERE username = '" + username + "' ");

        }catch (SQLException e) {
            System.out.println("something went wrong... " + e.getMessage());
        }

    }

    /*
    public method that allows admin to change the users password. the first parameter "username" selects the user from the
    database and the the second parameter "passwordTo" replaces the value in the password field.
     */

    public void changePassword (String username, String passwordTo) {

        try{
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbpassword);
            Statement statement = conn.createStatement();

            statement.execute("UPDATE newBankTable SET password =  '" + passwordTo + "' WHERE username = '" + username + "' ");

        }catch (SQLException e) {
            System.out.println("something went wrong... " + e.getMessage());
        }

    }

    /*
    public method that allows admin to change the answer to the security question, which by default is where do you live?
    the "username" parameter selects the user from the database and then the "answerTo" parameter replaces the current
    value
     */

    public void changeSecurityAnswer (String username, String answerTo) {

        try{
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbpassword);
            Statement statement = conn.createStatement();

            statement.execute("UPDATE newBankTable SET securityQ =  '" + answerTo + "' WHERE username = '" + username + "' ");

        }catch (SQLException e) {
            System.out.println("something went wrong... " + e.getMessage());
        }

    }

    /*
    public method that allows admin to delete the user from the database. the "username" parameter selects the user and
    then that user is deleted from the database
     */
    public void deleteUser (String username) {
        try{

            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbpassword);
            Statement statement = conn.createStatement();

            statement.execute(" DELETE FROM newBankTable WHERE username =  '" + username + "' ");


        }catch (SQLException e) {
            System.out.println("something went wrong... " + e.getMessage());
        }
    }


    /*
    a main function that prints out the entire database as it was useful when testing the code to see if the above
    methods had executed their functions successfully.
     */


    public static void main(String[] args) {

        Admin a = new Admin();

        //System.out.println(a.userNameCheck("Anthony"));
        //System.out.println(a.adminPasswordCheck("ADMINPASS"));
        // System.out.println(a.displayCurrentDB("Anthony"));
        //a.changeUserName("null", "Jeffery");
        //a.changePassword("Jeffery", "jefferies password");
        //a.changeSecurityAnswer("Jeffery", "gibralter");




       try {
            Connection conn = DriverManager.getConnection( "jdbc:mysql://mysql.stackcp.com:54129/newbank.db-3133310665", "newbank.db-3133310665", "Password#01" );
            Statement statement = conn.createStatement();
           // statement.execute("INSERT INTO newBankTable (username, password, securityQ) VALUES ('Anthony', 'myPassword', 'what country do you live in?')");
            ResultSet results = statement.executeQuery("SELECT * FROM newBankTable" );
            //statement.execute("SELECT * FROM newBankTable ");
            while(results.next()){
                System.out.println(results.getString("username"));
                System.out.println(results.getString("password"));
                System.out.println(results.getString("securityQ"));


            }
        }catch (SQLException e) {
            System.out.println("something went wrong " + e.getMessage());

        }

    }}




