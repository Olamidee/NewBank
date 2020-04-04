import java.sql.*;

public class Admin {

    private String dbURL = "jdbc:mysql://mysql.stackcp.com:54129/newbank.db-3133310665";
    private String dbUsername = "newbank.db-3133310665";
    private String dbpassword = "Password#01";
    //private Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbpassword );
    //private Statement statement = conn.createStatement();



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

    public boolean stringCheck (String toCheck) {

        boolean retBool = false;

        if (toCheck == "ADMIN") {
            retBool = true;
        }

        return retBool;

    }


    public static void main(String[] args) {

        Admin a = new Admin();




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




