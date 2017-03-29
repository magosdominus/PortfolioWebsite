<?php
/*
Author: Matthew Cormier
Date: 9/20/2015
Description: This page holds functions to be used on other webpages.
*/
?>

<?php

function displayCopyrightInfo()
{
	$webMaster = "Matthew Cormier, Gurpreet Maan, Maitri Gemlawala";
	
	?>
	&copy; <?php echo date('Y') . " $webMaster" . ". All rights reserved"; ?>
	<?php
}

function dump($arg)
{
	echo "<pre>";
	echo (is_array($arg))? print_r($arg): $arg;
	echo "</pre>";
}

function calculateAge($date)
{
	$year = date("Y", strtotime($date));
	$month = date("m", strtotime($date));
	$day = date("d", strtotime($date));
	$year_diff = date("Y") - $year;
	$month_diff = date("m") - $month;
	$day_diff = date("d") - $day;
	
	if ($day_diff < 0 && $month_diff == 0) 
	{
		$year_diff--;
	}
	
	if ($day_diff < 0 && $month_diff < 0) 
	{
		$year_diff--;
	}
	
	$age = $year_diff;
	
	return $age;
}

/*
	this function should be passed a integer power of 2, and any 
	decimal number,	it will return true (1) if the power of 2 is 
	contain as part of the decimal argument
*/
function isBitSet($power, $decimal) {
	if((pow(2,$power)) & ($decimal)) 
		return 1;
	else
		return 0;
} 

/*
	this function can be passed an array of numbers 
	(like those submitted as part of a named[] check 
	box array in the $_POST array).
*/
function sumCheckBox($array)
{
in_array("Orange",$_POST["food"]);
	//$num_checks = count($array); 
	//$sum = 0;
	//for ($i = 0; $i < $num_checks; $i++)
//	{
	//  $sum += $array[$i]; 
	//}
	//return $sum;
}

/*
This function generates a random password. Returns the password.
*/
function random_password( $length = 8 ) {
    $chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_-=+;:,.?";
    $password = substr( str_shuffle( $chars ), 0, $length );
    return $password;
}

?>

