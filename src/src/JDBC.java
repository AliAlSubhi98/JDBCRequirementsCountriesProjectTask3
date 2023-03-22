package src;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC {
	static String databaseName;
	static String userName;
	static String password;
	
	public JDBC setAccessToDatabase(JDBC setAccessToDatabase) {
	    Scanner sc = new Scanner(System.in);
	    System.out.println("==================LOGIN TO THE DATABASE==================");
	    System.out.print("Enter database name: ");
	    JDBC.databaseName = sc.next();
	    System.out.print("Enter user name: (sa) ");
	    JDBC.userName = sc.next();
	    System.out.print("Enter password: (root)");
	    JDBC.password = sc.next();
	    System.out.println("=========================================================");
	    return setAccessToDatabase;
	}
	
	public void initializeDatabase() {
	    System.out.println("Initialize Database");
	    Scanner scanner = new Scanner(System.in);

	    String url = "jdbc:sqlserver://" + "localhost:1433;" +
	            "encrypt=true;" +
	            "trustServerCertificate=true";
	    Connection con = null;

	    try {
	        Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	        DriverManager.registerDriver(driver);

	        con = DriverManager.getConnection(url, userName, password);
	        Statement st = con.createStatement();

	        // Check if the database exists
	        String sql1 = "SELECT * FROM sys.databases WHERE name='" + databaseName + "'";
	        ResultSet rs = st.executeQuery(sql1);

	        if (rs.next()) {
	            // Update url with the existing database name
	            url += ";databaseName=" + databaseName;
	            con = DriverManager.getConnection(url, userName, password);
	            Statement st2 = con.createStatement();

	            // Check if the universities table exists
	            String sql2 = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = ' ? '"; // replace ? with table name
	            rs = st2.executeQuery(sql2);

	            if (!rs.next()) {
	                // Create table if it doesn't exist
	                String sql3 = "CREATE TABLE countries (\r\n"
	                        + "  id INTEGER IDENTITY PRIMARY KEY,\r\n"
	                        + "  name VARCHAR(255),\r\n"
	                        + "  country VARCHAR(255),\r\n"
	                        + "  state_province VARCHAR(255),\r\n"
	                        + "  domains VARCHAR(MAX),\r\n"
	                        + "  web_pages VARCHAR(MAX),\r\n"
	                        + "  alpha_two_code VARCHAR(2)\r\n"
	                        + ");";
	                st2.executeUpdate(sql3);
	                System.out.println("countries Table created successfully!");
	            } else {
	                System.out.println("countries Table already exists!");
	            }
	        } else {
	            System.out.println("Database does not exist.");
	        }

	        con.close();
	    } catch (Exception ex) {
	        System.err.println(ex);
	    }
	}

}
