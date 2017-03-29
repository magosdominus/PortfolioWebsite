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
	echo "Welcome Administrator!" . $_SESSION["greeting"];
	
	$result = pg_execute($conn, "check_for_open_reports", array(OPEN));
	
	if(pg_num_rows($result) > 0)
	{
		echo "<h2 align=\"center\"> Reported Profiles </h2>";
		for($counter = 0; $counter < pg_num_rows($result); $counter++)
		{
			$records = pg_fetch_assoc($result, $counter);
		
			foreach($records as $key => $value)
			{
				echo "<li align=\"center\"><a href=\"display_profile.php?id=".$value."\">". $value ."</a></li>";
			}
		}
	}
	else
	{
		echo "<p align=\"center\"> Currently no open reports. </p>";
	}
}


?>
<?php include ("footer.php"); ?>