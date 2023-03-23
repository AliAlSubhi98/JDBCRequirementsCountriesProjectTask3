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
	            String sql2 = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'countries'"; // replace ? with table name
	            rs = st2.executeQuery(sql2);

	            if (!rs.next()) {
	                // Create table if it doesn't exist
	                String sql3 = "CREATE TABLE countries(\r\n"
	                		+ "  id INT identity PRIMARY KEY,\r\n"
	                		+ "  common_name VARCHAR(255),\r\n"
	                		+ "  official_name VARCHAR(255),\r\n"
	                		+ "  native_name_official VARCHAR(255),\r\n"
	                		+ "  native_name_common VARCHAR(255),\r\n"
	                		+ "  tld VARCHAR(255),\r\n"
	                		+ "  cca2 VARCHAR(255),\r\n"
	                		+ "  ccn3 VARCHAR(255),\r\n"
	                		+ "  cca3 VARCHAR(255),\r\n"
	                		+ "  independent BIT,\r\n"
	                		+ "  status VARCHAR(255),\r\n"
	                		+ "  un_member BIT,\r\n"
	                		+ "  currency_code VARCHAR(255),\r\n"
	                		+ "  currency_name VARCHAR(255),\r\n"
	                		+ "  currency_symbol VARCHAR(255),\r\n"
	                		+ "  idd_root VARCHAR(255)  ,\r\n"
	                		+ "  idd_suffixes VARCHAR(255),\r\n"
	                		+ "  capital VARCHAR(255),\r\n"
	                		+ "  alt_spellings VARCHAR(255),\r\n"
	                		+ "  region VARCHAR(255),\r\n"
	                		+ "  subregion VARCHAR(255),\r\n"
	                		+ "  languages VARCHAR(255),\r\n"
	                		+ "  translations_official VARCHAR(255),\r\n"
	                		+ "  translations_common VARCHAR(255),\r\n"
	                		+ "  latlng VARCHAR(255),\r\n"
	                		+ "  landlocked BIT,\r\n"
	                		+ "  borders VARCHAR(255),\r\n"
	                		+ "  area DECIMAL(15,2),\r\n"
	                		+ "  demonyms_f VARCHAR(255),\r\n"
	                		+ "  demonyms_m VARCHAR(255),\r\n"
	                		+ "  flag VARCHAR(255),\r\n"
	                		+ "  google_maps VARCHAR(255),\r\n"
	                		+ "  open_street_maps VARCHAR(255),\r\n"
	                		+ "  population INT,\r\n"
	                		+ "  gini VARCHAR(255),\r\n"
	                		+ "  fifa VARCHAR(255),\r\n"
	                		+ "  car_signs VARCHAR(255),\r\n"
	                		+ "  car_side VARCHAR(255),\r\n"
	                		+ "  timezones VARCHAR(255),\r\n"
	                		+ "  continents VARCHAR(255),\r\n"
	                		+ "  flags_png VARCHAR(255),\r\n"
	                		+ "  flags_svg VARCHAR(255),\r\n"
	                		+ "  flags_alt VARCHAR(255),\r\n"
	                		+ "  coat_of_arms_png VARCHAR(255),\r\n"
	                		+ "  coat_of_arms_svg VARCHAR(255),\r\n"
	                		+ "  start_of_week VARCHAR(255),\r\n"
	                		+ "  capital_latlng VARCHAR(255),\r\n"
	                		+ "  postal_code_format VARCHAR(255),\r\n"
	                		+ "  postal_code_regex VARCHAR(255)\r\n"
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
