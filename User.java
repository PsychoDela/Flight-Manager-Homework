public class User 
{
	private String username;
	private String password;
	private boolean created;
	
	public User(String username, String password) 
	{
		setUsername(username);
		setPassword(password);
		setCreated(true);
		Airport.users.add(this);
	}
	
	public static boolean loggedIn(String username , String password)
	{
		for (int i = 0; i < Airport.users.size(); i++)
		{
			if (Airport.users.get(i).getUsername().equals(username))
			{
				if(Airport.users.get(i).getPassword().equals(password))
				{
					return true;
				}
				
				Tools.errorMessage();
				System.out.println("Password doesn't match!");
				return false;
			}
		}
		
		Tools.errorMessage();
		System.out.println("Username doesn't exist!");
		return false;
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username)
	{
		for (int i = 0; i < Airport.users.size(); i++)
		{
			if (Airport.users.get(i).getUsername().equals(username))
			{
				Tools.errorMessage();
				System.out.println("Username already exists!");
				Main.createAccount();
			}
		}
		
		if (username.length() < 4)
		{
			System.out.println("\nUsername must not be less than 4 characters!");
			Tools.errorMessage();
			Main.createAccount();
		}
		
		else
		{
			this.username = username;
		}
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password)
	{
		if (password.length() < 8)
		{
			System.out.println("\nPassword must not be less than 8 characters!");
			Tools.errorMessage();
			Main.createAccount();
		}
		
		else
		{
			this.password = password;
		}
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
