package cormierm;

/**
 * InvalidIdException
 * 
 * Description: This exception will be thrown when the passed user id is invalid. 
 * used in the setID() method.
 * 
 * @author Matthew Cormier
 * @version 4.0 (03/07/2016)
 * @since 4.0
 */
public class InvalidIdException extends Exception {
	
	// Attributes
	
	/**
	 * Message for the parameterized constructor.
	 */
	public static String message;
	
	/**
	 * Default message constant for the default constructor.
	 */
	public static final String DEFAULT_MESSAGE = "Invalid user ID.";
	
	/**
	 * To suppress an IDE error.
	 */
	private static final long serialVersionUID = 1L;
	
	// Constructors
	
	/** 
	 *  Parameterized constructor
	 * 	Message is set to the error message passed to it.
	 * 	
	 */
	public InvalidIdException(String newMessage)
	{
		message = newMessage;
	}
	
	/** 
	 * Default constructor
	 * Sets message to default message.
	 */
	public InvalidIdException()
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
