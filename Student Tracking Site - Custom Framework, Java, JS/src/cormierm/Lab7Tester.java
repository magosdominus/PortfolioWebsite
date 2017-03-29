package cormierm;

import java.sql.Connection;
/**
 * Main method to test the new User class created for lab 6
 * 
 * @author Darren Puffer
 * @version 1.0 (2016 March 1)
 * @since 1.0
 */
import java.util.*;

public class Lab7Tester {

	public static void main(String[] args) {
		System.out.println("******************** Lab 7 Output ********************\n");
		Connection c = null;
		Student mainStudent;  //object for a program created Student
		Student dbStudent;   //object for database retrieved Student
		long possibleId = 100222222L;
		GregorianCalendar cal = new GregorianCalendar();
		Date lastAccess = cal.getTime();
		cal.set(2015, Calendar.SEPTEMBER, 3);
		Date enrol = cal.getTime();
		try{
			mainStudent = new Student();
			dbStudent = new Student();
			System.out.println("\nCreate a Student user to insert/delete later in the program, passing:\n\t" +
					"Student student1 = new Student(" + possibleId + "L, \"password\", \"Robert\", \"McReady\"," +
					" \"bob.mcready@dcmail.ca\", enrol, lastAccess, 's', true, \"CPA\", \"Computer Programmer Analyst\", 3);\n"); 
			
			mainStudent = new Student(possibleId,"password", "Robert", "McReady", 
					enrol, lastAccess, true, 's', "bob.mcready@dcmail.ca", "CPA", "Computer Programmer Analyst", 3);
			//mainStudent.displayToConsole();
			try{
				
	            // initialize the database (i.e. create a database connection)
	            c = StudentDatabaseConnect.initialize();
	            Student.initialize(c);
	            
	            try // attempt to get a Student that does NOT exist, throws Exception
	            {
	            	System.out.println("\nAttempt to retrieve a student that does not exist (Id: " + possibleId + ")");
	            	dbStudent = Student.retrieve(possibleId);
	            	System.out.println("Student record with id " + possibleId + " retrieved from the database\n");
	            	dbStudent.displayToConsole();
	            }
	            catch(NotFoundException e)
	            {	System.out.println(e.getMessage());}

	            try // attempt to get a Student that does exist
	            {
	            	possibleId = 100111111L;
	            	System.out.println("\nAttempt to retrieve a student that does exist (Id: " + possibleId + ")");
	            	dbStudent = Student.retrieve(possibleId);
	            	System.out.println("Student record with id " + possibleId + " retrieved from the database\n");
	            	dbStudent.displayToConsole();
	            }
	            catch(NotFoundException e)
	            {	System.out.println(e.getMessage());}
	            
	            try
	            {
	            	System.out.println("\nAttempt to insert a new student record for " 
	            						+ mainStudent.getFirstName() + " " + mainStudent.getLastName() 
	            						+ "(Id: " + mainStudent.getUserID()+")");
	            	mainStudent.create();
	                System.out.println("Student record added to the database.\n");
	            }
	            catch(DuplicateUserException e)
	            {	System.out.println(e);}
	            
	            try
	            {
	            	System.out.println("\nChange the student object and attempt to update the student record for " 
	            						+ mainStudent.getFirstName() + " " + mainStudent.getLastName() 
	            						+ "(Id: " + mainStudent.getUserID() +")");
	            	mainStudent.setUserPassword("newpassword");
	            	mainStudent.setProgramCode("RPN");
	            	mainStudent.setProgramDescription("Registered Practical Nurse");
	            	
	            	mainStudent.update();
	                System.out.println("Student record updated in the database.\n");
	            }
	            catch(NotFoundException e)
	            {	System.out.println(e);}
	            
	            System.out.println("\nStudents are encouraged to comment out the folowing try...catch block to"
	            		+ " verify the new record exists in pgAdmin by running the \"SELECT * FROM Students;\" command "); 
		            	
	            try // now, attempt to delete the new Student
	            {
	            	System.out.println("\nAttempt to delete the new student record for " 
	            	   						+ mainStudent.getFirstName() + " " + mainStudent.getLastName() 
   						+ "(Id: " + mainStudent.getUserID() + ")");
	        	   		mainStudent.delete();
	        	   	System.out.println("Student record with id " + mainStudent.getUserID() + " successfully removed from the database\n");
	            }
	            catch(NotFoundException e)
	                    {	System.out.println(e);}

	            try // now, try to find the deleted Student
	            {
	            	possibleId = 100222222L;
	                mainStudent = Student.retrieve(possibleId);
	                mainStudent.displayToConsole();
	            }
	            catch(NotFoundException e)
	            {
	            	System.out.println("Did not find student record with id " + possibleId + "\n");
	            }
			 }catch(Exception e){   //catch for database initialize/connect try
				  System.out.println(e.toString());
			 }finally{ // close the database resources, if possible            
		          try{  Student.terminate(); }catch(Exception e){}
		          try{  StudentDatabaseConnect.terminate(); }catch(Exception e){}
			 }
		}catch(InvalidUserDataException iude){
			System.out.println(iude.getMessage());
		}
	}
}

