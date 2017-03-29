<?php

/*
Author: Matthew Cormier
Date: 9/20/2015
*/

$title="WEDE3201 - Create/Update Profile Page";
$fileName="create_profile.php";
$date="9/20/2015";
$description= "This page displays a user's profile creation/update page";
$banner="Create/Update Profile";
include ("header.php");

require_once ("./includes/constants.php");
require_once ("./includes/db.php");
require_once ("./includes/functions.php");
?> 

<?php

if(!(isset($_SESSION["greeting"])))
{
	header('Location: member_login.php');
}
if(($_SESSION["user"]["user_type"] == DISABLED_CLIENT ))
{
	header('Location: user_dashboard.php');
}
elseif(($_SESSION["user"]["user_type"] == CLIENT || $_SESSION["user"]["user_type"] == ADMIN || $_SESSION["user"]["user_type"] == IMCOMPLETE_CLIENT))
{
	header('Location: update_profile.php');
}
	

	$relationship_sought = NULL;
	$body_type = NULL;
	$ethnicity = NULL;
	$religion = NULL;
	$relationship_status = NULL;
	$children = NULL;
	$smoking = NULL;
	$education = NULL;
	$city = NULL;
	$horoscope = NULL;
	
	$error = "";	
	
if ($_SERVER["REQUEST_METHOD"] == "GET")
{
	$txtHeight = "";
	$txtOccupation = "";
	$txtInterests = "";
	$txtProfileDescription = "";
	$txtMatchDescription = "";
	$txtProfileHeadline = "";
	
	$gender = "";
	$gender_sought = "";
	
}

else if($_SERVER["REQUEST_METHOD"] == "POST")	
{
	$relationship_sought = $_POST['relationship_sought'];
	$body_type = $_POST['body_type'];
	$horoscope = $_POST['horoscope'];
	$ethnicity = $_POST['ethnicity'];
	$religion = $_POST['religion'];
	$relationship_status = $_POST['relationship_status'];
	$children = isset($_POST['children'])?$_POST['children']:"";
	$smoking = isset($_POST['smoking'])?$_POST['smoking']:"";
	$education = $_POST['education'];

	$gender = (isset($_POST['gender']))?$_POST['gender']:"";
	if ($gender == "")
	{
		$error .= "<br /> You must select your gender! ";
	}
	$gender_sought = (isset($_POST['gender_sought']))?$_POST['gender_sought']:"";
	if ($gender_sought == "")
	{
		$error .= "<br /> You must select the gender you are seeking! ";
	}
	
	$city = (isset($_POST['city']))?$_POST['city']:"";
	if ($city == "" || strlen($city) == 0)
	{
		$error .= "<br /> You must select a city! ";
	}
	
	
	$txtHeight = trim($_POST['txtHeight']);
	$txtOccupation = trim($_POST['txtOccupation']);
	$txtInterests = trim($_POST['txtInterests']);
	$txtProfileDescription = trim($_POST['txtProfileDescription']);
	$txtMatchDescription = trim($_POST['txtMatchDescription']);
	$txtProfileHeadline = trim($_POST['txtProfileHeadline']);
	
	if ( strlen($txtProfileDescription) <= 0 
		|| strlen($txtMatchDescription) <= 0
		|| strlen($txtProfileHeadline) <= 0)
	{
		$error .= "<br /> Profile description, match description, and headline must all have input! ";
	}
	
	if (strlen($txtHeight) > 10)
	{
		$error .= "<br /> Height must be 10 or less characters. ";
	}
	
	if (strlen($txtOccupation) > 255)
	{
		$error .= "<br /> Occupation must be 255 or less characters. ";
	}
	
	if (strlen($txtInterests) > 255)
	{
		$error .= "<br /> Interests must be 255 or less characters. ";
	}
	
	if (strlen($txtProfileDescription) > 1000)
	{
		$error .= "<br /> Profile bio must be 1000 or less characters. ";
	}
	
	if (strlen($txtProfileHeadline) > 100)
	{
		$error .= "<br /> Profile headline must be 100 or less characters.";
	}
	
	if ($error == "")
	{
		
		$user_name = $_SESSION['user']['user_id'];
		
		pg_execute($conn, "create_user_profile", array($user_name, $txtProfileDescription, 0, $txtProfileHeadline, $txtMatchDescription, $city, $gender_sought, 
														$gender_sought, $relationship_sought, $body_type, $txtHeight , $horoscope, $ethnicity, $religion, $relationship_status, 
														$children, $smoking, $education, $txtOccupation, $txtInterests));
		
		$userType = CLIENT;
		
		pg_execute($conn, "update_user_type" , array($userType, $user_name));
		
		//header('Location: user_dashboard.php');
	}
	else
	{
		$error .= "<br /> Please try again. ";
	}
}
?>

