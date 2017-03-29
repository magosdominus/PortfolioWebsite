<?php

/* 
Name:Maitri Gemlawala, Matthew Cormier, Gurpreet Maan
WEDE3201
Date: 11/4/2015

Description: Search page that will allow the a completed user to search for matches based on selectable criteria.
			 Selected criteria will be saved to a cookie. If no matches are found a messages will be outputed.
			 If there are matches found, the user will be redirected to the result page.
*/

$title="WEDE3201 - Search Page";
$fileName="search.php";
$date="11/4/2015";
$Description= "This page allows the user to search other users through filters.";
$banner="Search Profiles";
include ("header.php");
?> 

<?php
		
$txtMinAge = "";
$txtMaxAge = "";
$relationship_sought = "";
$body_type = "";
$horoscope = "";
$ethnicity = "";
$religion = "";
$children = "";
$smoking = "";
$education = "";
$city_names_output = "";


$error = "";
	
$sql = "";

// check if user is logged in.

if(!(isset($_SESSION["greeting"])))
{
	header('Location: member_login.php');
}
else
{	
	// if the user is not a completed user.
	if(($_SESSION{"user"}["user_type"] == INCOMPLETE_CLIENT || $_SESSION["user"]["user_type"] == DISABLED_CLIENT ))
	{
		echo $_SESSION["user_type"];
		header('Location: user_dashboard.php');
	}
		
	else
	{
		// check for search criteria cookie.
		// if there is a cookie, load the preselections.
		if((isset($_SESSION["saved_locations"])))
		{
			if(isset($_COOKIE['relationship_sought']))
			{
				$relationship_sought = $_COOKIE['relationship_sought'];
			}
			
			
			// pull gender/gender sought from the logged in user information saved to a cookie.
			if ($_SERVER["REQUEST_METHOD"] == "GET")
			{

			}

			else if($_SERVER["REQUEST_METHOD"] == "POST")
			{
				//$sql = "SELECT profiles.user_id FROM profiles, users WHERE 1 = 1 ";
				$sql = "SELECT profiles.user_id FROM profiles,users WHERE 1=1 AND ";
				$relationship_sought = (empty($_POST["relationship_sought"]) )?"":$_POST["relationship_sought"];
				if($relationship_sought != "")
				{
					//setcookie("relationship_sought",$relationship_sought, time() + COOKIE_EXPIRE_DATE);
					$sql .= buildOrCondition("relationship_sought", $_POST["relationship_sought"]);
					$sql .=" AND ";
				}
				
				$body_type = (empty($_POST["body_type"]) )?"":$_POST["body_type"];
				if($body_type != "")
				{
					//setcookie("body_type",$body_type, time() + COOKIE_EXPIRE_DATE);
					$sql .= buildOrCondition("body_type", $body_type);
					$sql .=" AND ";
				}
				
				$horoscope = (empty($_POST["horoscope"]) )?"":$_POST["horoscope"];
				if($horoscope != "")
				{
					//setcookie("horoscope",$horoscope, time() + COOKIE_EXPIRE_DATE);
					$sql .= buildOrCondition("horoscope", $horoscope);
					$sql .=" AND ";
				}
				
				$ethnicity = (empty($_POST["ethnicity"]) )?"":$_POST["ethnicity"];
				if($ethnicity != "")
				{
					//setcookie("ethnicity",$ethnicity, time() + COOKIE_EXPIRE_DATE);
					$sql .= buildOrCondition("ethnicity", $ethnicity);
					$sql .=" AND ";
				}
				
				$religion = (empty($_POST["religion"]) )?"":$_POST["religion"];
				if($religion != "")
				{
					//setcookie("religion",$religion, time() + COOKIE_EXPIRE_DATE);
					$sql .= buildOrCondition("religion", $religion);
					$sql .=" AND ";
				}
				
				$children = (empty($_POST["children"]) )?"":$_POST["children"];
				if($children != "")
				{
					//setcookie("children",$children, time() + COOKIE_EXPIRE_DATE);
					$sql .= buildOrCondition("children", $children);
					$sql .=" AND ";
				}
				
				$smoking = (empty($_POST["smoking"]) )?"":$_POST["smoking"];
				if($smoking != "")
				{
					//setcookie("smoking",$smoking, time() + COOKIE_EXPIRE_DATE);
					$sql .= buildOrCondition("smoking", $smoking);
					$sql .=" AND ";
				}
				
				$education = (empty($_POST["education"]) )?"":$_POST["education"];
				if($education != "")
				{
					//setcookie("education",$education, time() + COOKIE_EXPIRE_DATE);
					$sql .= buildOrCondition("education", $education);
					$sql .=" AND ";
				}

				//$city = sumCheckBox($_SESSION["saved_locations"]);
                  $city = explode(",",$_COOKIE["city_value_list"]);  
				    $sql .= buildOrCondition("city", $city);
				
				$sql .= " AND profiles.user_id = users.user_id AND users.user_type <>'d' LIMIT " . RESULT_LIMIT;
				//echo $sql;
				
				if($error == "")
				{
					// save selections to the cookie and make the selections sticky
					session_set_cookie_params(0);
					//$s="SELECT profiles.user_id FROM profiles, users WHERE  relationship_sought =2 AND body_type =4 AND horoscope =1 AND ethnicity = 16 AND religion = 4 AND children = 2 AND smoking = 2 AND education = 2 AND city=2  AND users.user_id = profiles.user_id AND users.user_type <> 'd' ORDER BY users.last_access DESC LIMIT 200";
					
					 //echo $s;
					 $result = pg_query($conn, $sql);
					//echo "Results count ".pg_num_rows($result);
					
					
					$array_of_found_users = pg_fetch_result($result,"user_id");
					
					// save sql results to the session. 
					$_SESSION['search_results']=pg_fetch_all($result);
					
							
					// if no matches, output messages.
					if(pg_num_rows($result) <= 0)
					{ 
						$error .= "No matching users found. Try changing the search criteria. <br/>";
					}
					
					// if only one result, send user to the result profile.
					elseif(pg_num_rows($result) == 1)
					{ 
						// redirect user to the matching record profile page.
						header('Location: display_profile.php?id='.pg_fetch_result($result,"user_id").'');
					}
					
					// else, send user to the result page with ten profile previews per page.
					elseif(pg_num_rows($result) >= 2)
					{ 
						// redirect to result page.
						header('Location: search_results.php');
					}
				}
				
				else 
				{
					$error .= "<br /> Please try again. ";
				}	
			}
		}
			
		// else, send user to the location select page.
		else
		{
			header('Location: location_selection.php');
		}
	}
}


