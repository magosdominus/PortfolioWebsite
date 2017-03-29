package cormierm;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Vector;
import java.util.Iterator;

/**
 * Description: This class represents a student, inherits from the User class.
 * 
 * @author Matthew Cormier
 * @version 5.0 (03/16/2016)
 * @since 2.0
 */

public class Student extends User implements CollegeInterface {
	
	// Instance attributes 
	
	/**
	 * Course code number.
	 */
	protected String programCode;
	/**
	 * Description of the program.
	 */
	protected String programDescription;
	/**
	 * Year the student is currently enrolled in.
	 */
	protected int year;

	/**
	 * Vector that will hold marks.
	 */
	protected Vector<Mark> marks = new Vector<Mark>();
	
	// Getters and setters for each attribute.
	
	public Vector<Mark> getMarks() {
		return marks;
	}

	public void setMarks(Vector<Mark> marks) {
		this.marks = marks;
	}

	public String getProgramCode() {
		return programCode;
	}
	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public String getProgramDescription() {
		return programDescription;
	}

	public void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
	// Class attributes 
	
	/**
	 * Default program code number.
	 */
	protected static final String DEFAULT_PROGRAM_CODE = "UNDC";
	/**
	 * Default description of the program.
	 */
	protected static final String DEFAULT_PROGRAM_DESCRIPTION = "Undeclared";
	/**
	 * Default year of program.
	 */
	protected static final int DEFAULT_YEAR = 1; 
	
	// decimal formatting object.
	
	/**
	 * Decimal formatters to one decimal place with a leading zero.
	 */
	public static DecimalFormat decimalFormat = new DecimalFormat("0.0");
	
	// Parameterized constructor
	
	/* Implicit super constructor User() is undefined. Must explicitly invoke another constructor.
	 * 
	 * This constructor does not set the parent class attributes, so without the parent class default constructor this error is given.
	 * 
	 */
	
	/**
	 * Parameterized constructor that accepts long for userID.
	 * @throws InvalidUserDataException 
	 */
	public Student(Long userID, String userPassword, String firstName, String lastName, Date enrolDate, Date lastAccess, boolean userStatus, char userType, String emailAddress, String programCode, String programDescription, int year) throws InvalidUserDataException
	{
		this(Long.valueOf(userID), userPassword, firstName, lastName, enrolDate, lastAccess, userStatus, userType, emailAddress, programCode, programDescription, year, new Vector<Mark>());
	}
	
	// Default constructor
	
