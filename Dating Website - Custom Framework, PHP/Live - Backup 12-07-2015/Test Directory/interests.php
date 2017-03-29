<?php

/* 
Name: Matthew Cormier, Gurpreet Maan, Maitri Gemlawala
WEDE3201
Date: December 7th, 2015
*/

$title="WEDE3201 - Interests";
$fileName="interests.php";
$date="12/07/2015";
$Description= "This page will display all users the logged in user has shown interest in. The page will
				also show other users who have shown interest in the logged in user. The page will allow
				the user to remove interests.";
$banner="Interests";
include ("header.php");
?> 


<?php
$interests_of_user = "";

$result = pg_execute($conn, "select_all_interests", array($_SESSION["Logon_user_id"]));
if(pg_num_rows($result) > 0)
{
	echo "
	<div id=\"table\">
		<form  action=". $_SERVER['PHP_SELF']." method=\"post\">
			<table style=\"margin-left:auto; margin-right:auto; float:center;\" border=\"0\"  cellpadding=\"10\"> 
				<tr><td> <h3> Your Interests </h3> </td></tr>
				<tr><td> <p align=\"center\"> A mutual interest label is placed above users who have a mutual interest. </p> </td></tr>
	";
	
	for($counter = 0; $counter < pg_num_rows($result); $counter++)
	{
		$records = pg_fetch_assoc($result, $counter);
		
		foreach($records as $key => $value)
		{
			echo "<tr><td>";
			
			$result2 = pg_execute($conn, "check_for_interest", array($value, $_SESSION["Logon_user_id"]));
			if(pg_num_rows($result2) > 0)
			{
				echo "<b> Mutual Interest </b>";
			}
			createProfilePreview($value);
			
			echo $interests_of_user .= "</td></tr>";
			echo "
					<tr><td><input type=\"submit\" name=".$value." value=\"Remove Interest\"></input></td>
			";
			echo "<tr><td> <br/><br/> </td></tr>";
		}

	}
			
	echo "</table>
		</form>
	</div>
	";
}
else
{
	echo "No interests found.";
}

$result = pg_execute($conn, "select_all_other_interests", array($_SESSION["Logon_user_id"]));
if(pg_num_rows($result) > 0)
{
	echo "
	<div id=\"table\">
		<form  action=". $_SERVER['PHP_SELF']." method=\"post\">
			<table style=\"margin-left:auto; margin-right:auto; float:center;\" border=\"0\"  cellpadding=\"10\"> 
				<tr><td> <h3> Other User's Interests </h3> </td></tr>
				<tr><td> <p align=\"center\"> A mutual interest label is placed above users who have a mutual interest. </p> </td></tr>
	";
	
	for($counter = 0; $counter < pg_num_rows($result); $counter++)
	{
		$records = pg_fetch_assoc($result, $counter);
		
		foreach($records as $key => $value)
		{
			echo "<tr><td>";
			
			$result2 = pg_execute($conn, "check_for_interest", array($value, $_SESSION["Logon_user_id"]));
			if(pg_num_rows($result2) > 0)
			{
				echo "<b> Mutual Interest </b>";
			}
			createProfilePreview($value);
			
			echo $interests_of_user .= "</td></tr>";
			echo "
					<tr><td><input type=\"submit\" name=".$value." value=\"Remove Interest\"></input></td>
			";
			echo "<tr><td> <br/><br/> </td></tr>";
		}

	}
			
	echo "</table>
		</form>
	</div>
	";
}
else
{
	echo "No other user interests found.";
}

echo $interests_of_user;

if($_SERVER["REQUEST_METHOD"] == "POST")
{
	$button_name = array_keys($_POST);

	foreach($button_name as $key => $value)
	{
		$result = pg_execute($conn, "remove_interest", array($_SESSION["Logon_user_id"], $value));
	}
}
?>



<?php
include ("footer.php");
?>