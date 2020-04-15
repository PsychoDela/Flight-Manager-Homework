import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main 
{
	static Scanner sc = new Scanner (System.in);
	final private static String adminName = "Dela";
	final private static String adminPwd = "jasamjebentip";
	private static boolean adminLoggedIn;
	static ArrayList <Airport> airports = new ArrayList <Airport> ();
	static HashMap <String , String> airportNames = new HashMap <String , String> ();
	
	public static void main(String[] args) throws FileNotFoundException 
	{	
		User admin = new User (adminName , adminPwd);
		
		if (admin.isCreated())
		{
			System.out.println("Admin account available.\n");
		}
		
		else
		{
			System.out.println("Admin account not loaded.\n");
		}
		
		start();	
	}
	
	public static void start()
	{
		int choice1;
		
		System.out.println("*-*-* Flight Manager *-*-*\n");
		System.out.println("1) Login\n2) Create account");
		choice1 = sc.nextInt();
		
		if (choice1 < 1 || choice1 > 2)
		{ 
			Tools.errorMessage();
			start();
		}
		
		switch (choice1)
		{
			case 1:
				login();
				break;
				
			case 2:
				createAccount();
				break;
		}
	}
	
	public static void login()
	{
		String username;
		String password;
		
		System.out.println("*-*-* Login *-*-*");
		
		System.out.println("Enter username: ");
		sc.nextLine();
		username = sc.nextLine();
		
		System.out.println("Enter password: (password cannot contain whitespaces)");
		password = sc.next();
		
		if (User.loggedIn(username , password))
		{
			System.out.println("\nLogin successful");
			System.out.println("Proceeding to menu...\n");
			
			if (username.equals(adminName) && password.equals(adminPwd))
			{
				adminLoggedIn = true;
			}
			
			menu();
		}
		
		else
		{
			Tools.errorMessage();
			login();
		}
	}
	
	public static void createAccount()
	{
		String username;
		String password;
		
		System.out.println("*-*-* Sign up *-*-*");
		
		System.out.println("Enter username: ");
		sc.nextLine();
		username = sc.nextLine();
		
		System.out.println("Enter password: (password cannot contain whitespaces)");
		password = sc.next();
		
		User account = new User(username, password);
		
		if (account.isCreated())
		{
			System.out.println("\nAccount successfully created");
			System.out.println("Proceeding to login...\n");
			login();		
		}
		
		else
		{
			Tools.errorMessage();
			createAccount();
		}
	}
	
	public static void menu()
	{
		int choice2;
		
		System.out.println("*-*-* Menu *-*-*");
		
		System.out.println("\n1) Book flight\n2) Create airport\n3) Create airline\n4) Create flight");
		choice2 = sc.nextInt();
		
		if (choice2 < 1 || choice2 > 4)
		{
			Tools.errorMessage();
			menu();
		}
		
		switch (choice2)
		{
			case 1:
				reserveFlight();
				break;
			
			case 2:
				createAirport();
				break;
				
			case 3:
				createAirline();
				break;
				
			case 4:
				createFlight();
				break;
		}
	}
	
	public static void reserveFlight()
	{
		String origin;
		String destination;
		
		System.out.println("Enter origin airport: (3 letter acronym)");
		System.out.println("Available airports: " + airportNames);
		origin = sc.next();
		
		System.out.println("Enter destination airport: (3 letter acronym)");
		System.out.println("Available airports: " + airportNames);
		destination = sc.next();
		
		Flight.bookFlight(origin , destination);
	}
	
	public static void createAirport()
	{
		String airportName;
		String airportShort;
		
		if (adminLoggedIn == false)
		{
			Tools.errorMessage();
			System.out.println("Login as admin and try again!");
			login();
		}
		
		System.out.println("Enter airport name: ");
		sc.nextLine();
		airportName = sc.nextLine();
		
		System.out.println("Enter airport acronym: (e.g. JFK for 'John F. Kennedy International Airport')");
		airportShort = sc.next();
		
		Airport airport = new Airport(airportName, airportShort);
		
		if (airport.isCreated())
		{
			System.out.println("\nAirport successfully created!");
			System.out.println("Proceeding to menu...\n");
			menu();
		}
		
		else
		{
			Tools.errorMessage();
			createAirport();
		}
	}
	
	public static void createAirline()
	{
		String airlineName;
		
		if (adminLoggedIn == false)
		{
			Tools.errorMessage();
			System.out.println("Login as admin and try again!");
			login();
		}
		
		System.out.println("Enter airline name: (not more than 5 letters");
		sc.nextLine();
		airlineName = sc.nextLine();
		
		Airline airline = new Airline(airlineName);
		
		if (airline.isCreated())
		{
			System.out.println("\nAirline successfully created!");
			System.out.println("Proceeding to menu...\n");
			menu();
		}
		
		else
		{
			Tools.errorMessage();
			createAirline();
		}
	}
	
	public static void createFlight()
	{
		String airlineName;
		String origin;
		String destination;
		int seats;
		
		if (adminLoggedIn == false)
		{
			Tools.errorMessage();
			System.out.println("Login as admin and try again!");
			login();
		}
		
		System.out.println("Enter airline name: ");
		System.out.println("Available airlines: " + Airport.airlineNames);
		airlineName = sc.next();
		
		System.out.println("Enter origin airport: (3 letter acronym)");
		System.out.println("Available airports: " + airportNames);
		origin = sc.next();
		
		System.out.println("Enter destination airport: (3 letter acronym)");
		System.out.println("Available airports: " + airportNames);
		destination = sc.next();
		
		System.out.println("Choose the plane:\n1) Boeing 737 (24 seats)\n2) Boeing 747 (36 seats)\n3) Airbus A380 (48 seats)");
		seats = sc.nextInt();
		
		if (seats < 1 || seats > 3)
		{
			seats = 1;
		}
		
		Flight flight = new Flight(airlineName, origin, destination, seats);
		
		if (flight.isCreated())
		{
			System.out.println("\nFlight successfully created!");
			System.out.println("Proceeding to menu...\n");
			menu();
		}
		
		else
		{
			Tools.errorMessage();
			createFlight();
		}
	}
}