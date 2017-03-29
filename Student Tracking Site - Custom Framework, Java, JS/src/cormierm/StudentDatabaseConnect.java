package cormierm;

/**
 * CustomerDatabaseConnect - Used to manage database connectivity (opening and closing connections)
 * @author Darren Puffer
 * @author Matthew Cormier
 * @version 5.0 (03/16/2016)
 * @since 5.0
 */

import java.sql.*;

public class StudentDatabaseConnect
{
	/**
	 * Database location
	 */
	static String url = "jdbc:postgresql://127.0.0.1:5432/wedj4203_db";
	/**
	 * Connection object to open port to db
	 */
	static Connection aConnection;
	/**
	 * database user
	 */
	static String user = "wedj4203_admin";
	/**
	 * database user password
	 */
	static String password = "wedj4203_password";
	
	/**
	 * establishes the database connection
	 * @return Connection to the wedj4203example_db database
	 */
	public static Connection initialize()
	{
		try
 		{ 	
			Class.forName("org.postgresql.Driver"); // loads the JDBC Driver for PostGreSQL
			aConnection = DriverManager.getConnection(url, user, password); // creates the database connection instance
	    	
	 	}
		catch (ClassNotFoundException e)  //will occur if you did not import the PostGreSQL *.jar file with drivers
		{
			System.out.println(e);
		}
		catch (SQLException e)	//any other database exception (misnamed db, misnamed user, worng password, etc)
			{ System.out.println(e); }
		return aConnection;
	}

	/**
	 * closes the database connection
	 */
	public static void terminate()
	{
		try
 		{
    		aConnection.close();
		}
		catch (SQLException e)
			{ System.out.println(e);	}
	}
}
