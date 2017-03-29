package cormierm;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;
import java.util.Date;

/**
 * Description: Register Servlet - registers a student record.
 * @author Matthew Cormier
 * @version 8.0 (04/08/2016)
 * @since 8.0
 */
@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	
    public void doPost(HttpServletRequest request,
				            HttpServletResponse response) 
					throws ServletException, IOException
		{
    	
	            // connect to database
	            Connection c = StudentDatabaseConnect.initialize();
	            Student.initialize(c);
	            HttpSession session = request.getSession(true);
	            
	            String login = new String();
	            Long validLogin = 0l;
	            String password = new String();   
	            String confirmPassword = new String();
	            String emailAddress = new String();
	            String programCode = new String();
	            String programDescription = new String();
	            String year = new String();
	            int validYears;
	            String firstName = new String();
	            String lastName = new String();
	            
	            try 
	            {   // retrieve data from DB
	                login = request.getParameter( "Login" ); //this is the name of the text input box on the form
	                password = request.getParameter( "Password" );
	                confirmPassword = request.getParameter( "Confirm_Password" );
	                emailAddress = request.getParameter( "Email" );
	                programCode = request.getParameter( "Program_Code" );
	                programDescription = request.getParameter( "Program_Description" );
	                year = request.getParameter( "Year" );
	                firstName = request.getParameter( "First_Name" );
	                lastName = request.getParameter( "Last_Name" );
	                
	                validLogin = Long.parseLong(login);
	                validYears = Integer.parseInt(year);
	                
	                if (validLogin > Student.MAXIMUM_ID_NUMBER || validLogin < Student.MINIMUM_ID_NUMBER)
	                {
	                	throw new InvalidUserDataException("Login ID not with valid range " + Student.MINIMUM_ID_NUMBER + " to " + Student.MAXIMUM_ID_NUMBER);
	                }
	                if (Student.isExistingLogin(login) == true)
	                {
	                	throw new StudentNotFoundException("Login ID is already taken of this system.");
	                }
	                
	                if (!password.equals(confirmPassword))
	                {
	                	throw new InvalidUserDataException("Password and Confirm Password do not match");
	                }
	                if(password.length() > Student.MAXIMUM_PASSWORD_LENGTH || password.length() < Student.MINIMUM_PASSWORD_LENGTH)
	                {
	                	throw new InvalidUserDataException("Password not with valid range " + Student.MINIMUM_PASSWORD_LENGTH + " to " + Student.MAXIMUM_PASSWORD_LENGTH);	                	
	                }
	                
	                if(validYears > Student.MINUMUM_YEARS || validYears < Student.MAXIMUM_YEARS)
	                {
	                	throw new InvalidUserDataException("Year not with valid range " + Student.MINUMUM_YEARS + " to " + Student.MAXIMUM_YEARS);	                	
	                }
	                if (User.isValidEmailAddress(emailAddress))
	                {
	                	throw new InvalidUserDataException("Email is invalid");	                	
	                }
	                
	                Student aStudent = new Student(validLogin, password, firstName, lastName, new java.util.Date(), new java.util.Date(), true, 's', emailAddress, programCode, programDescription, validYears);
	                
	                aStudent.create();
	                
	                session.setAttribute("logoutMessage", "You account has been created. Please log in.");


	                response.sendRedirect("./login.jsp");
	            }
	            catch(NumberFormatException e)
	            {
	                //new code == way better, if I do say so myself
	                //sending errors to the page thru the session
	                StringBuffer errorBuffer = new StringBuffer();
	                errorBuffer.append("<strong>" + e.toString() + "<br/>");
	                errorBuffer.append("Please try again.</strong>");

	                session.setAttribute("errorMessage", errorBuffer.toString());
	                response.sendRedirect("./register.jsp");
	            }
	            catch(InvalidUserDataException e)
	            {
	                //new code == way better, if I do say so myself
	                //sending errors to the page thru the session
	                StringBuffer errorBuffer = new StringBuffer();
	                errorBuffer.append("<strong>" + e.toString() + "<br/>");
	                errorBuffer.append("Please try again.</strong>");
	                
	                session.setAttribute("errorMessage", errorBuffer.toString());
	                response.sendRedirect("./register.jsp");
	            }
	            catch(StudentNotFoundException e)
	            {
	                //new code == way better, if I do say so myself
	                //sending errors to the page thru the session
	                StringBuffer errorBuffer = new StringBuffer();
	                errorBuffer.append("<strong>" + e.toString() + "<br/>");
	                errorBuffer.append("Please try again.</strong>");

	                session.setAttribute("errorMessage", errorBuffer.toString());
	                response.sendRedirect("./register.jsp");
	            }
	            catch(NotFoundException e)
	            {
	                //new code == way better, if I do say so myself
	                //sending errors to the page thru the session
	                StringBuffer errorBuffer = new StringBuffer();
	                errorBuffer.append("<strong>" + e.toString() + "<br/>");
	                errorBuffer.append("Please try again.</strong>");

	                session.setAttribute("errorMessage", errorBuffer.toString());
	                response.sendRedirect("./register.jsp");	            	
	            } catch (DuplicateUserException e) {
	                //new code == way better, if I do say so myself
	                //sending errors to the page thru the session
	                StringBuffer errorBuffer = new StringBuffer();
	                errorBuffer.append("<strong>" + e.toString() + "<br/>");
	                errorBuffer.append("Please try again.</strong>");

	                session.setAttribute("errorMessage", errorBuffer.toString());
	                response.sendRedirect("./register.jsp");
				} catch (DuplicateException e) {
	                //new code == way better, if I do say so myself
	                //sending errors to the page thru the session
	                StringBuffer errorBuffer = new StringBuffer();
	                errorBuffer.append("<strong>" + e.toString() + "<br/>");
	                errorBuffer.append("Please try again.</strong>");

	                session.setAttribute("errorMessage", errorBuffer.toString());
	                response.sendRedirect("./register.jsp");
				}
	            catch (SQLException e)
				{ 
	                //new code == way better, if I do say so myself
	                //sending errors to the page thru the session
	                StringBuffer errorBuffer = new StringBuffer();
	                errorBuffer.append("<strong>" + e.toString() + "<br/>");
	                errorBuffer.append("Please try again.</strong>");

	                session.setAttribute("errorMessage", errorBuffer.toString());
	                response.sendRedirect("./register.jsp");	            	
				}
		}
}