	/**
	 * Default constructor.
	 * @throws InvalidUserDataException 
	 */
	public Student() throws InvalidUserDataException
	{
		// Call the parameterized constructor with the default class attribute values.
		this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME,  new Date(), new Date(), DEFAULT_ENABLED_STATUS, DEFAULT_TYPE, DEFAULT_EMAIL_ADDRESS, DEFAULT_PROGRAM_CODE, DEFAULT_PROGRAM_DESCRIPTION, DEFAULT_YEAR, new Vector<Mark>());
	}
	
	// parameterized constructor
	// accepts string for userID and converts it to a long before calling the first parameterized constructor.
	
	/**
	 * Parameterized constructor that accepts String for userID.
	 * @throws InvalidUserDataException 
	 * @throws NumberFormatException 
	 */
	public Student(String userID, String userPassword, String firstName, String lastName, Date enrolDate, Date lastAccess, boolean userStatus, char userType, String emailAddress, String programCode, String programDescription, int year) throws NumberFormatException, InvalidUserDataException	
	{	
		// Call the parameterized constructor with the passed values.
		this(Long.valueOf(userID), userPassword, firstName, lastName, enrolDate, lastAccess, userStatus, userType, emailAddress, programCode, programDescription, year, new Vector<Mark>());
	}
	
	// Constructors with mark vector parameters
	
	/**
	 * Parameterized constructor that accepts long for userID.
	 * @throws InvalidUserDataException 
	 */
	public Student(Long userID, String userPassword, String firstName, String lastName, Date enrolDate, Date lastAccess, boolean userStatus, char userType, String emailAddress, String programCode, String programDescription, int year, Vector<Mark> marks) throws InvalidUserDataException
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
			setProgramCode(programCode);
			setProgramDescription(programDescription);
			setYear(year);
			setMarks(marks);
		}
		catch(Exception e)
		{
			throw new InvalidUserDataException(e.getMessage());
		}
	}
	
	/**
	 * Parameterized constructor that accepts String for userID.
	 * @throws InvalidUserDataException 
	 */
	public Student(String userID, String userPassword, String firstName, String lastName, Date enrolDate, Date lastAccess, boolean userStatus, char userType, String emailAddress, String programCode, String programDescription, int year, Vector<Mark> marks) throws InvalidUserDataException
	{
		try
		{
			// Set each attribute.
			setUserID(Long.valueOf(userID));
			setUserPassword(userPassword);
			setFirstName(firstName);
			setLastName(lastName);
			setEnrolDate(enrolDate);
			setLastAccess(lastAccess);
			setUserStatus(userStatus);
			setUserType(userType);
			setEmailAddress(emailAddress);
			setProgramCode(programCode);
			setProgramDescription(programDescription);
			setYear(year);
			setMarks(marks);
		}
		catch(Exception e)
		{
			throw new InvalidUserDataException(e.getMessage());
		}
	}
	// overload toString()
	// @return the user information as a string.
	
	/**
	 * Overridden toString() method.
	 * @return the user information as a string.
	 */
	public String toString() 
	{
		String yearOutput;
		
		switch (getYear()) {
			case 1: yearOutput = getYear() + "st";
					break;
			case 2: yearOutput = getYear() + "nd";
					break;
			case 3: yearOutput = getYear() + "rd";
					break;
			case 4: yearOutput = getYear() + "th";
					break;
			default:yearOutput = String.valueOf(getYear());
					break;
		}			
		
		String studentInformation = "\n\n" + getTypeForDisplay() + " Info for: \n" +
					 "\t" + getFirstName() + " " + getLastName() + " (" + getUserID() + ")" +
					 "\n\tCurrently in " + yearOutput + " year of " + getProgramDescription() + " (" + getProgramCode() + ")" + "\n\tat "+ CollegeInterface.COLLEGE_NAME + 
					 "\n\tEnrolled: " + dateFormat.format(getEnrolDate()) + "\n";
		
		if (marks.size() > 0)
		{
			Iterator i = marks.iterator();

			while (i.hasNext())
			{
				studentInformation += "\n" + i.next().toString();
			}
		}
		else
		{
			studentInformation += "\n\tNo marks on record.";
		}
		
		return studentInformation;
	}
	
	/**
	 * Initializes connection to database.
	 * @param c
	 */
	public static void initialize(Connection c)
	{
		StudentDA.initialize(c);
	}
	
	/**
	 * Terminates connection to database.
	 */
	public static void terminate()
	{
		StudentDA.terminate();
	}
	
	/**
	 * Calls StudentDa retrieve to search for passed userID in database.
	 * @param key
	 * @throws NotFoundException
	 * @throws InvalidUserDataException
	 * @throws StudentNotFoundException 
	 */
	public static Student retrieve(long key) throws NotFoundException, InvalidUserDataException, StudentNotFoundException
	{
		return StudentDA.retrieve(key);
	}
	
	/**
	 * For use with a database in a future version.
	 * @return boolean
	 * @throws InvalidUserDataException 
	 * @throws DuplicateUserException 
	 * @throws StudentNotFoundException 
	 * @throws NotFoundException 
	 * @throws DuplicateException 
	 * @throws SQLException 
	 */
	@Override
	public boolean create() throws DuplicateUserException, InvalidUserDataException, DuplicateException, NotFoundException, StudentNotFoundException, SQLException {
		// TODO Auto-generated method stub
		if(StudentDA.create(this))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Updates student record.
	 * @return boolean
	 * @throws InvalidUserDataException 
	 * @throws NotFoundException 
	 * @throws StudentNotFoundException 
	 * @throws DuplicateException 
	 * @throws DuplicateUserException 
	 * @throws SQLException 
	 */
	@Override
	public int update() throws NotFoundException, InvalidUserDataException, StudentNotFoundException, DuplicateUserException, DuplicateException, SQLException {
		// TODO Auto-generated method stub
		return StudentDA.Update(this);
	}

	/**
	 * Deletes student object.
	 * @return 0
	 * @throws InvalidUserDataException 
	 * @throws NotFoundException 
	 * @throws StudentNotFoundException 
	 */
	@Override
	public int delete() throws NotFoundException, InvalidUserDataException, StudentNotFoundException {
		// TODO Auto-generated method stub
		StudentDA.delete(this);
		
		return 0;
	}
	
	/**
	 * Display the object type, inherited from CollegeInterface.
	 * @return Student type.
	 */
	@Override
	public String getTypeForDisplay() {
		// TODO Auto-generated method stub
		return "Student";
	}
	
	/**
	 * Searches for student in database based on userID and password.
	 * @return
	 * @throws NotFoundException
	 * @throws InvalidUserDataException
	 * @throws StudentNotFoundException
	 */
	public static Student login(Long key, String password) throws NotFoundException, InvalidUserDataException, StudentNotFoundException {
		// TODO Auto-generated method stub
		return StudentDA.Login(key, password);
	}

	/**
	 * Returns true if a record with the userID passed exists.
	 * @param key
	 * @return bool
	 */
	public static boolean isExistingLogin(String key)
	{
		return StudentDA.isExistingLogin(key);
	}
}
