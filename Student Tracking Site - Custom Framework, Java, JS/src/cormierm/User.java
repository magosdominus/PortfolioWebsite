package cormierm;

import java.sql.SQLException;
import java.text.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: This class represents users. This class will hold user information for processing. The class will be abstract.
 * and outputting.
 * @author Matthew Cormier
 * @version 5.0 (03/16/2016)
 * @since 1.0
 */

public abstract class User {
	
	// Instance attributes 
	
	/**
	 * User ID number.
	 */
	protected long userID;
	/**
	 * User's password.
	 */
	protected String userPassword;
	/**
	 * User's first name.
	 */
	protected String firstName;
	/**
	 * User's last name.
	 */
	protected String lastName;
	/**
	 * The date the user enrolled.
	 */
	protected Date enrolDate;
	/**
	 * Date the user last accessed the system.
	 */
	protected Date lastAccess;
	/**
	 * User's status.
	 */
	protected boolean userStatus;
	/**
	 * User's user type.
	 */
	protected char userType;
	/**
	 * User's email address.
	 */
	protected  String emailAddress;
	


	// date formatting object.
	public DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
	
	// Getters and setters for each attribute.
		
	protected String getEmailAddress() {
		return emailAddress;
	}
	protected void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public long getUserID() {
		return userID;
	}
	protected void setUserID(long userID) throws InvalidIdException {
		
		try{
			if (!this.verifyID(userID))
			{
				throw new InvalidIdException(userID + " is not a valid ID.");
			}
			this.userID = userID;
		}
		// If an invalidIDException is thrown in the try block.
		catch(InvalidIdException e)
		{
			throw e;
		}
		
	}
	protected String getUserPassword() {
		return userPassword;
	}
	protected void setUserPassword(String userPassword) throws InvalidPasswordException {
		
		try
		{
			if (userPassword.length() < MINIMUM_PASSWORD_LENGTH || userPassword.length() > MAXIMUM_PASSWORD_LENGTH)
			{
				throw new InvalidPasswordException("The user password must be between " + MINIMUM_PASSWORD_LENGTH + " and " + MAXIMUM_PASSWORD_LENGTH  + " characters long.");
			}
		}
		catch (InvalidPasswordException e)
		{
			throw e;
		}
		
		
		this.userPassword = userPassword;
	}
	public String getFirstName() {
		return firstName;
	}
	protected void setFirstName(String firstName) throws InvalidNameException {
		
		try
		{
			if (firstName.isEmpty())
			{
				throw new InvalidNameException("You must provide a first name.");
			}
		}
		catch(InvalidNameException e)
		{
			throw e;
		}
		
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	protected void setLastName(String lastName) throws InvalidNameException {
		
		try
		{
			if (lastName.isEmpty())
			{
				throw new InvalidNameException("You must provide a last name.");
			}
		}
		catch(InvalidNameException e)
		{
			throw e;
		}
		
		this.lastName = lastName;
	}
	protected Date getEnrolDate() {
		return enrolDate;
	}
	protected void setEnrolDate(Date enrolDate) {
		this.enrolDate = enrolDate;
	}
	protected Date getLastAccess() {
		return lastAccess;
	}
	protected void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}
	protected boolean isUserStatus() {
		return userStatus;
	}
	protected void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}
	protected char getUserType() {
		return userType;
	}
	protected void setUserType(char userType) {
		this.userType = userType;
	}
	
	// Class attributes 
	
	/**
	 * Default user ID.
	 */
	protected static Long DEFAULT_ID = Long.valueOf(100123456);
	/**
	 * Default password.
	 */
	protected static String DEFAULT_PASSWORD = "password";
	/**
	 * Default first name.
	 */
	protected static String DEFAULT_FIRST_NAME = "John";
	/**
	 * Default last name.
	 */
	protected static String DEFAULT_LAST_NAME = "Doe";
	/**
	 * Default status.
	 */
	protected static boolean DEFAULT_ENABLED_STATUS = true;
	/**
	 * Default user type.
	 */
	protected static char DEFAULT_TYPE = 's';
	/**
	 * Number length limit of the id.
	 */
	protected static byte ID_NUMBER_LENGTH = 9;
	/**
	 * Formatter for dates.
	 */
	protected static DateFormat DF;
	
	/**
	 * Default user email address.
	 */
	public static final String DEFAULT_EMAIL_ADDRESS = "john.doe@dcmail.com";
	
	/**
	 * Minimum password length.
	 */
	protected static final int MINIMUM_PASSWORD_LENGTH = 6;
	
	/**
	 * Maximum password length.
	 */
	protected static final int MAXIMUM_PASSWORD_LENGTH = 12;

	// Constructors 
	
	/**
	 * Default constructor
	 * @throws InvalidUserDataException 
	 */
	public User() throws InvalidUserDataException 	
	{
		// Call the parameterized constructor with the default class attribute values.
		this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME,  new Date(), new Date(), DEFAULT_ENABLED_STATUS, DEFAULT_TYPE, DEFAULT_EMAIL_ADDRESS);
	}

	/**
	 * Parameterized constructor that takes long for userId.
	 * @throws InvalidUserDataException 
	 */
	public User(Long userID, String userPassword, String firstName, String lastName, Date enrolDate, Date lastAccess, boolean userStatus, char userType, String emailAddress) throws InvalidUserDataException	
	{
		try
		{
			// Set each attribute.
			setUserID(userID);
			setUserPassword(userPassword);
			setFirstName(firstName);
			setLastName(lastName);
			setEnrolDate(enrolDate);
			setLastAccess(lastAccess);
			setUserStatus(userStatus);
			setUserType(userType);
			setEmailAddress(emailAddress);
		}
		catch(Exception e)
		{
			throw new InvalidUserDataException(e.getMessage());
		}
	}
	
	/**
	 * Parameterized constructor that takes string for userId.
	 * @throws InvalidUserDataException 
	 * @throws NumberFormatException 
	 */
	public User(String userID, String userPassword, String firstName, String lastName, Date enrolDate, Date lastAccess, boolean userStatus, char userType, String emailAddress) throws NumberFormatException, InvalidUserDataException	
	{
		// Call the parameterized constructor with the passed values.
		this(Long.valueOf(userID), userPassword, firstName, lastName, enrolDate, lastAccess, userStatus, userType, emailAddress);
	}
	
	// Methods 
	
	// overload toString()
	// @return the user information as a string.
	/**
	 * Creates and returns a string displaying the class information.
	 * @return the user information as a string.
	 */
	public String toString() 
	{
		String userInformation = "\n\nUser Info for: " + String.valueOf(getUserID() + 
								 "\nPassword: " + getUserPassword().replaceAll("[ ~!@#$%^&*()_+=-`<>,./?a-zA-Z0-9]", "*") +
								 "\nName: " + getFirstName() + " " + getLastName() + 
								 "\nCreated on: " + dateFormat.format(getEnrolDate()) +
								 "\nLast Access: " + dateFormat.format(getLastAccess()) +
								 "\nType: " + getUserType() +
								 "\nEnabled: " + isUserStatus()) +
								 "\nEmail Address: " + getEmailAddress();
	
		return userInformation;
	}
	
	// displayToConsole()
	// no return value, output the user info to the console by calling toString().
	/**
	 * Outputs the string returned from toString().
	 */
	public void displayToConsole()
	{
		System.out.println(toString());
	}
	
	// verifyID
	// No return value, accept the userID and check if the length is valid.
	/**
	 * Validates the userID.
	 * Throws an InvalidIdException if the userID is not the correct length or is negative.
	 * @throws InvalidIdException 
	 */
	public boolean verifyID(long newID) throws InvalidIdException
	{
		long userID = newID;
		boolean valid = true;
		
		// Convert userID value to a string
		String testString = String.valueOf(userID);

		// Check if the string length is equal to the ID valid length.
		// assign resulting message to a string for output.
		if (testString.length() != ID_NUMBER_LENGTH)
		{
			valid = false;
		}
		// Check if the number is negative.
		if (userID < 0)
		{
			valid = false;
		}
		
		return valid;
	}
	
	/**
	 * Abstract method for use by child classes.
	 * @return boolean
	 * @throws StudentNotFoundException 
	 * @throws NotFoundException 
	 * @throws DuplicateException 
	 * @throws SQLException 
	*/
	public abstract boolean create() throws DuplicateUserException, InvalidUserDataException, DuplicateException, NotFoundException, StudentNotFoundException, SQLException ;
	
	/**
	 * Abstract method for use by child classes.
	 * @return int
	 * @throws StudentNotFoundException 
	 * @throws DuplicateException 
	 * @throws DuplicateUserException 
	 * @throws SQLException 
	*/
	public abstract int update() throws NotFoundException, InvalidUserDataException, StudentNotFoundException, DuplicateUserException, DuplicateException, SQLException;
	
	/**
	 * Abstract method for use by child classes.
	 * @return int
	 * @throws StudentNotFoundException 
	*/
	public abstract int delete() throws NotFoundException, InvalidUserDataException, StudentNotFoundException;

	public static boolean isValidEmailAddress(String email)
	{
        String EMAIL_REGIX = "^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\[\\\\w!#$%&’*+/=?`{|}~^-]+)*(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(EMAIL_REGIX);
        Matcher matcher = pattern.matcher(email);
        return ((!email.isEmpty()) && (email!=null) && (matcher.matches()));
	}

}
