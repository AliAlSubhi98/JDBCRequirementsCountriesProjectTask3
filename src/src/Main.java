package src;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		JDBC myJDBC = new JDBC();

		myJDBC.setAccessToDatabase(myJDBC);
		
		while (true) {
			System.out.println(".........................................................");
			System.out.println("1.  Initialize database");
			System.out.println("2.  Fetch Data of countries from API");
			System.out.println("3.  INSERT INTO JDBC");
			System.out.println("4.  BACKUP THE DATABASE ");
			System.out.println("5.  REMOVE TABLES FROM DATABASE");
			System.out.println("6.  FETCH COUNTRIES TABLE FROM DB");
			System.out.println("7.  SEARCH FROM DATABASE");
			System.out.println("0.  Exit program");
			System.out.println("=========================================================");

			int choice;
			try {
				System.out.print("Enter option number: ");
				choice = sc.nextInt();
			} catch (Exception e) {
				System.err.println("Please enter an integer.");
				sc.next(); // discard non-integer input
				continue; // go back to the beginning of the loop
			}

			// continue with switch statement
			switch (choice) {
			case 1:
				myJDBC.initializeDatabase();
				break;
				
			case 2:
				APIConsumer.showApi();
				break;
				
			case 3:
				JDBC.INSERT_INTO_countries();
				break;
				
			case 4:
				JDBC.backupDatabase();
				break;
				
			case 5:
				JDBC.removeTablesFromDatabase();
				break;
			case 6:
				JDBC.fetchCountriesTablesFromDatabase();
				break;
			case 7:
				JDBC.searchFromDatabase();
				break;
			case 0:
				System.exit(0);
				break;

			default:
				System.err.println("Invalid option number");
				break;
			}
		}
	}
}
