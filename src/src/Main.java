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
				break;
				
			case 3:
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
