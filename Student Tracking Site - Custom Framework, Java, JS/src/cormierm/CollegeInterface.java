package cormierm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Description: Interface for colleges. Durham college's information and method headers that will be stored here for use by other classes.
 * and outputting.
 * @author Matthew Cormier
 * @version 3.0 (02/23/2016)
 * @since 3.0
 */

public interface CollegeInterface {

	// Constants 
	
	/**
	 * College name.
	 */
	public static final String COLLEGE_NAME = "Durham College";
	
	/**
	 * College phone number.
	 */
	public static final String PHONE_NUMBER = "(905)721-2000";
	
	/**
	 * Minimum ID number.
	 */
	public static final long MINIMUM_ID_NUMBER  = 100000000L;
	
	/**
	 * Maximum ID number.
	 */
	public static final long MAXIMUM_ID_NUMBER = 999999999L;

	/**
	 * Maximum years.
	 */
	public static final int MAXIMUM_YEARS = 1;
	
	/**
	 * Maximum years.
	 */
	public static final int MINUMUM_YEARS = 4;	
	
	// Method headers.
	
	/**
	 * Method header for getTypeForDisplay().
	 */
	public String getTypeForDisplay();
	
	public DateFormat TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd 'at' h:mm:ss a z");
}
