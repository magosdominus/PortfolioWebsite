<?php
/* 
Name:Maitri Gemlawala, Matthew Cormier, Gurpreet Maan
WEDE3201
Date: 30th September,2015
*/

$title="Member Login";
$fileName="member_login.php";
$date="9/30/2015";
$Description= "In this lab, I am going to make a login form in which a user can enter their login ID and password to get all the information about last access of the login page. But for that user have to do registration so it will link up to the login page and will display the login page after registration. And you can check that if the registration is confirmed or not by using the data which should be matched with the existed table data. ";
$banner="Member Login Page";
include ("header.php");
require_once ("./includes/functions.php");
require_once("./includes/constants.php");
?>

<?php 
$error= "";
$output="";
$rows="";

if ($_SERVER["REQUEST_METHOD"] == "GET")
    {
	   $login_id = "";  
	   $password = "";
	}
	
else if($_SERVER["REQUEST_METHOD"] == "POST")
{
	// Added md5 hash to encrypt the password.
	$login_id = trim($_POST["login_id"]);
	$password = trim($_POST["password"]);
	
	$hashPassword = md5($password);
	
	if(!isset($login_id) || $login_id == "")
	{
		$error .= "You must provide your user id."; 
	}
	
	if(!isset($password))
	{
		$error .= "You must enter a password"; 
	}
	
	if($error == "")
	{		
		$result = pg_execute($conn, "user_login", array($login_id, $hashPassword));
		
		//$result = pg_prepare($conn, "login_query", 'SELECT * FROM users WHERE user_id = ".$login_id." AND password = ".$password."');
		//$result = pg_execute($conn, "login_query", $_POST);
		//$records = pg_num_rows($result);
		if(pg_num_rows($result))
		{ 
			  $output = "<h2><p align=center> Welcome Back ". pg_fetch_result($result, "first_name"). "";
			  $output .= " ".pg_fetch_result($result, "last_name") . "<br/>";
			  $output .= " our records show that your <br/> email address is: ".pg_fetch_result($result, "email_address")."<br/>";
			  $output .= " and you last accessed our system: ".pg_fetch_result($result,"last_access");
				
				echo $output;
				session_set_cookie_params(2592000);
				
				//$_SESSION["client_type"] = pg_fetch_result($result, "user_type")
				
				//if($_SESSION['client_type'] == 1)
				//{
					//$temp_user_name =  pg_fetch_result($result, "user_id")
				
					//$_SESSION["user_profile_info"] = pg_execute($conn, "retrieve_profile", $temp_user_name)
				//}
				
				$_SESSION["greeting"] =$output;
				
				$currentUser = pg_fetch_result($result, "user_id");
				$_SESSION["Logon_user_id"] = $currentUser;
				
				$userInformation = pg_fetch_assoc($result, 0);
				
				$_SESSION['user'] = $userInformation;
				
				$currentDate = date("Y-m-d");
				
				pg_execute($conn, "access_time_update", array($currentDate, $currentUser));
				
				// Set session variable to hold the user type.
				//$userType = pg_fetch_result($result, "user_type");
				
				//$_SESSION["user_type"] = $userType;
				
				//http_redirect("http://opentech2.durhamcollege.org/wede3201/gemlawalam/user_dashboard.php", array("name" => "value"), true, HTTP_REDIRECT_PERM);
				
				if ($userInformation["user_type"] == CLIENT || $userInformation["user_type"] == INCOMPLETE_CLIENT || $userInformation["user_type"] == DISABLED_CLIENT)
				{
					header('Location: user_dashboard.php');
				}
				elseif ($userType == ADMIN)
				{
					header('Location: admin_dashboard.php');
				}
				
				
		}
		else 
		{
		   $error = "<h2>Login/password not found in Database!! <br/> Please try again.</h2>";

		}
		
	}
}
?>


<hr/>

<div id="Login-Detail">
<h2>Pleasse log in </h2>
<br/>
<h4>Enter your login ID and password to connect to this system</h4>
<br/>
<h3><?php echo $error; ?></h3>
<h2><?php echo $output; ?></h2>
<h2><?php  
			if(isset($_SESSION["request_message"]))
			{
				echo $_SESSION["request_message"];
			}
	?> 
</h2>
<br/>
<div id="table">
	<form  action="<?php echo $_SERVER['PHP_SELF'];?>" method="post">
		<table style="margin-left:auto; margin-right:auto; float:center;" border="0"  cellpadding="10">
			<tr><td>Login ID: </td><td><input type="text" name="login_id" value="<?php echo $login_id ?>" size="18" /></td></tr>
			<tr><td>Password: </td><td><input type="password" name="password" value="" size="18" /></td></tr>
		
		
		<tr><td><input type="submit" value="Log In"></input></td>
			<td><input type="reset" value="Clear"></input></td></tr>
		</table>
	</form>
</div>

<h4 style="margin-left:auto; margin-right:auto;"> Please wait after pressing Log in while we retrieve your records from our database<br/>
(This may take a few moments)</h4>
<hr />
</div>
 <!--Click <a href="./lab9_user.sql"> here</a> to see the SQL file-->
<?php include ("footer.php"); ?>