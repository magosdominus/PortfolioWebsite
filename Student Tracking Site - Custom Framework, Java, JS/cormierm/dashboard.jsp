<% session.setAttribute("pageTitle", "Dashboard"); %>
<jsp:include page="header.jsp"/>

<%@ page import = "cormierm.*" %>
<% Student aStudent = (Student)session.getAttribute("student"); %>

<h2> Dashboard </h2>

<p>
	Welcome, <%= aStudent.getFirstName() %> <%= aStudent.getLastName() %>! You last logged in on <%=  session.getAttribute("lastAccess")  %>

</p>
<p>
<% if (session.getAttribute("logoutMessage") == null || session.getAttribute("logoutMessage").equals("")) { %>

<% } else {%>

<%= session.getAttribute("logoutMessage") %>
<% } %>
</p>
			
<jsp:include page="footer.jsp"/>