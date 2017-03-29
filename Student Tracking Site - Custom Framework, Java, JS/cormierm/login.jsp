<% session.setAttribute("pageTitle", "Login Page"); %>
<jsp:include page="header.jsp"/>

<script language="JavaScript1.2">
	function verify() {
		if (document.Input.Login.value == "" || document.Input.Password.value == "")
		{
				alert("You must enter a login id and password!!");
			return false;
		}else return true;
	}
</script>

<%  String errorMessage = (String)session.getAttribute("errors"); 
	String login = (String)session.getAttribute("login");
	String password = (String)session.getAttribute("password");
	String logoutMessage = (String)session.getAttribute("logoutMessage");
	if(errorMessage == null)
		errorMessage="";
	if(login == null)
		login = "";
	if(password == null)
		password = "";
	if(logoutMessage == null)
		logoutMessage = "";
%>

<h2>Please log in</h2>
<p>Enter your login information below.<br></p>

<form name="Input" method="post" action="./Login" >
	<!-- action="http://localhost:81/Bradshaw/LoginServlet" > -->
	<table border=0 bgcolor="lightgoldenrodyellow" cellpadding=10 >
	<tr>
		<td colspan="2" align="center"><%= errorMessage %></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><%= logoutMessage %></td>
	</tr>
	<tr>
		<td><strong>Login Id</strong></td>
		<td><input type="text" name="Login" value="<%= login %>" size=20></td>
	</tr>
		<td><strong>Password</strong></td>
		<td><input type="text" name="Password" value="<%= password %>" size=20></td>
	</table>
	<table border=0 cellspacing=15 >
	<tr>
		<td><input type="button" value = "Log In"
				onClick = "if (verify() == true) submit();"></td>
		<td><input type="reset" value = "Clear"></td>
	</tr>
	</table>
</form>

	<% session.removeAttribute("errors"); 
	   session.removeAttribute("logoutMessage"); %>

			
<jsp:include page="footer.jsp"/>