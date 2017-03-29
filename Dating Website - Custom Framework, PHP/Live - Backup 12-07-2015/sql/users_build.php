<?php
require "../includes/functions.php";
require "../includes/db.php";
$conn = db_connect();
		
		// Insert statements for testing login. Remove insert statements after site is working on opentech.
		$password ="password";
		$sql = "
		INSERT INTO users(user_id, password, user_type, email_address, first_name, last_name, birth_date, enrol_date, last_access)
		VALUES ('matthewcormier','".md5($password)."', 'i', 'matthewcormier1993@gmail.com', 'Matthew', 'Cormier', '1993-07-23', '2015-09-18', '2015-09-30')";
$t=pg_query($conn, $sql);
		$sql = "INSERT INTO users(user_id, password, user_type, email_address, first_name, last_name, birth_date, enrol_date, last_access)
		VALUES ('test2','" . md5($password)."', 'i', 'testemail@email.com', 'Mike', 'Johnson', '1980-05-18', '2015-09-20', '2015-10-1')";
$t=pg_query($conn, $sql);
		$sql = "INSERT INTO users(user_id, password, user_type, email_address, first_name, last_name, birth_date, enrol_date, last_access)
		VALUES ('test3','".md5($password)."', 'i', 'testemail2@gmail.com', 'John', 'Macklem', '1967-03-28', '2015-09-20', '2015-10-2')";
$t=pg_query($conn, $sql);
		$sql = "INSERT INTO users(user_id, password, user_type, email_address, first_name, last_name, birth_date, enrol_date, last_access)
		VALUES ('test4','".md5($password)."', 'i', 'testemail3@gmail.com', 'Tom', 'Smith', '1975-12-15', '2015-09-25', '2015-10-5')";
	
	 $t=pg_query($conn, $sql);
?>