package cormierm;

/**
 * NotFoundException - This exception is thrown when a student is not found in the database.
 * @author Matthew Cormier
 * @version 6.0 (03/31/2016)
 * @since 5.0
 */

@SuppressWarnings("serial")
public class StudentNotFoundException extends Exception
{
	/**
	 * Default constructor.
	 */
	public StudentNotFoundException()
	{ super();}
	
	/**
	 * Parameterized Constructor.
	 * @param message
	 */
	public StudentNotFoundException(String message)
	{ super(message);}
}