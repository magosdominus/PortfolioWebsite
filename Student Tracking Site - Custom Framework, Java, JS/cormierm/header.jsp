<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en"> 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="./css/style.css" /> 
		<!-- CHANGE THE HREF ABOVE TO intn2201.css AFTER YOU HAVE INCLUDED THE CONTENTS OF style.css INTO IT -->
	<!--
	Author: Matthew Cormier
	Filename: header.php this will be echo'ed using a PHP variable eventually
	Date: 31 March 2016
	Description: Header for website pages.
	-->
	<title><%= session.getAttribute("pageTitle") %></title><!-- THE <title> WILL COME FROM A PHP VARIABLE TOO -->
</head>
<body>
<div id="container">
	<div id="header">
		<img src="./images/DC-fullcolourpng-logo.png" alt="Durham College Logo" />
		<h1>
			Student Grade Tracking 
		</h1>
	</div>
	<div id="sites">
		<ul>
			<% if (session.getAttribute("student") == null || session.getAttribute("student").equals("")) { %>
			
			<li><a href="./index.jsp">Home</a></li>
			<li><a href="./login.jsp">Login</a></li>
			<li><a href="./register.jsp">Register</a></li>
			
			<% } else {  %>
			
			<li><a href="./index.jsp">Home</a></li>
			<li><a href="logout">Log Out</a></li>
			<li><a href="./update.jsp">Update</a></li>			
			<%@ page import="cormierm.*" %>
			
			<% } %>
		</ul>
	</div>
	<div id="content-container">
		<div id="navigation">
			&nbsp;
		</div>
		<div id="content">
		<!-- start of main page content. --> 
		
		