package cormierm;

import java.text.DecimalFormat;
import java.util.Date;
	
	/**
	 * Description: This class represents a faculty member, inherits from the User class.
	 * 
	 * @author Matthew Cormier
	 * @version 5.0 (03/16/2016)
	 * @since 2.0
	 */

public class Faculty extends User implements CollegeInterface {

		// Instance attributes 
		
		/**
		 * Code for the member's school department.
		 */
		private String schoolCode;
		/**
		 * Description of the school department.
		 */
		private String schoolDescription;
		/**
		 * Member's office location.
		 */
		private String office;
		/**
		 * Member's phone extension
		 */
		private int extension;

		// Getters and setters for each attribute.
		
		public String getSchoolCode() {
			return schoolCode;
		}

		public void setSchoolCode(String schoolCode) {
			this.schoolCode = schoolCode;
		}

		public String getSchoolDescription() {
			return schoolDescription;
		}

		public void setSchoolDescription(String schoolDescription) {
			this.schoolDescription = schoolDescription;
		}

		public String getOffice() {
			return office;
		}

		public void setOffice(String office) {
			this.office = office;
		}
		public int getExtension() {
			return extension;
		}

		public void setExtension(int extension) {
			this.extension = extension;
		}
		
		// Class attributes 
		
		/**
		 * Default school code.
		 */
		static final String DEFAULT_SCHOOL_CODE = "SET";
		/**
		 * Default description of the school.
		 */
		static final String DEFAULT_SCHOOL_DESCRIPTION = "School of Engineering & Technoloy";
		/**
		 * Default office location.
		 */
		static final String DEFAULT_OFFICE = "H-140"; 
		/**
		 * Default phone extension.
		 */
		static final int DEFAULT_PHONE_EXTENSION = 1234; 
		
		// decimal formatting object.
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
		public Faculty(Long userID, String userPassword, String firstName, String lastName, Date enrolDate, Date lastAccess, boolean userStatus, char userType, String emailAddress, String schoolCode, String schoolDescription, String office, int extension) throws InvalidUserDataException
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
			setSchoolCode(schoolCode);
			setSchoolDescription(schoolDescription);
			setOffice(office);
			setExtension(extension);
			}
			catch(Exception e)
			{
				throw new InvalidUserDataException(e.getMessage());
			}
		}
		
		// Default constructor
		
		/**
		 * Default constructor.
		 * @throws InvalidUserDataException 
		 */
		public Faculty() throws InvalidUserDataException
		{
			// Call the parameterized constructor with the default class attribute values.
			this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME,  new Date(), new Date(), DEFAULT_ENABLED_STATUS, DEFAULT_TYPE, DEFAULT_EMAIL_ADDRESS, DEFAULT_SCHOOL_CODE, DEFAULT_SCHOOL_DESCRIPTION, DEFAULT_OFFICE, DEFAULT_PHONE_EXTENSION);
		}
		
		// parameterized constructor
		// accepts string for userID and converts it to a long before calling the first parameterized constructor.
		/**
		 * Parameterized constructor that accepts String for userID.
		 * @throws InvalidUserDataException 
		 * @throws NumberFormatException 
		 */
		public Faculty(String userID, String userPassword, String firstName, String lastName, Date enrolDate, Date lastAccess, boolean userStatus, char userType, String emailAddress, String schoolCode, String schoolDescription, String office, int extension) throws NumberFormatException, InvalidUserDataException	
		{
			// Call the parameterized constructor with the passed values.
			this(Long.valueOf(userID), userPassword, firstName, lastName, enrolDate, lastAccess, userStatus, userType, emailAddress, schoolCode, schoolDescription, office, extension);
		}
		
		// overload toString()
		// @return the user information as a string.
		
		/**
		 * Method that creates and returns a string with the user information.
		 * @return the Faculty user information as a string.
		 */
		public String toString() 
		{	
			String studentInformation = "\n\n" + getTypeForDisplay() + " Info for: " + getUserID() +
					"\n\tPassword: " + getUserPassword().replaceAll("[ ~!@#$%^&*()_+=-`<>,./?a-zA-Z0-9]", "*") +
					 "\n\t" + getFirstName() + " " + getLastName() + " (" + getEmailAddress() + ")" +
					 "\n\tCreated on: " + dateFormat.format(getEnrolDate()) +
					 "\n\tLast Access: " + dateFormat.format(getLastAccess()) +
					 "\n\tType: " + getUserType() +
					 "\n\t" + getSchoolDescription() + " (" + getSchoolCode() + ")" +
					 "\n\t" + CollegeInterface.COLLEGE_NAME +
					 "\n\tOffice: " + getOffice() +
					 "\n\t" + CollegeInterface.PHONE_NUMBER + " x" + getExtension();

			return studentInformation;
		}

		/**
		 * For use with a database in a future version.
		 * @return 0
		 */
		@Override
		public boolean create() {
			// TODO Auto-generated method stub
			return false;
		}

		/**
		 * For use with a database in a future version.
		 * @return 0
		 */
		@Override
		public int update() {
			// TODO Auto-generated method stub
			return 0;
		}

		/**
		 * For use with a database in a future version.
		 * @return 0
		 */
		@Override
		public int delete() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		/**
		 * Display the object type, inherited from CollegeInterface.
		 * @return Student type.
		 */
		@Override
		public String getTypeForDisplay() {
			// TODO Auto-generated method stub
			return "Faculty";
		}
	}

