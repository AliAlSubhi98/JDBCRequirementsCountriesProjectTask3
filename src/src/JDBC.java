package src;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;
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
	                String sql3 = "create table countries (\r\n"
	                		+ "	id int primary key ,\r\n"
	                		+ "	common_name varchar(255),\r\n"
	                		+ "	official_name varchar(255),\r\n"
	                		+ "	cca2 varchar(5),\r\n"
	                		+ "	ccn3 varchar(5),\r\n"
	                		+ "	cca3 varchar(5),\r\n"
	                		+ "	cioc varchar(5),\r\n"
	                		+ "	independent BIT,\r\n"
	                		+ "	country_status varchar(20),\r\n"
	                		+ "	un_member binary,\r\n"
	                		+ "	idd_root varchar(5),\r\n"
	                		+ "	region varchar(20),\r\n"
	                		+ "	subregion varchar(50),\r\n"
	                		+ "	latitude float,\r\n"
	                		+ "	logitude float,\r\n"
	                		+ "	land_locked binary,\r\n"
	                		+ "	area float,\r\n"
	                		+ "	eng_f varchar(50),\r\n"
	                		+ "	eng_m varchar(50),\r\n"
	                		+ "	fra_f varchar(50),\r\n"
	                		+ "	fra_m varchar(50),\r\n"
	                		+ "	flag varchar(100),\r\n"
	                		+ "	google_maps varchar(100),\r\n"
	                		+ "	open_street_maps varchar(100),\r\n"
	                		+ "	c_population int,\r\n"
	                		+ "	gini_year varchar(4),\r\n"
	                		+ "	gini_val float,\r\n"
	                		+ "	fifa varchar(5),\r\n"
	                		+ "	car_side varchar(10),\r\n"
	                		+ "	flag_png varchar(255),\r\n"
	                		+ "	flag_svg varchar(255),\r\n"
	                		+ "	flag_alt text,\r\n"
	                		+ "	coa_png varchar(100),\r\n"
	                		+ "	coa_svg varchar(100),\r\n"
	                		+ "	start_of_week varchar(10),\r\n"
	                		+ "	capital_lat float,\r\n"
	                		+ "	capital_long float,\r\n"
	                		+ "	postal_format varchar(255),\r\n"
	                		+ "	postal_regex varchar(255)\r\n"
	                		+ ");\r\n"
	                		+ "create table timezones(\r\n"
	                		+ "	id int primary key identity(1,1),\r\n"
	                		+ "	tz varchar(15),\r\n"
	                		+ "	cid int\r\n"
	                		+ ");\r\n"
	                		+ "create table car_signs(\r\n"
	                		+ "	id int primary key identity(1,1),\r\n"
	                		+ "	csign varchar(10),\r\n"
	                		+ "	cid int\r\n"
	                		+ ");\r\n"
	                		+ "create table borders(\r\n"
	                		+ "	id int primary key identity(1,1),\r\n"
	                		+ "	border varchar(5),\r\n"
	                		+ "	cid int\r\n"
	                		+ ");\r\n"
	                		+ "create table translations(\r\n"
	                		+ "	id int primary key identity(1,1),\r\n"
	                		+ "	tran_key varchar(5),\r\n"
	                		+ "	tran_official varchar(255),\r\n"
	                		+ "	tran_common varchar(255),\r\n"
	                		+ "	cid int\r\n"
	                		+ ");\r\n"
	                		+ "create table languages (\r\n"
	                		+ "	id int primary key identity(1,1),\r\n"
	                		+ "	lan_key varchar(5),\r\n"
	                		+ "	lan_name varchar(255),\r\n"
	                		+ "	cid int\r\n"
	                		+ ");\r\n"
	                		+ "create table alt_spellings(\r\n"
	                		+ "	id int primary key identity(1,1),\r\n"
	                		+ "	spelling varchar(255),\r\n"
	                		+ "	cid int\r\n"
	                		+ ");\r\n"
	                		+ "create table capitals (\r\n"
	                		+ "	id int primary key identity(1,1),\r\n"
	                		+ "	capital_name varchar(255),\r\n"
	                		+ "	cid int\r\n"
	                		+ ");\r\n"
	                		+ "create table native_names(\r\n"
	                		+ "	id int primary key identity(1,1),\r\n"
	                		+ "	name_key varchar(5),\r\n"
	                		+ "	common_namme varchar(255),\r\n"
	                		+ "	official_name varchar(255),\r\n"
	                		+ "	cid int\r\n"
	                		+ ");\r\n"
	                		+ "create table tlds(\r\n"
	                		+ "	id int primary key identity(1,1),\r\n"
	                		+ "	tld varchar(255),\r\n"
	                		+ "	cid int\r\n"
	                		+ ");\r\n"
	                		+ "create table currencies(\r\n"
	                		+ "	id int primary key identity(1,1),\r\n"
	                		+ "	currency_key varchar(5),\r\n"
	                		+ "	currency_name varchar(255),\r\n"
	                		+ "	symbol varchar(20),\r\n"
	                		+ "	cid int\r\n"
	                		+ ");\r\n"
	                		+ "create table suffixes(\r\n"
	                		+ "	id int primary key identity(1,1),\r\n"
	                		+ "	suf varchar(255),\r\n"
	                		+ "	cid int\r\n"
	                		+ ");";
	                st2.executeUpdate(sql3);
	                System.out.println(" Tables created successfully!");
	            } else {
	                System.out.println(" Tables already exists!");
	            }
	        } else {
	            System.out.println("Database does not exist.");
	        }

	        con.close();
	    } catch (Exception ex) {
	        System.err.println(ex);
	    }
	}
	
	public static void INSERT_INTO_countries() {

		System.out.println("TRYING TO INSERT INTO countries");

		String url = "jdbc:sqlserver://" + "localhost:1433;" + "encrypt=true;" + "trustServerCertificate=true";
		Connection con = null;

		try {
			

			con = DriverManager.getConnection(url, userName, password);
			Statement st = con.createStatement();

			// Update url with the database name
			url += ";databaseName=" + databaseName;
			con = DriverManager.getConnection(url, userName, password);

			String sql3 = "INSERT INTO countries(common_name, official_name, cca2, ccn3, cca3, cioc, independent, country_status, un_member, idd_root, region, subregion, latitude, logitude, land_locked, area, eng_f, eng_m, fra_f, fra_m, flag, google_maps, open_street_maps, c_population, gini_year, gini_val, fifa, car_side, flag_png, flag_svg, flag_alt, coa_png, coa_svg, start_of_week, capital_lat, capital_long, postal_format, postal_regex,id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql3);
			
			String sql4 = "insert into timezones(tz,cid) values(?,?);";
			PreparedStatement ps2 = con.prepareStatement(sql4);
			
			String sql5 = "insert into car_signs(csign,cid) values (?,?);";
			PreparedStatement ps3 = con.prepareStatement(sql5);
			
			String sql6 = "insert into borders(border,cid) values (?,?);";
			PreparedStatement ps4 = con.prepareStatement(sql6);
			
			String sql7 = "insert into translations (tran_key,tran_official,tran_common,cid) values (?,?,?,?);";
			PreparedStatement ps5 = con.prepareStatement(sql7);
			
			String sql8 = "insert into languages (lan_key,lan_name,cid) values (?,?,?);";
			PreparedStatement ps6 = con.prepareStatement(sql8);
			
			String sql9 = "insert into alt_spellings (spelling,cid) values (?,?);";
			PreparedStatement ps7 = con.prepareStatement(sql9);

			String sql10 ="insert into capitals (capital_name,cid) values (?,?);";
			PreparedStatement ps8 = con.prepareStatement(sql10);

			String sql11 = "insert into native_names (name_key,common_namme,official_name,cid) values (?,?,?,?);";
			PreparedStatement ps9 = con.prepareStatement(sql11);

			String sql12 = "insert into tlds(tld,cid) values (?,?);";
			PreparedStatement ps10 = con.prepareStatement(sql12);

			String sql13 = "insert into currencies(currency_key,currency_name,symbol,cid) values (?,?,?,?);";
			PreparedStatement ps11 = con.prepareStatement(sql13);

			String sql14 = "insert into suffixes(suf,cid) values (?,?);";
			PreparedStatement ps12 = con.prepareStatement(sql14);


			ArrayList<MyObject> countries = APIConsumer.countries;
			for (int i=0 ; i<countries.size() ; i++) {
				ps.setInt(39, i);
				ps.setString(1, countries.get(i).name.common);
				ps.setString(2, countries.get(i).name.official);
				ps.setString(3, countries.get(i).cca2);
				ps.setString(4, countries.get(i).ccn3);
				ps.setString(5, countries.get(i).cca3);
				ps.setString(6, countries.get(i).cioc);
				ps.setBoolean(7, countries.get(i).independent);
				ps.setString(8, countries.get(i).status);
				ps.setBoolean(9, countries.get(i).unMember);
				ps.setString(10, countries.get(i).idd.root);
				ps.setString(11, countries.get(i).region);
				ps.setString(12, countries.get(i).subregion);
				if(countries.get(i).capitalInfo.latlng != null) {
					ps.setDouble(13, countries.get(i).latlng[0]);
					ps.setDouble(14, countries.get(i).latlng[1]);
				}
				ps.setBoolean(15, countries.get(i).landlocked);
				ps.setDouble(16, countries.get(i).area);
				if(countries.get(i).demonyms != null) {
					if (countries.get(i).demonyms.get("eng") != null) {
						ps.setString(17, countries.get(i).demonyms.get("eng").f);
						ps.setString(18, countries.get(i).demonyms.get("eng").m);
					}
					if(countries.get(i).demonyms.get("fra") != null) {
						ps.setString(19, countries.get(i).demonyms.get("fra").f);
						ps.setString(20, countries.get(i).demonyms.get("fra").m);
					}
				}
				ps.setString(21, countries.get(i).flag);
				ps.setString(22, countries.get(i).maps.googleMaps);
				ps.setString(23, countries.get(i).maps.openStreetMaps);
				ps.setInt(24, countries.get(i).population);
				if(countries.get(i).gini != null) {
					for(String key : countries.get(i).gini.keySet()){
						ps.setString(25, key);
						ps.setFloat(26, countries.get(i).gini.get(key));
					}
				}
				else {
					ps.setString(25, null);
					ps.setFloat(26, 0);
				}
				ps.setString(27, countries.get(i).fifa);
				ps.setString(28, countries.get(i).car.side);
				ps.setString(29, countries.get(i).flags.png);
				ps.setString(30, countries.get(i).flags.svg);
				ps.setString(31, countries.get(i).flags.alt);
				ps.setString(32, countries.get(i).coatOfArms.png);
				ps.setString(33, countries.get(i).coatOfArms.svg);
				ps.setString(34, countries.get(i).startOfWeek);
				if(countries.get(i).capitalInfo.latlng != null) {
					ps.setDouble(35, countries.get(i).capitalInfo.latlng[0]);
					ps.setDouble(36, countries.get(i).capitalInfo.latlng[1]);
				}
				if(countries.get(i).postalCode != null) {
					ps.setString(37, countries.get(i).postalCode.format);
					ps.setString(38, countries.get(i).postalCode.regex);
				}

				for(int j = 0 ; j<countries.get(i).timezones.length; j++) {
					ps2.setString(1, countries.get(i).timezones[j]);
					ps2.setInt(2, i);
					ps2.executeUpdate();
				}
				
				if (countries.get(i).car.signs != null) {
					for (int j = 0; j < countries.get(i).car.signs.length; j++) {
						ps3.setString(1, countries.get(i).car.signs[j]);
						ps3.setInt(2, i);
						ps3.executeUpdate();
					}
				}
				
				if (countries.get(i).borders != null) {
					for(int j=0 ; j<countries.get(i).borders.length ;j++) {
						ps4.setString(1, countries.get(i).borders[j]);
						ps4.setInt(2, i);
						ps4.executeUpdate();
					}
				}
				
				for(String key : countries.get(i).translations.keySet()) {
					ps5.setString(1, key);
					ps5.setString(2, countries.get(i).translations.get(key).official);
					ps5.setString(3, countries.get(i).translations.get(key).common);
					ps5.setInt(4, i);
					ps5.executeUpdate();
				}
				
				for(String key : countries.get(i).languages.keySet()) {
					ps6.setString(1, key);
					ps6.setString(2, countries.get(i).languages.get(key));
					ps6.setInt(3, i);
					ps6.executeUpdate();
				}
				
				if (countries.get(i).altSpellings != null) {
					for (int j = 0; j < countries.get(i).altSpellings.length; j++) {
						ps7.setString(1, countries.get(i).altSpellings[j]);
						ps7.setInt(2, i);
						ps7.executeUpdate();
					}
				}
				
				if (countries.get(i).capital != null) {
					for (int j = 0; j < countries.get(i).capital.length; j++) {
						ps8.setString(1, countries.get(i).capital[j]);
						ps8.setInt(2, i);
						ps8.executeUpdate();
					}
				}
				
				for(String key : countries.get(i).name.nativeName.keySet()) {
					ps9.setString(1, key);
					ps9.setString(2, countries.get(i).name.nativeName.get(key).common);
					ps9.setString(3, countries.get(i).name.nativeName.get(key).official);
					ps9.setInt(4, i);
					ps9.executeUpdate();
				}
				
				if (countries.get(i).tld != null) {
					for (int j = 0; j < countries.get(i).tld.length; j++) {
						ps10.setString(1, countries.get(i).tld[j]);
						ps10.setInt(2, i);
						ps10.executeUpdate();
					}
				}
				
				if (countries.get(i).currencies != null) {
					for (String key : countries.get(i).currencies.keySet()) {
						ps11.setString(1, key);
						ps11.setString(2, countries.get(i).currencies.get(key).name);
						ps11.setString(3, countries.get(i).currencies.get(key).symbol);
						ps11.setInt(4, i);
						ps11.executeUpdate();
					}
				}

				if (countries.get(i).idd.suffixes != null) {
					for (int j = 0; j < countries.get(i).idd.suffixes.length; j++) {
						ps12.setString(1, countries.get(i).idd.suffixes[j]);
						ps12.setInt(2, i);
						ps12.executeUpdate();
					}
				}

				ps.executeUpdate();
			}
			System.out.println("Data inserted into tables!");
			

			

			
			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}
	
	public static void printUniversityTable() {

		Connection con = null;
		try {
			String url = "jdbc:sqlserver://" + "localhost:1433;" + "encrypt=true;" + "trustServerCertificate=true";

			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(url, userName, password);

			String sql = "Select * from  countries";

			PreparedStatement st = con.prepareStatement(sql);
			ResultSet resultSet = st.executeQuery();

			while (resultSet.next()) {

				String alpha_two_code = resultSet.getString("alpha_two_code");

			}

			st.close();
			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}
	
	static public void backupDatabase() {
		
		System.out.println("TRYING TO BACKUP DATABASE ");

		String url = "jdbc:sqlserver://" + "localhost:1433;" + "encrypt=true;" + "trustServerCertificate=true";
		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			
			// Update url with the database name
			url += ";databaseName=" + databaseName;
			con = DriverManager.getConnection(url, userName, password);
		    Statement st2 = con.createStatement();

		    // Create table if it doesn't exist
		    String sql2 = "BACKUP DATABASE ali\r\n"
		    		+ "TO DISK = 'C:\\Users\\Lenovo\\eclipse-workspace\\JDBCRequirementsCountriesProjectTask3\\Backup\\Backup.bak';;";
		    st2.executeUpdate(sql2);
			
			System.out.println("BACKUP DATABASE SUCCESSFULLY");
			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}
	

}
