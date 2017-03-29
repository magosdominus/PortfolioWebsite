<?php
/*
Author: Matthew Cormier
Date: 10/09/2015
Description: This page holds functions to be used within the database layer of the website. pg preare statements, 
database connect, and radio button/drop down build functions will be on this page.
*/
?>

<?php
$conn = db_connect();

function db_connect() 
{
	// Get group 15 username and password.
	$conn = pg_connect("host=127.0.0.1 dbname=group15_db user=group15_admin password=group15mgm" ); 

	return $conn;
}


$stmt1 = pg_prepare($conn, "user_login", 'SELECT * FROM users WHERE user_id = $1 AND password = $2' );

$stmt2 = pg_prepare($conn, "user_retrieve", 'SELECT * FROM users WHERE user_id = $1 ');

$stmt8 = pg_prepare($conn, "profile_retrieve", 'SELECT * FROM profiles WHERE user_id = $1');

$stmt9 = pg_prepare($conn, "update_password" , 'UPDATE users SET password = $1 WHERE user_id = $2');

$stmt10 = pg_prepare($conn, "select_city" , 'SELECT * FROM city WHERE value=$1');

$stmt3 = pg_prepare($conn, "check_for_user", 'SELECT user_id FROM users WHERE user_id = $1');

$stmt4 = pg_prepare($conn, "create_account", 'INSERT INTO users (user_id, password, user_type, first_name, last_name, email_address, birth_date, enrol_date, last_access) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9) ');

$stmt17 = pg_prepare($conn, "update_account", 'UPDATE users SET first_name=$1,last_name=$2,birth_date=$3,email_address=$4 WHERE user_id=$5');

$stmt5 = pg_prepare($conn, "access_time_update", 'UPDATE users SET last_access = $1 WHERE user_id = $2');

$stmt6 = pg_prepare($conn, "update_user_type", 'UPDATE users SET user_type = $1 WHERE user_id = $2 ' );

