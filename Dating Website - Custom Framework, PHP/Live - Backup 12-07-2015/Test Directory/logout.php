<?php
/* 
Name:Maitri Gemlawala, Matthew Cormier, Gurpreet Maan
WEDE3201
Date: 30th September,2015
*/
$title="User Dashboard";
$fileName="user_dashboard.php";
$date="9/30/2015";
$Description= "In this lab, I am going to make a login form in which a user can enter their login ID and password to get all the information about last access of the login page. But for that user have to do registration so it will link up to the login page and will display the login page after registration. And you can check that if the registration is confirmed or not by using the data which should be matched with the existed table data. ";
$banner="Dashboard";
include ("header.php");
require_once ("./includes/functions.php");
?>

<?php
session_start();
unset($_SESSION['greetings']);
session_destroy();

header("Location: member_login.php");
exit;
?>


<?php include ("footer.php"); ?>