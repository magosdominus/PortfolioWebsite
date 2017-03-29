package cormierm;  //change this to make the class part of your package
/**
 * Main method to test the new User class created for lab 6
 * 
 * @author Darren Puffer
 * @version 1.0 (2016 March 1)
 * @since 1.0
 */
import java.util.*;

public class Lab6Tester {

	public static void main(String[] args) {
		
		System.out.println("******************** Lab 6 Output ********************\n");
		
		GregorianCalendar cal = new GregorianCalendar();
		Date lastAccess = cal.getTime();
		cal.set(2015, Calendar.SEPTEMBER, 3);
		Date enrol = cal.getTime();
		Student student1;
		System.out.println("\n*************************************************************************************************************");
		System.out.println("NOTE: any exceptions displayed while instantiating Student objects in the program only display the contents " +
							"of the Exception, by using the getMessage() method.");
		System.out.println("*************************************************************************************************************");
		System.out.println("\nThe following will cause an Exception based on student id being too small (123L).\n"+
							"Trying to instantiating a random student before displaying it, passing:\n" +
							"\n\tStudent student1 = new Student(123L, \"password\", \"Robert\", \"McReady\"," +
							" \"bob.mcready@dcmail.ca\", enrol, lastAccess, 's', true, \"CPA\", \"Computer Programmer Analyst\", 3);\n"); 
		try{
			student1 = new Student(123L,"password", "Robert", "McReady", 
								enrol, lastAccess, true,'s',  "bob.mcready@dcmail.ca", "CPA", "Computer Programmer Analyst", 3);
			student1.displayToConsole();
		}catch(InvalidUserDataException iude){
			System.out.println(iude.getMessage());
		}
		System.out.println("\nThe following will cause an Exception based on student id being too big(10012345678L).\n"+
				"Trying to instantiating a random student before displaying it, passing:\n\t" +
				"Student student1 = new Student(10012345678L, \"password\", \"Robert\", \"McReady\"," +
				" \"bob.mcready@dcmail.ca\", enrol, lastAccess, 's', true, \"CPA\", \"Computer Programmer Analyst\", 3);\n"); 
		try{
			student1 = new Student(10012345678L,"password", "Robert", "McReady", 
					enrol, lastAccess,true, 's', "bob.mcready@dcmail.ca", "CPA", "Computer Programmer Analyst", 3);
			student1.displayToConsole();
		}catch(InvalidUserDataException iude){
			System.out.println(iude.getMessage());
		}
		System.out.println("\nThe following will not cause an Exception (data is valid).\n"+
				"Trying to instantiating a student before displaying it, passing:\n\t" +
				"Student student1 = new Student(10012345678L, \"password\", \"Robert\", \"McReady\"," +
				" \"bob.mcready@dcmail.ca\", enrol, lastAccess, 's', true, \"CPA\", \"Computer Programmer Analyst\", 3);\n"); 
		try{
			student1 = new Student(100123456L,"password", "Robert", "McReady", 
					enrol, lastAccess, true,'s',  "bob.mcready@dcmail.ca","CPA", "Computer Programmer Analyst", 3);
			student1.displayToConsole();
			try{
				System.out.println("\nTry to set the last name for student1 to an empty string.");
				student1.setLastName("");
			}catch(InvalidNameException ine)
			{
				System.out.println(ine.getMessage());
			}
			try{
				long newId = -100123456L;
				System.out.println("\nTry to set the id for student1 to an invalid number: " + newId + ".");
				student1.setUserID(newId);
			}catch(InvalidIdException iie)
			{
				System.out.println(iie.getMessage());
			}
			try{
				String newPassword = "tiny";
				System.out.println("\nTry to set the password for student1 to a String that is not valid (too short): " + newPassword + ".");
				student1.setUserPassword(newPassword);
			}catch(InvalidPasswordException ipe)
			{
				System.out.println(ipe.getMessage());
			}
			try{
				String newPassword = "supercrazylongpassword";
				System.out.println("\nTry to set the password to a String that is not valid (too long): " + newPassword + ".");
				student1.setUserPassword(newPassword);
			}catch(InvalidPasswordException ipe)
			{
				System.out.println(ipe.getMessage());
			}
		}catch(InvalidUserDataException iude){
			System.out.println(iude.getMessage());
		}
		
		System.out.println("\n*************************************************************************************************************");
		System.out.println("NOTE: any exceptions displayed while instantiating Faculty objects will call the toString() method, " +
							"this includes the Exception type as part of the message.");
		System.out.println("*************************************************************************************************************");
		
		System.out.println("\nThe following object instantiations will use a Faculty object passing a String for the id (where there could be parsing issues).\n");
		
		Faculty professor;
		String possibleId = "pufferd"; //invalid/un-parseable string
		System.out.println("\nThe following will cause an NumberFormatException based on faculty id being passed (i.e. \""+possibleId+"\")\n"+
				"\tcannot be converted to from a String to a long using Long.parseLong(\""+possibleId+"\")."+
				"\n\nTrying to instantiate my professor before displaying it, passing:\n\t" +
				"professor = new Faculty(\""+ possibleId +"\", \"another password\", \"Darren\", \"Puffer\", " +
				"\"darren.puffer@durhamcollege.ca\", enrol, lastAccess, 'f', true, " +
				"\"BITM\", \"School of Business, IT & Management\", \"C-315\", 2044);\n"); 
		try{
			professor = new Faculty(possibleId, "another password", "Darren", "Puffer", enrol, lastAccess, true, 'f',  "darren.puffer@durhamcollege.ca",
					"BITM", "School of Business, IT & Management", "C-315", 2044);
			professor.displayToConsole();
		}catch(NumberFormatException nfe)
		{
			System.out.println("Problem creating a professor with an id of: \"" + possibleId + "\"\n\t " + nfe.toString());
		}catch(InvalidUserDataException ide)
		{
			System.out.println("Problem creating a professor with an id of: \"" + possibleId + "\"\n\t " + ide.toString());
			
		}
		
		possibleId = "100123654"; //valid String that can become an id
		System.out.println("\nThe following will cause an InvalidUserDataException based on a missing first name,\n"+
				"the program will ignore the NumberFormatException catch and \ninstead get caught by the InvalidUserDataException block.\n"+
				"\nTrying to instantiate my professor before displaying it, passing:\n\t" +
				"professor = new Faculty(\""+ possibleId +"\", \"somepassword\", \"\", \"Puffer\", " +
				"\"darren.puffer@durhamcollege.ca\", enrol, lastAccess, 'f', true, " +
				"\"BITM\", \"School of Business, IT & Management\", \"C-315\", 2044);\n");  
		try{
			professor = new Faculty(possibleId, "somepassword", "", "Puffer", enrol, lastAccess, true, 'f',  "darren.puffer@durhamcollege.ca",
					"BITM", "School of Business, IT & Management", "C-315", 2044);
			professor.displayToConsole();
		}catch(NumberFormatException nfe)
		{
			System.out.println("Problem creating a professor with an id of: \"" + possibleId + "\"\n\t " + nfe.toString());
		}catch(InvalidUserDataException ide)
		{
			System.out.println("Problem creating a professor with an id of: \"" + possibleId + "\"\n\t " + ide.toString());
			
		}
		
		System.out.println("\nThe following will cause an InvalidUserDataException based on a missing first name,\n"+
				"the program demonstrates that InvalidUserDataException (in fact any/all Exceptions) can be caught by\n"
				+ "the generic Exception catch.\n"+
				"\nTrying to instantiate my professor before displaying it, passing:\n\t" +
				"professor = new Faculty(\""+ possibleId +"\", \"somepassword\", \"\", \"Puffer\", " +
				"\"darren.puffer@durhamcollege.ca\", enrol, lastAccess, 'f', true, " +
				"\"BITM\", \"School of Business, IT & Management\", \"C-315\", 2044);\n");  
		try{
			professor = new Faculty(possibleId, "somepassword", "", "Puffer",  enrol, lastAccess, true, 'f',  "darren.puffer@durhamcollege.ca",
					"BITM", "School of Business, IT & Management", "C-315", 2044);
			professor.displayToConsole();
		}catch(Exception e)
		{
			System.out.println("Problem creating a professor with an id of: \"" + possibleId + "\"\n\t " + e.toString());
		}
		
		
	}

}