$stmt7 = pg_prepare($conn, "create_user_profile", 'INSERT INTO profiles (user_id, user_bio, user_picture, user_headline, match_description, city, gender, gender_sought, relationship_sought, body_type, height, horoscope, ethnicity, religion, relationship_status, children, smoking, education, occupation, interests) 
																	VALUES ($1 , 	$2, 		$3, 			$4 , 			$5, 		$6, 	$7, 		$8 , 				$9 , 		$10 , 	  $11, 		$12 , 		$13 , 	  $14 , 	  	   $15 , 		$16 ,  	  $17 , 	$18 ,  	 	$19, 	   $20 )');

$stmt16 = pg_prepare($conn, "update_user_profile", 'UPDATE profiles SET user_id=$1, user_bio=$2, user_picture=$3, user_headline=$4, match_description=$5, city=$6, gender=$7, gender_sought=$8, relationship_sought=$9, body_type=$10, height=$11, horoscope=$12, ethnicity=$13, religion=$14, relationship_status=$15, children=$16, smoking=$17, education=$18, occupation=$19, interests=$20 WHERE user_id=$21');
																	
																	
$stmt12 = pg_prepare($conn, "retrieve_profile", 'SELECT * FROM profiles WHERE user_id = $1');
$stmt13 = pg_prepare($conn, "retrieve_user_account" , 'SELECT * FROM users WHERE user_id = $1');

$stmt14 = pg_prepare($conn, "update_image_count", 'UPDATE profiles SET user_picture=$1 WHERE user_id = $2');

$stmt15 = pg_prepare($conn, "check_images", 'SELECT user_picture FROM profiles WHERE user_id = $1');

$stmt18 = pg_prepare($conn, "check_email", 'SELECT * from users WHERE user_id=$1 AND email_address=$2');

$stmt19 = pg_prepare($conn, "update_request_password", 'UPDATE users SET password = $1 WHERE user_id = $2');

$stmt20 = pg_prepare($conn, "check_for_interest", 'SELECT * FROM interests WHERE user_id = $1 AND interest_id = $2');

$stmt21 = pg_prepare($conn, "remove_interest", 'DELETE FROM interests WHERE user_id = $1 AND interest_id = $2');
$stmt22 = pg_prepare($conn, "add_interest", 'INSERT INTO interests (user_id, interest_id) VALUES($1, $2)');

$stmt23 = pg_prepare($conn, "select_all_interests", 'SELECT interest_id FROM interests WHERE user_id = $1');
$stmt24 = pg_prepare($conn, "select_all_other_interests", 'SELECT user_id FROM interests WHERE interest_id = $1');

$stmt25 = pg_prepare($conn, "check_if_reported",'SELECT * FROM offensives WHERE reporting_user = $1 AND offending_user = $2');
$stmt26 = pg_prepare($conn, "report_profile", 'INSERT INTO offensives (reporting_user, offending_user, status) VALUES($1, $2, $3)');
$stmt27 = pg_prepare($conn, "check_for_open_reports", 'SELECT offending_user FROM offensives WHERE status = $1');

$stmt28 = pg_prepare($conn, "disable_user", 'UPDATE users SET user_type = $1 WHERE user_id = $2');
$stmt29 = pg_prepare($conn, "close_report", 'UPDATE offensives SET status = $1 WHERE offending_user = $2');
$stmt30 = pg_prepare($conn, "select_disabled_users", 'SELECT user_id FROM users WHERE user_type = $1');

/* Use these execute functions to query the database. Use similiar functions for all the queries on the site. */
/* $result = pg_execute($conn, "user_login", array($user_id, $hashPassword)); */

function buildOrCondition($table, $sum)
{
        $output = $table." IN(";
        foreach($sum as $checkU) {
        
           if($checkU !="," && $checkU !="" && $checkU !="undefined"){
          $output .=  $checkU ." , ";	
		  }
           
        }
		
		$output = substr($output, 0, strlen($output) - strlen(" , "));
		$output .=" )";
        //$output = "AND(";
        //foreach($sum as $checkU) {
        

 //         $output .=  $table." = ".$checkU ." OR ";	
           
   //     }
		
	//	$output = substr($output, 0, strlen($output) - strlen(" OR "));
		//$output .=" )";
		return $output;
	//$output = "AND (";
	//$i = 0;
	
//	while(pow(2, $i) <= $sum)
	//{
		//$output .= $table ." = " . pow(2, $i) . " OR ";
		//$i++;
	//}
	//$output = substr($output, 0, strlen($output) - strlen(" OR "));
	//$output .= ")";
	//return $output;
}
function buildDropDown($tableName, $preselected)
{
	global $conn;
		
	$sql = 'SELECT * FROM ' . $tableName;
	
	$result = pg_query($conn, $sql);

	$records = pg_num_rows($result);
	
	if ($records > 0)
	{
		echo "\n<select name='" . $tableName . "'>";
		
		for($i = 0; $i < $records; $i++)
		{
			$property = pg_fetch_result($result, $i, "property");
			$value = pg_fetch_result($result, $i, "value");
			
			echo " \n\t<option ";
			if ($value == $preselected)
			{	
				if($value  == $preselected) 
				{
					echo 'selected = "selected"';
				}
			}
			echo "value=\"". $value."\"> " . $property . " </option>";
		}
		echo "\n</select>";
	}
}

function buildRadio($tableName, $preselected)
{
	global $conn;
		
	$sql = 'SELECT * FROM ' . $tableName;
	
	$result = pg_query($conn, $sql);

	$records = pg_num_rows($result);
	
	if ($records > 0)
	{		
		echo "\n<fieldset id='" . $tableName . "'>";
		for($i = 0; $i < $records; $i++)
		{
			$property = pg_fetch_result($result, $i, "property");
			$value = pg_fetch_result($result, $i, "value");
			
			echo " \n\t<label ";
			echo "value=\"". $value."\"> " . $property . " </label>";
			
			echo " \n\t<input type=\"radio\" name='" . $tableName . "' value='" . $value . "' ";
			if ($value == $preselected)
			{
				if($value  == $preselected) 
				{
					echo 'checked = "checked"';
				}
			}
			echo "/>";
			
		}
		echo "\n</fieldset>";
	}
}

function buildCheckBox($tableName, $preselected)
{
	global $conn; 
	
	$sql = ' SELECT * FROM ' . $tableName;
	
	$result = pg_query($conn, $sql);

	$records = pg_num_rows($result);
	
	if ($records > 0)
	{
		for($i = 0; $i < $records; $i++)
		{
			$property = pg_fetch_result($result, $i, "property");
			$value = pg_fetch_result($result, $i, "value");
			
			echo " \n\t<input type=\"checkbox\" ";
			
			if($value  == $preselected) 
			{
				echo 'checked = "checked"';
			}
		
			echo "name='" . $tableName ."[]' value=\"". $value."\" />" . $property;
		
		}
	}
}

function buildCityCheckBox($tableName, $preselected)
{
	global $conn; 
	
	$sql = ' SELECT * FROM ' . $tableName;
	
	$result = pg_query($conn, $sql);

	$records = pg_num_rows($result);
	
	if ($records > 0)
	{
		for($i = 0; $i < $records; $i++)
		{
			$property = pg_fetch_result($result, $i, "property");
			$value = pg_fetch_result($result, $i, "value");
			
			echo " \n\t<input  type=\"checkbox\" ";
			
			if($value  == $preselected) 
			{
				echo 'checked = "checked"';
			}
		
			echo "class=" . $property . " name=\"city[]\" value=" . $value . " >" . $property;
		
		}
	}
}

function createProfilePreview($user_id)
{
	global $conn; 
	
	$sql = "SELECT * FROM profiles INNER JOIN users ON profiles.user_id = users.user_id WHERE users.user_id = '". $user_id ."' AND profiles.user_id ='" . $user_id . "' ";
	$result = pg_query($conn, $sql);
	
	if(pg_num_rows($result) > 0 || pg_fetch_result($result, "user_type" != DISABLED_CLIENT))
	{
		$output = "
			<!-- Search Result Table -->
			<table cellspacing=\"15px\" cellpadding=\"12px\" style=\"text-align:left; margin: 0px auto 0px auto\" width=\"100%\">
				<tr>
					<td class=\"para20\" >
						<h4> <a href=\"display_profile.php?id=" . pg_fetch_result($result, "user_id") . "\">" . pg_fetch_result($result, "user_id") . "</a>'s Profile Preview </h4>
						<p><img src=\"images/profile.png\" width=\"200\" height=\"200\" alt=\"default profile picture\" style=\"float:left\"/></p>
					</td>
					<td class=\"para80\">
						<p><b>Profile Headline: " . pg_fetch_result($result, "user_headline") . "</b></p>
						<p><b>User Self Description:" . pg_fetch_result($result, "user_bio") . "</b></p>
						
					</td>
				</tr>	
			</table> ";
		echo $output;
	}
}


/* returns the name of the field in the parameter table */
function getProperty($tableName, $tableValue)
{
	global $conn;
		
	$sql = "SELECT property FROM " . $tableName . " WHERE value=".$tableValue;
	/*"  $tableValue ""  ;*/
	
	$result = pg_query($conn, $sql);
	
	$records = pg_num_rows($result);
	
	if ($records > 0)
	{
		$output = pg_fetch_result($result, "property");
		return $output;
	}	
	else
	{
		$output = "";
		return $output;
	}
}


function buildProfile($user_id)
{
	global $conn;
	
	$profile_sql = "SELECT * FROM profiles WHERE user_id ='" . $user_id . "'"; 
	$user_sql = "SELECT * FROM users WHERE user_id ='" . $user_id . "'"; 
	
	$profile_result = pg_query($conn, $profile_sql);
	$user_result = pg_query($conn, $user_sql); 
	
	$userName = pg_fetch_result($user_result, "user_id");

	$self_description = pg_fetch_result($profile_result, "user_bio");
	$user_headline = pg_fetch_result($profile_result, "user_headline");

	$first_name = pg_fetch_result($user_result, "first_name");
	$last_name = pg_fetch_result($user_result, "last_name");
	$name = $first_name . " " . $last_name;
	
	$body_type = pg_fetch_result($profile_result, "body_type");
	$body_type = getProperty("body_type", $body_type);	
	
	$gender = pg_fetch_result($profile_result, "gender");
	$gender = getProperty("gender", $gender);
	
	$height = pg_fetch_result($profile_result, "height");
	
	$gender_sought = pg_fetch_result($profile_result, "gender_sought");
	$gender_sought = getProperty("gender_sought", $gender_sought);
	
	$relationship_sought = pg_fetch_result($profile_result, "relationship_sought");
	$relationship_sought = getProperty("relationship_sought", $relationship_sought);	
	
	$age = pg_fetch_result($user_result, "birth_date");
	$age =  calculateAge($age);
	
	$horoscope = pg_fetch_result($profile_result, "horoscope");
	$horoscope = getProperty("horoscope", $horoscope);
		
	$ethnicity = pg_fetch_result($profile_result, "ethnicity");
	$ethnicity = getProperty("ethnicity", $ethnicity);
		
	$religion = pg_fetch_result($profile_result, "religion");
	$religion = getProperty("religion", $religion);
		
	$relationship_status = pg_fetch_result($profile_result, "relationship_status");
	$relationship_status = getProperty("relationship_status", $relationship_status);
		
	$children = pg_fetch_result($profile_result, "children");
	$children = getProperty("children", $children);
		
	$smoking = pg_fetch_result($profile_result, "smoking");
	$smoking = getProperty("smoking", $smoking);
		
	$city = pg_fetch_result($profile_result, "city");
	$city = getProperty("city", $city);
	
	$education = pg_fetch_result($profile_result, "education");
	$education = getProperty("education", $education);
		
	$occupation = pg_fetch_result($profile_result, "occupation");
	$interests = pg_fetch_result($profile_result, "interests");

	$output = "<table cellspacing=\"15px\" cellpadding=\"12px\" style=\"text-align:left; margin: 0px auto 0px auto\">
	<tr>
		<td>
			<h3>
				" .  $user_id  . "'s Profile
			</h3>
		</td>
	</tr>
	<tr>
		<td class=\"para\">
			<p><img src=\"images/profile.png\" width=\"300\" height=\"300\" alt=\"default profile picture\" style=\"float:left;\"/>
			</p>
		</td>
		<td class=\"descPara\" >
			<p><b>
			User Self Description: " . $self_description . "
			</b></p>
		</td>
	</tr>
	<tr>
		<td class=\"para\">
			<div id=\"table\">
				<form action=" . $_SERVER['PHP_SELF'] . " method=\"post\">
					<table style=\"margin-left:auto; margin-right:auto;\">
						<tr><td><input type=\"button\" name=\"btnMessage\" value=\"Message User\" size=\"20\"/></td></tr>
					</table>
				</form>			
			</div>
		</td>
		<td class=\"para\">
			<p><b>
			User Headline: " . $user_headline . "
			</b></p>
		</td>

	</tr>	
	<tr>
		<td colspan=\"2\">
				<table style=\"width:100%;border:0px solid black\"><tr>
					<td style=\"width:20%;\">
						<p><b>
						Name:
						<br/>
						Gender:
						<br/>
						Gender sought:
						<br/>
						Relationship sought:
						<br/>
						<br/>
						
						Body type:
						<br/>
						Height:
						<br/>
						Age:
						<br/>
						Horoscope:
						<br/>
						Ethnicity:
						<br/>
						Religion:
						<br/>
						<br/>
						
						Relationship status:
						<br/>
						Children:
						<br/>
						Smoking:
						<br/>
						<br/>
		
						City:
						<br/>
						Education:
						<br/>
						Occupation:
						<br/>
						Interests:
						<br/>
						</b></p>	
					</td>
					<td>
						<p style=\"float:left\">
						" . $name . "
						<br/>
						" . $gender . "
						<br/>
						" . $gender_sought . "
						<br/>
						" . $relationship_sought . "
						<br/>
						<br/>
						
						" . $body_type . "
						<br/>
						" . $height . "
						<br/>
						" . $age . "
						<br/>
						" . $horoscope . "
						<br/>
						" . $ethnicity . "
						<br/>
						" . $religion . "
						<br/>
						<br/>
						
						" . $relationship_status . "
						<br/>
						" . $children . "
						<br/>
						" . $smoking . "
						<br/>
						<br/>
		
						" . $city . "
						<br/>
						" . $education . "
						<br/>
						" . $occupation . "
						<br/>
						" . $interests . "
						<br/>
						</p>	
					</td>
				</tr></table>
		</td>
	</tr>
</table> ";

echo $output;
}

function buildImages($user_id, $number_of_images)
{
	$dir = "./user_images/" . $user_id."/";
	
	$output = "";
	
	if ($opendir = opendir($dir) !== FALSE)
	{
		$output .= "
		
		<table cellspacing=\"15px\" cellpadding=\"12px\" style=\"text-align:left; margin: 0px auto 0px auto\" width=\"100%\">
					<tr>
						<td>
							<h3>
							<br/>
							Uploaded Images
							</h3>
						</td>
					</tr>
					<tr>
						<td class=\"para\">
							<div id=\"table\">
								<table style=\"margin-left:auto; margin-right:auto;\" >
		";
		
		//while ( ($file = readdir($opendir)) !== FALSE)
		for ($image_counter = 1; $image_counter <= $number_of_images; $image_counter++)
		{
			//$file = readdir($opendir);
			
			$file = $dir. $user_id ."_".$image_counter.".jpg";
			if (file_exists($file));
			//if ($file != "." && $file != "..")
			{
				$output .= "
										<tr>
											<td>
												<img src='". $file."?".time()."' >
											</td>
											<td>
												<input type=\"checkbox\" name=\"images[]\" value=\"". $image_counter ."\" />
											</td>
										</tr>	
								
				";
			}

			
		}
		
		$output .= "				</table>
								</form>			
							</div>
						</td>
					</tr>
				</table>"
		
		;
		
	
	}
	
	else
	{
		$output = "Image build error.";
		
	}
		return $output;
}




?>

	

