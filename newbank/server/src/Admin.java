import java.sql.*;

public class Admin {

    private String dbURL = "jdbc:mysql://mysql.stackcp.com:54129/newbank.db-3133310665";
    private String dbUsername = "newbank.db-3133310665";
    private String dbpassword = "Password#01";

    //private Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbpassword );
    //private Statement statement = conn.createStatement();


    public String[] displayCurrentDB (String userName) {

        String[] user = new String[3];

        try{
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbpassword);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery( " SELECT * FROM newBankTable WHERE username = '" + userName + "'   ");

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

    public void changeUserName (String username, String userNameTo) {

        try{
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbpassword);
            Statement statement = conn.createStatement();

            statement.execute("UPDATE newBankTable SET username =  '" + userNameTo + "' WHERE username = '" + username + "' ");

        }catch (SQLException e) {
            System.out.println("something went wrong... " + e.getMessage());
        }

    }

    public void changePassword (String username, String passwordTo) {

        try{
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbpassword);
            Statement statement = conn.createStatement();

            statement.execute("UPDATE newBankTable SET password =  '" + passwordTo + "' WHERE username = '" + username + "' ");

        }catch (SQLException e) {
            System.out.println("something went wrong... " + e.getMessage());
        }

    }

    public void changeSecurityAnswer (String username, String answerTo) {

        try{
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbpassword);
            Statement statement = conn.createStatement();

            statement.execute("UPDATE newBankTable SET securityQ =  '" + answerTo + "' WHERE username = '" + username + "' ");

        }catch (SQLException e) {
            System.out.println("something went wrong... " + e.getMessage());
        }

    }
    public void deleteUser (String username) {
        try{

            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbpassword);
            Statement statement = conn.createStatement();

            statement.execute(" DELETE FROM newBankTable WHERE username =  '" + username + "' ");


        }catch (SQLException e) {
            System.out.println("something went wrong... " + e.getMessage());
        }
    }




    /*public boolean stringCheck (String toCheck) {

        boolean retBool = false;

        if (toCheck == "ADMIN") {
            retBool = true;
        }

        return retBool;

    }*/


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




