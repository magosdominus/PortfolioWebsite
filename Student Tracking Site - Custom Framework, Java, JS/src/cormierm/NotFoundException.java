package cormierm;

/**
 * NotFoundException - this file contains extends the generic Exception class so that
 *                 we have a custom Exception, this one will be used to flag when 
 *                 no record was found in the database (and therefore nothing can be
 *                 done to it)
 * @author Darren Puffer
 * @author Matthew Cormier
 * @version 5.0 (03/16/2016)
 * @since 5.0
 */

@SuppressWarnings("serial")
public class NotFoundException extends Exception
{
	/**
	 * Default constructor.
	 */
	public NotFoundException()
	{ super();}
	
	/**
	 * Parameterized Constructor.
	 * @param message
	 */
	public NotFoundException(String message)
	{ super(message);}
}