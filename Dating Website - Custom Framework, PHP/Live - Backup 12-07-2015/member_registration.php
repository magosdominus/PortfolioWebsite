<?php
/* 
Name:Maitri Gemlawala, Matthew Cormier, Gurpreet Maan
WEDE3201
Date: 30th September,2015
*/
$title="Member Registration Page";
$fileName="member_registration.php";
$date="9/30/2015";
$Description= "On this lab, user should have to do registration and the user have to fill up all the personal information to register. ";
$banner="Registration Page";
include ("header.php");
require_once("./includes/constants.php");
require_once ("./includes/functions.php");
ob_start()
?>
<?php

if((isset($_SESSION["greeting"])))
{
	if(($_SESSION["user"]["user_type"] == DISABLED_CLIENT ))
	{
		header('Location: user_dashboard.php');
	}
	elseif(($_SESSION["user"]["user_type"] == CLIENT || $_SESSION["user"]["user_type"] == ADMIN || $_SESSION["user"]["user_type"] == IMCOMPLETE_CLIENT))
	{
		header('Location: update_account.php');
	}
}

$error= "";
$result="";
$rows="";
$register="Create";

if ($_SERVER["REQUEST_METHOD"] == "GET")
    {
	   $user_id = "";  
	   $password = "";
	   $conf_password = "";
	   $first_name="";
	   $last_name="";
	   $email_address="";
	   $birth_date="";
	}

else if($_SERVER["REQUEST_METHOD"] == "POST")	
{
	ob_start();
	$user_id = trim($_POST['user_id']);
	$password = trim($_POST['password']);
	$conf_password = trim($_POST['conf_password']);
	$first_name = trim($_POST['first_name']);
	$last_name = trim($_POST['last_name']);
	$birth_date=trim($_POST['birth_date']);;
	$email_address = trim($_POST['email_address']);
	
	//db_connect();
	
	if (!isset($user_id) || $user_id == ""  
		|| !isset($password) || $password == ""  
		|| !isset($conf_password) || $conf_password == ""  
		|| !isset($first_name) || $first_name == ""  
		|| !isset($last_name) || $last_name == ""  
		|| !isset($birth_date) || $birth_date == "" 
		|| !isset($email_address) || $email_address == "")
	{
		$error .= "<br /> You must enter a value into each text box. ";
	}
	
	else 
	{		
		if (strlen($user_id) < MINIMUM_ID_LENGTH || strlen($user_id) > MAXIMUM_ID_LENGTH)
		{
			$error .= "<br /> Login must be between " . MINIMUM_ID_LENGTH . " and " . MAXIMUM_ID_LENGTH . " characters. Your entered: " . $user_id;
		}
		
		else
		{
			$result = pg_execute($conn, "check_for_user", array($user_id));
			$records = pg_num_rows($result);
			
			if ($records == 1)
			{
				$error .= "<br /> User login already in use on this database. Your entered: " . $user_id;
			}
		}
		
		if (strlen($password) < MINIMUM_PASSWORD_LENGTH || strlen($password) > MAXIMUM_PASSWORD_LENGTH)
		{
			$error .= "<br /> Password must be between " . MINIMUM_PASSWORD_LENGTH . " and " . MAXIMUM_PASSWORD_LENGTH . " characters. Your entered: " . $password;
		}
		
		if (strlen($conf_password) < MINIMUM_PASSWORD_LENGTH || strlen($conf_password) > MAXIMUM_PASSWORD_LENGTH)
		{
			$error .= "<br /> Confirm Password must be between " . MINIMUM_PASSWORD_LENGTH . " and " . MAXIMUM_PASSWORD_LENGTH . " characters. ";
		}
		
		if (strcmp($password, $conf_password) !== 0)
		{
			$error .= "<br /> Password and Confirm Password do not match. ";
		}

		if (is_numeric($first_name))
		{
			$error .= "<br /> First Name must not be numeric. Your entered: " . $firstName;
		}
		
		if (strlen($first_name) > MAX_FIRST_NAME_LENGTH)
		{
			$error .= "<br /> First Name must be less than " . MAX_FIRST_NAME_LENGTH . " characters. Your entered: " . $firstName;
		}
		
		if (is_numeric($last_name))
		{
			$error .= "<br /> First Name must not be numeric. Your entered: " . $lastName;
		}
		
		if (strlen($last_name) > MAX_LAST_NAME_LENGTH)
		{
			$error .= "<br /> First Name must be less than " . MAX_LAST_NAME_LENGTH . " characters. Your entered: " . $lastName;
		}
		
		$age = calculateAge($birth_date);
		
		if ($age < MIN_AGE)
		{
			$error .= "<br /> You must be over the age of ".MIN_AGE." to create an account.";
		}
		
		if (strlen($email_address) > MAXIMUM_EMAIL_LENGTH)
		{
			$error .= "<br /> Email Address must be less than " . MAXIMUM_EMAIL_LENGTH . "characters. Your entered: " . $emailAddress;
		}
		
		if (!filter_var($email_address, FILTER_VALIDATE_EMAIL))
		{
			$error .= "<br /> Email Address is not valid. You entered: " . $emailAddress;
		}
	}
	
	if ($error == "")
	{
		$hashPassword = md5($password);
		
		$date = date("Y-m-d", time());
		$user_type = INCOMPLETE_CLIENT;
		
		pg_execute($conn, "create_account", array($user_id, $hashPassword, $user_type , $first_name, $last_name, $email_address, $birth_date, $date, $date));
		
		header("Location: ./member_login.php");
	}
	
	else 
	{
		$error .= "<br /> Please try again. ";
	}
}
	
	
	
?>

<hr/>
<div id= "Register-Detail">
<h2>Please register in our system</h2>
<br/>
<h4>Please enter your personal information</h4>
<br/>
</div>
<h3><?php echo $error; ?></h3>

<div id="table">
<form method="post" action="<?php echo $_SERVER['PHP_SELF'];  ?>">
	<table style="margin-left:auto; margin-right:auto; float:center;" border="0"  cellpadding="10">
	<tr>
		<td><strong>Login ID</strong></td>
		<td><input type="text" name="user_id" value="<?php echo $user_id; ?>" size="20" /></td>
	</tr>
	<tr>
		<td><strong>Password</strong></td>
		<td><input type="password" name="password" value= "<?php echo $password; ?>" size="20" /></td>
	</tr>
	<tr>
		<td><strong>Confirm Password</strong></td>
		<td><input type="password" name="conf_password" value="<?php echo $conf_password; ?>" size="20" /></td>
	</tr>
	<tr>
		<td><strong>First Name</strong></td>
		<td><input type="text" name="first_name" value="<?php echo $first_name; ?>" size="20" /></td>
	</tr>
	<tr>
		<td><strong>Last Name</strong></td>
		<td><input type="text" name="last_name"  value="<?php echo $last_name; ?>" size="20" /></td>
	</tr>
	<tr>
		<td><strong>Birth Date</strong></td>
		<td><input type="text" name="birth_date"  value="<?php echo $birth_date; ?>" size="20" /></td>
	</tr>
	<tr>
		<td><strong>Email Address</strong></td>
		<td><input type="text" name="email_address" value="<?php echo $email_address; ?>" size="20" /></td>
	</tr>
<tr>
	<td><input type="submit" value = "<?php echo $register; ?>" /></td><td><input type="reset" value = "Clear" /></td>
</tr></table>
</form>
</div>
<hr/>

 <!--Click <a href="./lab10.sql"> here</a> to see the SQL file-->
<?php
include ("footer.php")
?>