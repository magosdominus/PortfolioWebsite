<?php
/*
Author: Matthew Cormier
Date: 9/20/2015
Description: This page holds functions to be used on other webpages.
*/
?>

<?php
function db_connect() 
{
	$conn = pg_connect("host=127.0.0.1 dbname=group15_db user=group15_admin password=group15mgm" ); 

	return $conn;
}

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

//$stmt1 = pg_prepare($conn, "statement");

?>