<table cellspacing="15px" cellpadding="12px" style="text-align:left; margin: 0px auto 0px auto" width="100%">
	<tr>
		<td>
			<h3>
			<br/>
			Create User Profile 
			</h3>
		</td>
	</tr>
	<tr>
		<?php echo $error; ?>
	</tr>
	<tr>
		<td class="para">
			<div id="table">
				<form action="<?php echo $_SERVER['PHP_SELF'];  ?>" method="post" >
					<table style="margin-left:auto; margin-right:auto;" >
						
						<tr><td><strong>Headline:</strong></td><td><input type="text" name="txtProfileHeadline"  value="<?php echo $txtProfileHeadline; ?>" /></td></tr>
						<tr><td><strong>Description of Myself:</strong></td><td><textarea name="txtProfileDescription" cols="40" rows="5" ><?php echo $txtProfileDescription; ?></textarea></td></tr>
						<tr><td><strong>Description of my Perfect Match:</strong></td><td><textarea name="txtMatchDescription" cols="40" rows="5" ><?php echo $txtMatchDescription; ?></textarea><br/><br/></td></tr>
						
						<tr><td><strong>Your Gender:</strong></td><td>	
						    <?php buildRadio("gender", $gender); ?>
						</td></tr>
						<tr><td><strong>Gender Sought:</strong></td><td>	
							<?php buildRadio("gender_sought", $gender_sought); ?>
						</td></tr>
						
						<tr><td><strong>Relationship Sought: </strong></td><td>
							<?php buildDropDown("relationship_sought", $relationship_sought); ?>
						</td></tr>
					
						<tr><td><br/><br/><strong>Body Type</strong></td><td><br/><br/>
							<?php buildDropDown("body_type", $body_type); ?>
						</td></tr>
						
						<tr><td><strong>Height:</strong></td><td><input type="text" name="txtHeight" value="<?php echo $txtHeight ?>" /></td>
						
						<tr><td><strong>Horoscope: </strong></td><td>
							<?php buildDropDown("horoscope", $horoscope); ?>
						</td></tr>
						
						<tr><td><strong>Ethnicity: </strong></td><td>
							<?php buildDropDown("ethnicity", $ethnicity); ?>
						</td></tr>
						
						<tr><td><strong>Religion:</strong></td><td>
							<?php buildDropDown("religion", $religion); ?>
						</td></tr>
						
						<tr><td><br/><br/><strong>Relationship Status:</strong></td><td><br/><br/>
							<?php buildDropDown("relationship_status", $relationship_status); ?>
						</td></tr>
						
						<tr><td><strong>Children?</strong></td><td>	
							<?php buildRadio("children", $children); ?>
						</td></tr>
						
						<tr><td><strong>Smoking?</strong></td><td>	
							<?php buildRadio("smoking", $smoking); ?>
						</td></tr><br/><br/>
						
						<tr><td><br/><br/><strong>City:</strong></td>
						<td>
							<br/><br/>
							<?php buildDropDown("city", $city); ?>
						</td>
						
						<tr><td><strong>Occupation:</strong></td><td><input type="text" name="txtOccupation" value="<?php echo $txtOccupation ?>"></input></td>
						
						<tr><td><strong>Education:</strong></td><td>
							<?php buildDropDown("education", $education); ?>
						</td></tr>	
						
						<tr><td><strong>Interests/Hobbies:</strong></td><td><textarea name="txtInterests" cols="40" rows="5" value="<?php echo $txtInterests ?>" ></textarea></td>
						
						<tr><td colspan=3 align="center"><br/><br/><input type="submit" name="btnCreateProfile" value="Create Profile"size="20"></input>
						<input type="Reset" name="btnClear" value="Clear Page"size="20" ></input></td>
						</tr>
					</table>
				</form>			
			</div>
		</td>
	</tr>
</table>



<?php
include ("footer.php");
?>