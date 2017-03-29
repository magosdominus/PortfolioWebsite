package cormierm;

/**
 * CustomerDA - this file is contains all of the data access methods, that actually get/set data to the database. 
 * Note: that all the methods are static this is because you do not really create CustomerDA objects (does not make sense)
 * @author Darren Puffer
 * @author Matthew Cormier
 * @version 6.0 (03/31/2016)
 * @since 5.0
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.sql.*;

import javax.servlet.http.HttpSession;

import com.sun.javafx.scene.web.Debugger;

public class StudentDA
{
	/**
	 * Student object.
	 */
	static Student aStudent;
	
	// declare variables for the database connection
	/**
	 * Connection object.
	 */
	static Connection aConnection;
	/**
	 * Statement object.
	 */
	static Statement aStatement;

	// declare static variables for all Student instance attribute values
	/**
	 * User ID number.
	 */
	protected static long userID;
	/**
	 * User's password.
	 */
	protected static String userPassword;
	/**
	 * User's first name.
	 */
	protected static String firstName;
	/**
	 * User's last name.
	 */
	protected static String lastName;
	/**
	 * The date the user enrolled.
	 */
	protected static Date enrolDate;
	/**
	 * Date the user last accessed the system.
	 */
	protected static Date lastAccess;
	/**
	 * User's status.
	 */
	protected static boolean userStatus;
	/**
	 * User's user type.
	 */
	protected static char userType;
	/**
	 * User's email address.
	 */
	protected static String emailAddress;
	/**
	 * Course code number.
	 */
	protected static String programCode;
	/**
	 * Description of the program.
	 */
	protected static String programDescription;
	/**
	 * Year the student is currently enrolled in.
	 */
	protected static int year;

	/**
	 * Vector that will hold marks.
	 */
	protected static Vector<Mark> marks = new Vector<Mark>();
	
	/**
	 * Date formatter for formatting dates into database compatible strings.
	 */
	private static final SimpleDateFormat SQL_DF = new SimpleDateFormat("yyyy-MM-dd");

	// establish the database connection
	/**
	 * Creates a statement object.
	 * @param c
	 */
	public static void initialize(Connection c)
	{
            try {
                aConnection=c;
                aStatement=aConnection.createStatement();
            }
            catch (SQLException e)
            { System.out.println(e);	}
	}

	// close the database connection
	/**
	 * Closes a statement/connection.
	 */
	public static void terminate()
	{
            try
            { 	// close the statement
                aStatement.close();
            }
            catch (SQLException e)
            { System.out.println(e);	}
	}
	
	/**
	 * Returns true if a record with the userID passed exists.
	 * @param key
	 * @return bool
	 */
	public static boolean isExistingLogin(String key)
	{
		
		boolean output = false;
		
		// retrieve Student and Boat data
		aStudent = null;
		// define the SQL query statement using the phone number key
		String sqlQuery = "SELECT * " +
		                 " FROM students " +
		                 " WHERE id = '" + key + "'" ;
		//System.out.println(sqlQuery);
		// execute the SQL query statement
		try
		{
			ResultSet rs = aStatement.executeQuery(sqlQuery);
			// next method sets cursor & returns true if there is data
			boolean gotIt = rs.next();
			if (gotIt)
			{
				output = true;
			}
		
	   	}catch (SQLException e)
		{ System.out.println(e);}
		
		return output;
	}
	
	/**
	 * Retrieves a student object form the database.
	 * @param key for the student ID
	 * @return Student object
	 * @throws NotFoundException
	 * @throws InvalidUserDataException 
	 * @throws StudentNotFoundException 
	 */
	public static Student retrieve(Long key) throws InvalidUserDataException, StudentNotFoundException
	{ // retrieve Student and Boat data
		aStudent = null;
		// define the SQL query statement using the phone number key
		String sqlQuery = "SELECT * " +
                                    " FROM students " +
                                    " WHERE id = '" + key + "'" ;
                //System.out.println(sqlQuery);
	 	// execute the SQL query statement
		try
 		{
                    ResultSet rs = aStatement.executeQuery(sqlQuery);
                    // next method sets cursor & returns true if there is data
                    boolean gotIt = rs.next();
                    if (gotIt)
                    {	// extract the data
			
			userID = rs.getLong("id");
			userPassword = rs.getString("password");
			firstName = rs.getString("firstname");
			lastName = rs.getString("lastname");
			enrolDate = rs.getDate("enroldate");
			lastAccess = rs.getDate("lastaccess");
			userStatus = rs.getBoolean("enabled");
			emailAddress = rs.getString("emailaddress");
			programCode = rs.getString("programcode");
			programDescription = rs.getString("programdescription");
			year = rs.getInt("year");
			
			// create Customer
                        try{
                            aStudent = new Student(userID, userPassword, firstName, lastName, enrolDate, lastAccess, userStatus, userType, emailAddress, programCode, programDescription, year);
                        }catch (InvalidUserDataException e)
                        { System.out.println("Record for " + key + " contains an invalid userID. Verify and correct.");}
                        
                    } else	// nothing was retrieved
                    {//throw (new StudentNotFoundException("Problem retrieving Customer record, userID " + key + " does not exist in the system."));}
                    rs.close();
                    }
	   	}catch (SQLException e)
		{ System.out.println(e);}
                
		return aStudent;
	}


	/**
	 * Create a student record.
	 * @param aStudent
	 * @return boolean
	 * @throws DuplicateUserException
	 * @throws InvalidUserDataException 
	 * @throws DuplicateException 
	 * @throws NotFoundException 
	 * @throws StudentNotFoundException 
	 * @throws SQLException 
	 */
	public static boolean create(Student aStudent) throws DuplicateUserException, InvalidUserDataException, DuplicateException, NotFoundException, StudentNotFoundException, SQLException
	{	
		boolean inserted = false; //insertion success flag		
		
		try
		{
			if (Student.retrieve(aStudent.userID) == null)
			{
				// retrieve the student attribute values
				userID = aStudent.getUserID();
				userPassword = aStudent.getUserPassword();
				firstName = aStudent.getFirstName();
				lastName = aStudent.getLastName();
				enrolDate = aStudent.getEnrolDate();
				lastAccess = aStudent.getLastAccess();
				userStatus = aStudent.isUserStatus();
				emailAddress = aStudent.getEmailAddress();
				programCode = aStudent.getProgramCode();
				programDescription = aStudent.getProgramDescription();
				year = aStudent.getYear();
				marks = aStudent.getMarks();
				userType = 's';
				
				// create the SQL insert statement using attribute values
				String sqlInsert = "INSERT INTO students " +
		                                    "(id, password, firstname, lastname, emailaddress, enroldate, lastaccess, enabled, type, programcode, programdescription, year) " +
		                                    "VALUES ('" + userID + "', '" +
		                                    userPassword + "', '" +
		                                    firstName + "', '" +
		                                    lastName + "', '" +
		                                    emailAddress + "', '" +
		                                    SQL_DF.format(enrolDate) + "', '" +
		                                    SQL_DF.format(lastAccess) + "', '" +
		                                    userStatus + "', '" +
		                                    userType + "', '" +
		                                    programCode + "', '" +
		                                    programDescription + "', '" +
		                                    year + "')";
		
				try
	 			{  // execute the SQL update statement
		    		inserted = aStatement.execute(sqlInsert);
				}
				finally{}
	
			}
			else
			{
				throw new DuplicateException("Student " + aStudent.getUserID() + " is already in the database.");
			}
		}
		finally
		{}
		
		return inserted;
	}

	/**
	 * Deletes a student record.
	 * @param aStudent
	 * @return
	 * @throws NotFoundException
	 * @throws InvalidUserDataException 
	 * @throws StudentNotFoundException 
	 */
	public static int delete(Student aStudent) throws NotFoundException, InvalidUserDataException, StudentNotFoundException
	{	
		int records = 0;
		// retrieve the userID (key)
		userID = aStudent.getUserID();
		// create the SQL delete statement
		String sqlDelete = "DELETE FROM students " +
                                    "WHERE id = '" + userID +"'";

		// see if this customer already exists in the database
		try
		{
			StudentDA.retrieve(userID);  //used to determine if record exists for the passed Customer
    		// if found, execute the SQL update statement
    		records = aStatement.executeUpdate(sqlDelete);
		}catch(StudentNotFoundException e)
		{
			throw new NotFoundException("Studnet with userID " + userID 
					+ " cannot be deleted, does not exist.");
		}catch (SQLException e)
			{ System.out.println(e);	}
		return records;
	}
	
	
	/**
	 * Updates student record.
	 * @param aStudent
	 * @return 
	 * @throws NotFoundException
	 * @throws InvalidUserDataException 
	 * @throws StudentNotFoundException 
	 * @throws SQLException 
	 */
	public static int Update(Student aStudent) throws DuplicateUserException, InvalidUserDataException, DuplicateException, NotFoundException, StudentNotFoundException, SQLException
	{	
		int inserted = 0; //insertion success flag		
		
		try
		{
			if (Student.retrieve(aStudent.userID) != null)
			{
		// retrieve the customer argument attribute values
		userID = aStudent.getUserID();
		userPassword = aStudent.getUserPassword();
		firstName = aStudent.getFirstName();
		lastName = aStudent.getLastName();
		enrolDate = aStudent.getEnrolDate();
		lastAccess = aStudent.getLastAccess();
		userStatus = aStudent.isUserStatus();
		emailAddress = aStudent.getEmailAddress();
		programCode = aStudent.getProgramCode();
		programDescription = aStudent.getProgramDescription();
		year = aStudent.getYear();

		// define the SQL query statement using the phone number key
		String sqlUpdate = "UPDATE students " +
                "SET " +			
                "password 			='" + userPassword 				+ "', " +
                "firstname 			='" + firstName 				+ "', " +
                "lastname 			='" + lastName 					+ "', " +
                "emailaddress 		='" + emailAddress 				+ "', " +
                "enroldate 			='" + SQL_DF.format(enrolDate) 	+ "', " +
                "lastaccess 		='" + SQL_DF.format(lastAccess) + "', " +
                "enabled 			='" + userStatus 				+ "', " +
                "programcode 		='" + programCode 				+ "', " +
                "programdescription ='" + programDescription 		+ "', " +
                "year 				='" + year 						+ "' " +
                " WHERE id 			='" + Math.toIntExact(userID) + "'";

			try
				{  // execute the SQL update statement
				
	    		inserted = aStatement.executeUpdate(sqlUpdate);
	    		
			}
			finally{}
		}
		else
		{
			throw new DuplicateException("Student " + aStudent.getUserID() + " does not exist in the database.");
		}
	}
	finally
	{}
	
	return inserted;
}

	
	/**
	 * Searches for a student record in the database based on userID and password.
	 * @param key
	 * @param password
	 * @return Student object
	 * @throws StudentNotFoundException
	 */
	public static Student Login(long key, String password) throws StudentNotFoundException
	{
			// retrieve Student and Boat data
			aStudent = null;
			// define the SQL query statement using the phone number key
			String sqlQuery = "SELECT * " +
	                                    " FROM students " +
	                                    " WHERE id = '" + key + "'" + "AND password = '" + password + "'";
	                
		 	// execute the SQL query statement
			try
	 		{
	            ResultSet rs = aStatement.executeQuery(sqlQuery);
	            // next method sets cursor & returns true if there is data
	            boolean gotIt = rs.next();
	            if (gotIt)
	            {	
	            	// extract the data
				
					userID = rs.getLong("id");
					userPassword = rs.getString("password");
					firstName = rs.getString("firstname");
					lastName = rs.getString("lastname");
					enrolDate = rs.getDate("enroldate");
					lastAccess = rs.getDate("lastaccess");
					userStatus = rs.getBoolean("enabled");
					emailAddress = rs.getString("emailaddress");
					programCode = rs.getString("programcode");
					programDescription = rs.getString("programdescription");
					year = rs.getInt("year");
					

					// create Customer
		            try
		            {
		            	aStudent = new Student(userID, userPassword, firstName, lastName, enrolDate, lastAccess, userStatus, userType, emailAddress, programCode, programDescription, year);
		            }
		            catch (InvalidUserDataException e)
		            { 
		            	System.out.println("Record for " + key + " contains an invalid userID. Verify and correct.");}
		                        
		            } 
	            else	// nothing was retrieved
	            {
	            	throw (new StudentNotFoundException("Problem retrieving Student record, userID " + key + " does not exist in the system."));}
	                rs.close();
		   		}
				catch (SQLException e)
				{ 
					System.out.println(e);
				}
	                
			return aStudent;
		}


}

