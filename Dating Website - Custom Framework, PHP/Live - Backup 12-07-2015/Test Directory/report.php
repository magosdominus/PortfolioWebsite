<?php

/* 
Name: Matthew Cormier, Gurpreet Maan, Maitri Gemlawala
WEDE3201
Date: 12/07/2015
*/

$title="WEDE3201 - Report";
$fileName="report.php";
$date="12/07/2015";
$Description= "This page sends an automatic report agaisnt a user profile.";
$banner="Report Profile";
include ("header.php");
?> 

<?php

if(!(isset($_SESSION["greeting"])))
{
	header('Location: member_login.php');
}
else
{
	if(($_SESSION{"user"}["user_type"] == INCOMPLETE_CLIENT || $_SESSION["user"]["user_type"] == DISABLED_CLIENT ))
	{
		header('Location: user_dashboard.php');
	}
}

$result = pg_execute($conn, "report_profile", array($_SESSION["Logon_user_id"], $_SESSION["viewing_user_id"], OPEN));

echo "<h3> Profile report for offensive behaviour has been successfully applied agianst ". $_SESSION["viewing_user_id"] .". </h3>"


?>

<?php
include ("footer.php");
?>