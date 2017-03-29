<?php

/* 
Name:Maitri Gemlawala, Matthew Cormier, Gurpreet Maan
WEDE3201
Date: 11/4/2015

Description: This page will generate a list of locations as checkboxes. The user selects the locations they want to 
			 search for and then the page redirects the user back to the search page.
*/

$title="WEDE3201 - Location Selection";
$fileName="location_selection.php";
$date="11/04/2015";
$Description= "This page allows the user to select location criteria for profile searches.";
$banner="Location Select";
include ("header.php");
?> 

<script type="text/javascript">

	/*NOTE: for the following function to work, on your page
			you have to create a checkbox id'ed as city_toggle
				
	<input type="checkbox"  id="city_toggle" onclick="cityToggleAll();" name="city[]" value="0">
			
		and each city checkbox element has to be an named as an 
		array (specifically named "city[]")
		e.g.
			<input type="checkbox" name="city[]" value="1">Ajax
	*/
	function cityToggleAll()
	{
		//alert("In cityToggleAll()");  //alerts used for de-bugging
		var isChecked = document.getElementById("city_toggle").checked;
		var city_checkboxes = document.getElementsByName("city[]");
		for (var i in city_checkboxes){
		//SAME AS for ( i = 0; i < city_checkboxes.length; i++){
			city_checkboxes[i].checked = isChecked;
		}		
	}
	
</script>

<?php

$error="";
$city="";
$counter=0;

$currentCityIndex = 0;
$city_names_output = "";
$city_array = "";

if ($_SERVER["REQUEST_METHOD"] == "GET")
{
	$city = "";
	
	$error = "";
}

else if($_SERVER["REQUEST_METHOD"] == "POST")
{
	// check if fields are empty 
	if( !isset($_POST["city"]) )
	{
		$error .= "Aleast one location must be selected. ";
		
	}
	
	 if($error == "")
	{
		if(isset($_POST['city']))
		{
			$city_array = $_POST['city'];
			array_pop($city_array);
		}
	
		
		for($counter = 0; $counter < count($city_array); $counter++)
		{
			$currentCityIndex = $city_array[$counter];
			
			$city_names_output .= getProperty("city", $currentCityIndex) . "   " ;
		}
		
		setcookie("city_name_list",$city_names_output, time() + COOKIE_EXPIRE_DATE);
		setcookie("saved_locations",$city_array, time() + COOKIE_EXPIRE_DATE);
		$_SESSION["saved_locations"] = $city_array;
		
		header("Location: search.php");
	}
	
	else 
	{
		$error .= "<br /> Please try again. ";
	}	
}
?>

<script type="text/javascript">

function checkboxLocation(location)
{
	if(document.getElementByClass(location).checked == true)
	{
		document.getElementByClass(location).checked = false;
	}
	else
	{
		document.getElementByClass(location).checked = true;
	}
}

</script>

<!-- Location Table -->
<table cellspacing="15px" cellpadding="12px" style="text-align:left; margin: 0px auto 0px auto" width="100%">
	<tr>
		<td>
			<h3>
				<br/>
				Location Selection
			</h3>
			
			<p align="center">
				Enter the locations for the search criteria. 
			</p>
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
						
						<tr><td><br/><br/><strong>Locations: </strong></td><br/><br/><td>
							<?php buildCityCheckBox("city", $city); ?>
						</td></tr>
						<tr><td><br/><br/><strong>Toggle All: </strong></td><br/><br/><td>
							<input type="checkbox"  id="city_toggle" onclick="cityToggleAll();" name="city[]" value="0">
						</td></tr>
						
						<tr><td colspan=3 align="center"><br/><br/><input type="Submit" name="btnEnter" value="Enter"size="20"></input>
							<input type="Reset" name="btnClear" value="Clear Page"size="20" ></input></td>
						</tr>
						
						</table>
						<tr>
						<table style="margin-left:auto; margin-right:auto;" >
							<td rowspan=3 align=left>
					
							
								<img src="./images/map.gif" usemap="#durham_map" >
								
								<map name="durham_map">
									<area shape="rect" coords="405,281,492,421" OnClick="checkboxLocation(Oshawa)" alt="Oshawa">
									<area shape="rect" coords="351,303,443,451" OnClick="" alt="Whitby">
									<area shape="rect" coords="333,402,386,468" OnClick="" alt="Ajax">
									<area shape="rect" coords="333,402,386,468" OnClick="" alt="Ajax">
									<area shape="rect" coords="245,336,334,477" OnClick="" alt="Pickering">
									<area shape="rect" coords="465,269,702,388" OnClick="" alt="Bowmanville">
									<area shape="rect" coords="186,103,541,233" OnClick="" alt="Port Perry">
								</map>
							</td>
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
