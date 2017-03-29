<% session.setAttribute("pageTitle", "Register Page"); %>
<jsp:include page="header.jsp"/>


			<h2>
				Register Page 
			</h2>

			<% if (session.getAttribute("student") == null || session.getAttribute("student").equals("")) { %>
			
				<%  String errorMessage = (String)session.getAttribute("errorMessage"); 
					String studentNumber = (String)session.getAttribute("studentNumber"); 
					String password = (String)session.getAttribute("password");
					String confirmPassword = (String)session.getAttribute("confirmPassword");
					String emailAddress = (String)session.getAttribute("emailAddress");
					String programCode = (String)session.getAttribute("programCode");
					String programDescription = (String)session.getAttribute("programDescription");
					String year = (String)session.getAttribute("year");
					String firstName = (String)session.getAttribute("firstName");
					String lastName = (String)session.getAttribute("lastName");
					if(errorMessage == null)
						errorMessage="";
					if(studentNumber == null)
						studentNumber = "";
					if(password == null)
						password = "";
					if(confirmPassword == null)
						confirmPassword = "";
					if(emailAddress == null)
						emailAddress = "";
					if(programCode == null)
						programCode = "";
					if(programDescription == null)
						programDescription = "";
					if(year == null)
						year = "";
					if(firstName == null)
						firstName = "";
					if(lastName == null)
						lastName = "";					
				%>
				
					<script language="JavaScript1.2">
						function verify() {
							if (document.Input.Login.value == "" || document.Input.Password.value == "" || document.Input.First_Name.value == "" || document.Input.Last_Name.value == "" || document.Input.Confirm_Password.value == "" || document.Input.Email.value == "" || document.Input.Program_Code.value == "" || document.Input.Program_Description.value == "" || document.Input.Year.value == "")
							{
									alert("You must enter in all inputs.");
								return false;
							}else return true;
						}
					</script>
			
				<form name="Input" method="post" action="./Register" >
					<!-- action="http://localhost:81/Bradshaw/LoginServlet" > -->
					<table border=0 bgcolor="lightgoldenrodyellow" cellpadding=10 >
					<tr>
						<td colspan="2" align="center"><%= errorMessage %></td>
					</tr>
					<tr>
						<td><strong>Login Id</strong></td>
						<td><input type="text" name="Login" value="<%= studentNumber %>" size=20></td>
					</tr>
					<tr>
						<td><strong>First Name</strong></td>
						<td><input type="text" name="First_Name" value="<%= firstName %>" size=20></td>
					</tr>
					<tr>
						<td><strong>Last Name</strong></td>
						<td><input type="text" name="Last_Name" value="<%= lastName %>" size=20></td>
					</tr>					
					<tr>
						<td><strong>Password</strong></td>
						<td><input type="text" name="Password" value="<%= password %>" size=20></td>
					</tr>
					<tr>
						<td><strong>Comfirm Password</strong></td>
						<td><input type="text" name="Confirm_Password" value="<%= confirmPassword %>" size=20></td>
					</tr>
					<tr>
						<td><strong>Email Address</strong></td>
						<td><input type="text" name="Email" value="<%= emailAddress %>" size=20></td>
					</tr>
					<tr>
						<td><strong>Program Code</strong></td>
						<td><input type="text" name="Program_Code" value="<%= programCode %>" size=20></td>
					</tr>
					<tr>
						<td><strong>Program Description</strong></td>
						<td><input type="text" name="Program_Description" value="<%= programDescription %>" size=20></td>
					</tr>
					<tr>
						<td><strong>Current Year</strong></td>
						<td><input type="text" name="Year" value="<%= year %>" size=20></td>
					</tr>
					</table>
					<table border=0 cellspacing=15 >
					<tr>
						<td><input type="button" value = "Register"
								onClick = "if (verify() == true) submit();"></td>
						<td><input type="reset" value = "Clear"></td>
					</tr>
					</table>
				</form>

			
			<% } else {  %>
			
				<%
				session.setAttribute("UpdateMessage", "You should update, not register since your logged in.");
				response.sendRedirect("./update.jsp");
				%>
			
			<% } %>
			
<% session.removeAttribute("errorMessage"); %>
			
<jsp:include page="footer.jsp"/>