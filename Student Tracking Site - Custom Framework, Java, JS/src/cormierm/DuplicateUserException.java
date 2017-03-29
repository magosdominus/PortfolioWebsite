package cormierm;

/**
 * DuplicateException - this file contains extends the generic Exception class so that
 * 			            we have a custom Exception, this one will be used to flag an attempt
 * 			            at a duplicate record in our database
 * @author Darren Puffer
 * @version 1.0 (13 March 2015)
 * @since 1.0
 */ 

@SuppressWarnings("serial")
public class DuplicateUserException extends Exception
{
	/**
	 * Default constructor.
	 */
    public DuplicateUserException()
    { super();}
    
    /**
     * Parameterized constructor.
     * @param message
     */
    public DuplicateUserException(String message)
    { super(message);}
}