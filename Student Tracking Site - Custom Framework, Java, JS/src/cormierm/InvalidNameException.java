package cormierm;

/**
 * Description: This exception will be thrown when the password is invalid. 
 * used in the setPassword() method.
 * 
 * @author Matthew Cormier
 * @version 4.0 (03/07/2016)
 * @since 4.0
 */
public class InvalidNameException extends Exception {
	
	// Attributes
	
	/**
	 * Message for the parameterized constructor.
	 */
	public static String message;
	
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
	public InvalidNameException(String newMessage)
	{
		message = newMessage;
	}
	
	/** 
	 * Default constructor
	 */
	public InvalidNameException()
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
