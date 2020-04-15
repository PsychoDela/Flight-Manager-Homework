import java.util.ArrayList;

public class Airline 
{
	private String airlineName;
	private boolean created;
	static ArrayList <Flight> flights = new ArrayList <Flight> ();

	public Airline(String airlineName) 
	{
		setAirlineName(airlineName);
		setCreated(true);
		Airport.airlines.add(this);
		Airport.airlineNames.add(airlineName);
	}

	public String getAirlineName() 
	{
		return airlineName;
	}

	public void setAirlineName(String airlineName) 
	{
		for (int i = 0; i < Airport.airlines.size(); i++)
		{
			if (Airport.airlines.get(i).getAirlineName().length() > 5)
			{
				Tools.errorMessage();
				System.out.println("Airline name cannot be longer than 5 characters!");
				Main.createAirline();
			}
			
			if (Airport.airlines.get(i).getAirlineName().equals(airlineName))
			{
				Tools.errorMessage();
				System.out.println("Two airlines cannow have the same name!");
				Main.createAirline();
			}
		}
		
		this.airlineName = airlineName;
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
