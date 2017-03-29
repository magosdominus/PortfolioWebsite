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

$interest_form = "";
$interest_exists = "test";

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

if($_SERVER["REQUEST_METHOD"] == "GET")
{
	$user_id = $_GET['id'];
	$_SESSION["viewing_user_id"] = $user_id;
	
	if($_SESSION{"user"}["user_type"] == ADMIN)
	{
		echo "
		<li><a href=\"disable.php\">Disable User</a></li>
		<li><a href=\"close_report.php\">Do Not Disable User & Close Report</a></li>	";	
	}
	else
	{
		$result =  pg_execute($conn, "check_if_reported", array($_SESSION["Logon_user_id"] , $_SESSION["viewing_user_id"]));
		
		if ($user_id == $_SESSION["Logon_user_id"])
		{
			echo "<h2> \nThis is how your profile appears to other users. </h2>";
		}
		else
		{
			if(pg_num_rows($result) > 0)
			{
				echo "You have already reported this profile. ";
			}
			else
			{
				echo "<li><a href=\"report.php\">Report Profile</a></li>";	
			}
			
			
			$result = pg_execute($conn, "check_for_interest", array($_SESSION["Logon_user_id"], $_SESSION["viewing_user_id"]));
			if(pg_num_rows($result) > 0)
			{
				$interest_form = "
				<div id=\"table\">
					<form  action=". $_SERVER['PHP_SELF']." method=\"post\">
						<table style=\"margin-left:auto; margin-right:auto; float:center;\" border=\"0\"  cellpadding=\"10\">
						<tr><td><p> You have already shown an interest in this user. </p></td></tr>
						<tr><td><input type=\"submit\" value=\"Remove Interest\"></input></td>
						</table>
					</form>
				</div>
				";
				//$interest_exists = true;
			}
			else
			{
				$interest_form = "
				<div id=\"table\">
					<form  action=". $_SERVER['PHP_SELF']." method=\"post\">
						<table style=\"margin-left:auto; margin-right:auto; float:center;\" border=\"0\"  cellpadding=\"10\">
						<tr><td><p> You have not shown interest in this user. </p></td></tr>
						<tr><td><input type=\"submit\" value=\"Show Interest\"></input></td>
						</table>
					</form>
				</div>
				";
				//$interest_exists = false;
			}
		}
	}
	}


elseif($_SERVER["REQUEST_METHOD"] == "POST")
{
	$result = pg_execute($conn, "check_for_interest", array($_SESSION["Logon_user_id"], $_SESSION["viewing_user_id"]));
	if(pg_num_rows($result) > 0)
	{
		$interest_exists = true;
	}
	else
	{
		$interest_exists = false;
	}
			
	if($interest_exists == true)
	{
		$result = pg_execute($conn, "remove_interest", array($_SESSION["Logon_user_id"], $_SESSION["viewing_user_id"]));
		echo "Interest has been removed!";
		$interest_exists == false;
		header('Location: display_profile.php?id='.$_SESSION["viewing_user_id"]);
	}
	elseif($interest_exists == false)
	{
		$result = pg_execute($conn, "add_interest", array($_SESSION["Logon_user_id"], $_SESSION["viewing_user_id"]));
		$interest_exists == true;
		header('Location: display_profile.php?id='.$_SESSION["viewing_user_id"]);
	}
}

echo $interest_form;

buildProfile($_SESSION["viewing_user_id"]);


?>

<?php
include ("footer.php");
?>