<?php

/* 
Name:Maitri Gemlawala, Matthew Cormier, Gurpreet Maan
WEDE3201
Date: 30th September,2015
*/

$title="WEDE3201 - Profile Page";
$fileName="display_profile.php";
$date="9/30/2015";
$Description= "This page displays a user's profile page";
$banner="User Profile";
include ("header.php");
?> 


<?php

if(!(isset($_SESSION["greeting"])))
{
	header('Location: member_login.php');
}
else
{
	if(($_SESSION{"user"}["user_type"] == INCOMPLETE_CLIENT ))
	{
		header('Location: user_dashboard.php');
	}
	else if(( $_SESSION["user"]["user_type"] == DISABLED_CLIENT ))
	{
		header('Location: member_login.php');
	}
	
}

$user_id = $_GET['id'];

buildProfile($user_id);







?>

<?php
include ("footer.php");
?>