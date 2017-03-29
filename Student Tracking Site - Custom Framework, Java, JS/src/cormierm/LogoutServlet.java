package cormierm;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;

/**
 * Description: Logout servlet that removes a student from teh session.
 * @author Matthew Cormier
 * @version 6.0 (03/31/2016)
 * @since 6.0
 */
@SuppressWarnings("serial")
public class LogoutServlet extends HttpServlet {
	
	 public void doGet(HttpServletRequest request,
             HttpServletResponse response) 
		throws ServletException, IOException
		{
		 	HttpSession session = request.getSession(true);
		 
			String output = "You have successfully logged out";
			session.removeAttribute("student");
			
			session.setAttribute("logoutMessage", output);
			
			response.sendRedirect("./login.jsp");
		}
}

