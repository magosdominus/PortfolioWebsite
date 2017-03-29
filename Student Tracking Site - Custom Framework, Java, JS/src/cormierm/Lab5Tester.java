/*package cormierm;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

*//**
 * Description: Main for testing lab 5 changes to student and faculty classes.
 * and outputting.
 * @author Matthew Cormier
 * @version 3.0 (02/23/2016)
 * @since 3.0
 *//*

public class Lab5Tester {

	public static void main(String[] args) {
		
		
		  * Cannot instantiate the type User	Lab5Tester.java.
		  * This error happens because the user class is abstract meaning it cannot be instantiated.
		 
		//User testUser = new User();

		// Testing default objects
		
		Student student1 = new Student();
		Faculty professor1 = new Faculty();
		
		
		
		// Testing parameterized objects.
		
		// Gregorian calendar object.
		GregorianCalendar myCalendar = new GregorianCalendar();
					
		// Set the calendar to date for testing.
		myCalendar.set(GregorianCalendar.YEAR, 2015);
		myCalendar.set(GregorianCalendar.MONTH, 8);
		myCalendar.set(GregorianCalendar.DATE, 2);
					
		Date enrol = myCalendar.getTime();
					
		// Set the calendar to date for testing.
		myCalendar.set(GregorianCalendar.YEAR, 2015);
		myCalendar.set(GregorianCalendar.MONTH, 8);
		myCalendar.set(GregorianCalendar.DATE, 3);
					
		Date lastAccess = myCalendar.getTime();
					
		Student student2 = new Student(Long.valueOf(100586123),"password", "Robert", "McReady", enrol, lastAccess, true,'s',  "bob.mcready@dcmail.ca",   "CPA", "Computer Programmer Analyst", 3);
					
		// Set the calendar to date for testing.
		myCalendar.set(GregorianCalendar.YEAR, 2014);
		myCalendar.set(GregorianCalendar.MONTH, 8);
		myCalendar.set(GregorianCalendar.DATE, 1);
					
		enrol = myCalendar.getTime();
					
		// Set the calendar to date for testing.
		myCalendar.set(GregorianCalendar.YEAR, 2016);
		myCalendar.set(GregorianCalendar.MONTH, 1);
		myCalendar.set(GregorianCalendar.DATE, 7);
					
		lastAccess = myCalendar.getTime();
		
		// create 5 marks and add them to the constructor.
		Vector<Mark> marks = new Vector<Mark>();
		Mark commMark = new Mark("COMM3201", "Communications I For IT", 65, 3.0f);
		Mark copsMark = new Mark("COPS1104", "Computer Operating Systems", 77, 4.0f);
		Mark csysMark = new Mark("CSYS1122", "Computer Systems-Hardware", 81, 4.0f);
		Mark dbasMark = new Mark("DBAS1201", "Intro to Databases", 68, 4.0f);
		Mark webdMark = new Mark("MATH1100", "Mathematics for IT", 73, 3.0f);
		
		marks.add(commMark);
		marks.add(copsMark);
		marks.add(csysMark);
		marks.add(dbasMark);
		marks.add(webdMark);
		
		Student student3 = new Student(Long.valueOf(100570485),"password", "Matthew", "Cormier", enrol, lastAccess, true,'s',  "matthew.cormier@dcmail.ca",   "CPA", "Computer Programmer Analyst", 2, marks);
		
		// Set the calendar to date for testing.
		myCalendar.set(GregorianCalendar.YEAR, 2000);
		myCalendar.set(GregorianCalendar.MONTH, 11);
		myCalendar.set(GregorianCalendar.DATE, 14);
							
		Date start = myCalendar.getTime();
							
		// Set the calendar to date for testing.
		myCalendar.set(GregorianCalendar.YEAR, 2016);
		myCalendar.set(GregorianCalendar.MONTH, 0);
		myCalendar.set(GregorianCalendar.DATE, 10);
							
		Date last = myCalendar.getTime();
						
		Faculty professor2 = new Faculty("100190125", "another password",
										"Darren", "Puffer", start, last, true,'f',"darren.puffer@durhamcollege.ca",
										"BITM", "School of Business, IT & Management", "C-315", 2044);
				
		// loop and display each user.
		Vector<User> users = new Vector<User>();
		users.add(student1);
		users.add(student2);
		users.add(student3);
		users.add(professor1);
		users.add(professor2);
		
		for (int loopCounter = 0; loopCounter < users.size(); loopCounter++)
		{
			users.elementAt(loopCounter).displayToConsole();
		}		
		
		
	}

}
*/