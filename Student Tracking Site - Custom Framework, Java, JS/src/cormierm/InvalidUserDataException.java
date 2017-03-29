package cormierm;

/**
 * Description: This exception will be used to indicate if any passed data for a user is invalid.
 * 
 * @author Matthew Cormier
 * @version 4.0 (03/07/2016)
 * @since 4.0
 */
public class InvalidUserDataException extends Exception {
	
	// Attributes
	
	/**
	 * Message for the parameterized constructor.
	 */
	public static String message = " is not a valid password.";
	
	/**
	 * Default message constant for the default constructor.
	 */
	public static final String DEFAULT_MESSAGE = "You must provide a last name";
	
	/**
	 * To suppress an IDE error.
	 */
	private static final long serialVersionUID = 1L;
	
	// Constructors
	
	/** 
	 *  Parameterized constructor
	 * 	Displays an error message with the passed message value.
	 * 	
	 */
	public InvalidUserDataException(String newMessage)
	{
		 message = newMessage;
	}
	
	/** 
	 * Default constructor
	 */
	public InvalidUserDataException()
	{
		message = DEFAULT_MESSAGE;
	}
	
	/**
	 * Returns the exception's message.
	 * @return message
	 */
	@Override
	public String getMessage()
	{
		return message;
	}

}