?>

<!-- Search Filter Table -->
<table cellspacing="15px" cellpadding="12px" style="text-align:left; margin: 0px auto 0px auto" width="100%">
	<tr>
		<td>
			<h3>
				<br/>
				Search Criteria 
			</h3>
			
			<p align="center">
				Enter the criteria you are looking for in possible matches.
				<br/><br/><br/><br/>
				<?php echo $error; ?>
			</p>
		</td>
	</tr>
	<tr>
		<p align="center">
			Locations are set to <?php  if(!isset($_COOKIE["city_name_list"])) {
              echo "Cookie named city_name_list  is not set!";
           
		   }else{
		   
				echo $_COOKIE["city_name_list"];
                 }
			
			
			
			?> <br/>
			<a href="location_selection.php">Click here to reselect locations</a>
		</p>
		
	</tr>
	<tr>
		<td class="para">
			<div id="table">
				<form action="<?php echo $_SERVER['PHP_SELF'];  ?>" method="post" >
					<table style="margin-left:auto; margin-right:auto;" >
						
						<!--<tr><td><strong>Age Range: </strong></td><td>
							
							<input type="text" name="txtMinAge" value="<?php echo $txtMinAge ?>" />
							
							<strong>To</strong>
							
							<input type="text" name="txtMaxAge" value="<?php echo $txtMaxAge ?>" />
							
						</td></tr>-->
						
						<tr><td><br/><br/><strong>Relationship Sought: </strong></td><br/><br/><td>
							<?php buildCheckBox("relationship_sought", $relationship_sought); ?>
						</td></tr>
					
						<tr><td><br/><br/><strong>Body Type</strong></td><td><br/><br/>
							<?php buildCheckBox("body_type", $body_type); ?>
						</td></tr>
					
						<tr><td><br/><br/><strong>Horoscope: </strong></td><td><br/><br/>
							<?php buildCheckBox("horoscope", $horoscope); ?>
						</td></tr>
						
						<tr><td><br/><br/><strong>Ethnicity: </strong></td><td><br/><br/>
							<?php buildCheckBox("ethnicity", $ethnicity); ?>
						</td></tr>
						
						<tr><td><br/><br/><strong>Religion:</strong></td><td><br/><br/>
							<?php buildCheckBox("religion", $religion); ?>
						</td></tr>
						
						<tr><td><br/><br/><strong>Children?</strong></td><td><br/><br/>	
							<?php buildCheckBox("children", $children); ?>
						</td></tr>
						
						<tr><td><br/><br/><strong>Smoking?</strong></td><td><br/><br/>	
							<?php buildCheckBox("smoking", $smoking); ?>
						</td></tr><br/><br/>
						
						<tr><td><br/><br/><strong>Education:</strong></td><td><br/><br/>
							<?php buildCheckBox("education", $education); ?>
						</td></tr>	
						
						
						<tr><td colspan=3 align="center"><br/><br/><input type="submit" name="btnSearch" value="Search"size="20"></input>
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