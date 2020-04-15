import java.util.ArrayList;

public class Airport 
{
	private String airportName;
	private String airportShort;
	private boolean created;
	static ArrayList <User> users = new ArrayList <User> ();
	static ArrayList <Airline> airlines = new ArrayList <Airline> ();
	static ArrayList <String> airlineNames = new ArrayList <String> ();

	public Airport(String airportName, String airportShort) 
	{
		setAirportName(airportName);
		setAirportShort(airportShort);
		setCreated(true);
		Main.airports.add(this);
		Main.airportNames.put(airportShort, airportName);
	}

	public String getAirportName() 
	{
		return airportName;
	}

	public void setAirportName(String airportName) 
	{
		for (int i = 0; i < Main.airports.size(); i++)
		{
			if (Main.airports.get(i).getAirportName().equals(airportName))
			{
				Tools.errorMessage();
				System.out.println("Airport already exists!");
				Main.createAirport();
			}
		}
		
		this.airportName = airportName;
	}

	public String getAirportShort() 
	{
		return airportShort;
	}

	public void setAirportShort(String airportShort) 
	{
		if (airportShort.length() > 3)
		{
			Tools.errorMessage();
			System.out.println("Airport acronym MUST be 3 letters!");
			Main.createAccount();
		}
		
		for (int i = 0; i < Main.airports.size(); i++)
		{
			if (Main.airports.get(i).getAirportShort().equals(airportShort))
			{
				Tools.errorMessage();
				System.out.println("Airport already exists!");
				Main.createAirport();
			}
		}
		
		this.airportShort = airportShort;
	}

	public boolean isCreated() 
	{
		return created;
	}

	public void setCreated(boolean created) 
	{
		this.created = created;
	}
}
