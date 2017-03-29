<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<!-- Latest compiled and minified CSS -->


	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="./css/style.css" /> 
		<!-- CHANGE THE HREF ABOVE TO intn2201.css AFTER YOU HAVE INCLUDED THE CONTENTS OF style.css INTO IT -->
	<!--
	Author: Maitri Gemlawala, Matthew Cormier,Gurpreet Maan
	Title: <?php echo $title; ?>
	Filename: <?php echo $fileName; ?> 
	Date:<?php echo $date; ?>
	Description: <?php echo $Description; ?>
	Banner: <?php echo $banner; ?>
	-->
	
	<?php
		error_reporting(E_ALL);
		ini_set('display_errors', true);
		require_once ("./includes/db.php");
		require_once ("./includes/constants.php");
		require_once ("./includes/functions.php");
		session_start();
		
		$loggedInUserID = "";
		if((isset($_SESSION["greeting"])))
		{
			$loggedInUserID = $_SESSION["Logon_user_id"];
			$profileLink = '<a href="display_profile.php?id=' . $loggedInUserID . '">My Profile </a>';
{
			if($_SESSION["user"]["user_type"] == DISABLED_CLIENT)
			{
				$linkList = "
				<li><a href=\"index.php\">Home</a></li>	
				<li><a href=\"user_dashboard.php\">Dashboard</a></li>
				<li><a href=\"logout.php\">Log Out</a></li>
				";
			}
			elseif($_SESSION["user"]["user_type"] == CLIENT)
			{
				$linkList = "
				<li><a href=\"index.php\">Home</a></li>	
				<li><a href=\"member_registration.php\">Update Account</a></li>
				<li><a href=\"create_profile.php\">Update Profile</a></li>
				<li><a href=\"file_upload.php\">Upload Images</a></li>
				<li><a href=\"user_dashboard.php\">Dashboard</a></li>
				<li><a href=\"search.php\">Profile Search</a></li>
				<li><a href=\"interests.php\">User Interests</a></li>
				<li> ". $profileLink." </li>
				<li><a href=\"logout.php\">Log Out</a></li>
				";
			}
			elseif($_SESSION["user"]["user_type"] == ADMIN)
			{
				$linkList = "
				<li><a href=\"index.php\">Home</a></li>	
				<li><a href=\"member_registration.php\">Update Account</a></li>
				<li><a href=\"admin_dashboard.php\">Dashboard</a></li>
				<li><a href=\"disabled-users.php\">Disabled Users</a></li>
				<li><a href=\"search.php\">Profile Search</a></li>
				<li><a href=\"logout.php\">Log Out</a></li>
				";
			}
			elseif($_SESSION["user"]["user_type"] == INCOMPLETE_CLIENT)
			{
				$linkList = "
				<li><a href=\"index.php\">Home</a></li>	
				<li><a href=\"member_registration.php\">Update Account</a></li>
				<li><a href=\"create_profile.php\">Create Profile</a></li>
				<li><a href=\"admin_dashboard.php\">Dashboard</a></li>
				<li><a href=\"logout.php\">Log Out</a></li>
				";
			}
		}
		// Full link list
		/*
			<li><a href="index.php">Home</a></li>	
			<li><a href="member_login.php">Member Login</a></li>
			<li><a href="member_registration.php">Member Registration</a></li>
			<li><a href="create_profile.php">Create Profile</a></li>
			<li><a href="user_dashboard.php">Dashboard</a></li>
			<li><a href="search.php">Profile Search</a></li>
			<li> <?php echo $profileLink; ?></li>
			<li><a href="logout.php">Log Out</a></li>
			<li><a href="file_upload.php">Upload Images</a></li>
			<li><a href="user_password_request.php">Request New Password</a></li>
			<li><a href="interests.php">User Interests</a></li>
			<li><a href="disabled-users.php">Disabled Users</a></li>
		*/
		}
		else
		{
			$profileLink = '<a href="display_profile.php?link=' . $loggedInUserID . '" >My Profile </a>';
			
			$linkList = "
			<li><a href=\"index.php\">Home</a></li>	
			<li><a href=\"member_login.php\">Member Login</a></li>
			<li><a href=\"user_password_request.php\">Request New Password</a></li>
			<li><a href=\"member_registration.php\">Create Account</a></li>
			";
		}	
		
	?>
	
	<title><?php echo $title;?></title>
</head>
<body>
<div id="container">
	<div id="header">
		<img src="./images/images.jpg" alt="YOUR SITE LOGO ALT" class="img-circle" />
		<h1>
			LoveLinks.com
		</h1>
	</div>
	<div id="sites">
		<ul>
			<?php echo $linkList; ?>
		</ul>
	</div>

