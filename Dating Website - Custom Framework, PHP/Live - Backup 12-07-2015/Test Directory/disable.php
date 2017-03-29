<?php
/* 
Name:Maitri Gemlawala, Matthew Cormier, Gurpreet Maan
WEDE3201
Date: 30th September,2015
*/
$title="Disable User";
$fileName="disable.php";
$date="12/07/2015";
$Description= "This page automaticly disables a user and closes the respective report.";
$banner="Disable User";
include ("header.php");
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
	$result = pg_execute($conn, "disable_user", array(DISABLED_CLIENT, $_SESSION["viewing_user_id"]));
	echo $_SESSION["viewing_user_id"] . " has been successfully disabled.";
	
	$result = pg_execute($conn, "close_report", array(CLOSED, $_SESSION["viewing_user_id"]));
}


?>
<?php include ("footer.php"); ?>