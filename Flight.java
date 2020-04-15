import java.util.ArrayList;

public class Flight 
{
	private String airlineName;
	private String origin;
	private String destination;
	private boolean created;
	private int seats;
	ArrayList <String> seatMap = new ArrayList <String> ();
	
	public Flight(String airlineName, String origin, String destination, int seats) 
	{
		setAirline(airlineName);
		setOrigin(origin);
		setDestination(origin , destination);
		setCreated(true);
		
		if (seats == 1)
		{
			setSeatMap(0 , 24);
			setSeats(4);
		}
		
		else if (seats == 2)
		{
			setSeatMap(0 , 36);
			setSeats(6);
		}
		
		else
		{
			setSeatMap(0 , 48);
			setSeats(8);
		}
		
		Airline.flights.add(this);
	}

	public String getAirline() 
	{
		return airlineName;
	}
	
	public void setAirline(String airlineName) 
	{
		for (int i = 0; i < Airport.airlines.size(); i++)
		{	
			if (Airport.airlines.get(i).getAirlineName().equals(airlineName))
			{
				this.airlineName = airlineName;
				return;
			}
		}
		
		Tools.errorMessage();
		System.out.println("Airline does not exist!");
		Main.createFlight();
	}
	
	public String getOrigin() 
	{
		return origin;
	}
	
	public void setOrigin(String origin) 
	{
		for (int i = 0; i < Main.airports.size(); i++)
		{
			if (Main.airports.get(i).getAirportShort().equals(origin))
			{
				this.origin = origin;
				return;
			}
		}
		
		Tools.errorMessage();
		System.out.println("Airport does not exist!");
		Main.createFlight();
	}
	
	public String getDestination() 
	{
		return destination;
	}
	
	public void setDestination(String origin , String destination) 
	{
		for (int i = 0; i < Main.airports.size(); i++)
		{
			if (Main.airports.get(i).getAirportShort().equals(destination))
			{
				if (destination.equals(origin) == false)
				{
					this.destination = destination;
					return;
				}
				
				else
				{
					Tools.errorMessage();
					System.out.println("Destination cannot be the same as origin!");
					Main.createFlight();
				}	
			}
		}
		
		for (int j = 0; j < Airline.flights.size(); j++)
		{
			if (Airline.flights.get(j).getDestination().equals(destination) && Airline.flights.get(j).getOrigin().equals(origin))
			{
				Tools.errorMessage();
				System.out.println("Flight already exists");
				Main.createFlight();
			}
		}
		
		Tools.errorMessage();
		System.out.println("Airport does not exist!");
		Main.createFlight();
	}
	
	public boolean isCreated() 
	{
		return created;
	}

	public void setCreated(boolean created)
	{
		this.created = created;
	}
		
	public ArrayList <String> getSeatMap() 
	{
		return seatMap;
	}

	public void setSeatMap(int seatNum , int seats) 
	{
		for (int i = 0; i < seats; i++)
		{
			if (i == (seatNum - 1))
			{
				seatMap.add("*");
			}
			
			else 
			{
				seatMap.add("-");
			}
		}
	}
	
	public int getSeats() 
	{
		return seats;
	}

	public void setSeats(int seats) 
	{
		this.seats = seats;
	}

	public static void bookFlight(String origin , String destination)
	{
		for (int i = 0; i < Airline.flights.size(); i++)
		{
			if (Airline.flights.get(i).getOrigin().equals(origin) || Airline.flights.get(i).getDestination().equals(destination))
			{
				System.out.println("A flight between " + origin + " and " + destination + " is hosted by " + Airline.flights.get(i).getAirline());
				System.out.println("Would you like to book this flight? (1 => yes, 0 => no)");
				
				int choice = Main.sc.nextInt();
				
				if (choice != 1)
				{
					System.out.println("Sorry we couldn't be of service.\nProceeding to menu...");
					Main.menu();
				}
				
				else
				{
					String rows = "ABCDEF";
					int seatsInRow = Airline.flights.get(i).seatMap.size() / 6;
					System.out.println("\n- => empty seat , * => taken seat\n");
					
					for (int x = 0; x < 6; x++)
					{
						System.out.print(rows.charAt(x) + " ");
						
						for (int y = 0; y < seatsInRow; y++)
						{
							System.out.print(Airline.flights.get(i).seatMap.get(x * seatsInRow + y));
						}
						
						System.out.println();
					}
					
					String row;
					int seat;
					int rowNum = 0;
					
					System.out.println("Enter which row you want to buy a seat in: ");
					row = Main.sc.next();
					row = row.toUpperCase();
					
					System.out.println("Enter which seat would you like sit in: (selected row : " + row + ")");
					seat = Main.sc.nextInt();
					
					if (row.equals("A"))
					{
						rowNum = 1;
					}
					
					else if (row.equals("B"))
					{
						rowNum = 2;
					}
					
					else if (row.equals("C"))
					{
						rowNum = 3;
					}
					
					else if (row.equals("D"))
					{
						rowNum = 4;
					}
					
					else if (row.equals("E"))
					{
						rowNum = 5;
					}
					
					else if (row.equals("F"))
					{
						rowNum = 6;
					}
					
					if (Airline.flights.get(i).seatMap.get((rowNum - 1) * seatsInRow + seat - 1).equals("*"))
					{
						Tools.errorMessage();
						System.out.println("Seat already taken!");
						bookFlight(origin, destination);
					}
					
					else
					{
						Airline.flights.get(i).seatMap.set(((rowNum - 1) * seatsInRow + seat - 1) , "*");
						System.out.println("\nFlight successfully booked!");
						System.out.println("Proceeding to menu...");
						Main.menu();
					}
				}
			}
		}
	}
}