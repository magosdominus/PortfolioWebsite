<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<!-- Latest compiled and minified CSS -->


<?php


$sql = "

INSERT INTO users(user_id, password, user_type, email_address, first_name, last_name, birth_date, enrol_date, last_access)
		VALUES ('testName1','".md5($password)."', 'c', 'email1@gmail.com', 'name1', 'name2', '1990-01-01', '2015-09-18', '2015-09-30')";
$t=pg_query($conn, $sql);

$sql = "

INSERT INTO users(user_id, password, user_type, email_address, first_name, last_name, birth_date, enrol_date, last_access)
		VALUES ('testName2','".md5($password)."', 'a', 'email1@gmail.com', 'name1', 'name2', '1990-01-01', '2015-09-18', '2015-09-30')";
$t=pg_query($conn, $sql);

$sql = "

INSERT INTO users(user_id, password, user_type, email_address, first_name, last_name, birth_date, enrol_date, last_access)
		VALUES ('testingProfile','".md5($password)."', 'i', 'email1@gmail.com', 'name1', 'name2', '1990-01-01', '2015-09-18', '2015-09-30')";
$t=pg_query($conn, $sql);

$sql = "

INSERT INTO users(user_id, password, user_type, email_address, first_name, last_name, birth_date, enrol_date, last_access)
		VALUES ('TestingSearch','".md5($password)."', 'x', 'email1@gmail.com', 'name1', 'name2', '1990-01-01', '2015-09-18', '2015-09-30')";
$t=pg_query($conn, $sql);

	




















"



























?>