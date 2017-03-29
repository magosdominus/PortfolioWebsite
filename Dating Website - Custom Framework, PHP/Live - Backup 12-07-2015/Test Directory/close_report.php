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
elseif($_SESSION{"user"}["user_type"] == INCOMPLETE_CLIENT || $_SESSION["user"]["user_type"] == DISABLED_CLIENT || $_SESSION["user"]["user_type"] == CLIENT)
{
	header('Location: user_dashboard.php');
}
else
{
	echo $_SESSION["viewing_user_id"] . " reports have been closed. User remains enabled.";
	
	$result = pg_execute($conn, "close_report", array(CLOSED, $_SESSION["viewing_user_id"]));
}


?>
<?php include ("footer.php"); ?>