<?php
/* 
Name:Maitri Gemlawala, Matthew Cormier, Gurpreet Maan
WEDE3201
Date: 30th September,2015
*/
$title="Admin Dashboard";
$fileName="admin_dashboard.php";
$date="11/4/2015";
$Description= "This is the home dashboard for admins.";
$banner="Dashboard";
include ("header.php");
require_once ("./includes/functions.php");
?>
<?php

if(!(isset($_SESSION["greeting"])))
{
	header('Location: member_login.php');
}
else
{
	echo "Welcome Administrator!" . $_SESSION["greeting"];	
}

?>
<?php include ("footer.php"); ?>