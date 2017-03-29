package cormierm;

/**
 * DulicateException - Thrown when a object is inserted into a database that already has a object with the same userid.
 * @author Matthew Cormier
 * @version 6.0 (03/31/2016)
 * @since 5.0
 */

@SuppressWarnings("serial")
public class DuplicateException extends Exception
{
	/**
	 * Default constructor.
	 */
	public DuplicateException()
	{ super();}
	
	/**
	 * Parameterized Constructor.
	 * @param message
	 */
	public DuplicateException(String message)
	{ super(message);}
}