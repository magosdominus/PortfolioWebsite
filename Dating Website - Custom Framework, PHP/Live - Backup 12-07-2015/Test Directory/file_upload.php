<?php
/* 
Name:Maitri Gemlawala, Matthew Cormier, Gurpreet Maan
WEDE3201
Date: 30th September,2015
*/
$title="File Upload";
$fileName="file_upload.php";
$date="11/25/2015";
$description= "This page will allow a user to upload and delete thier profile images.";
$banner="File Upload";
include ("header.php");
require_once ("./includes/functions.php");
?>
<?php
$images_form = "";
if(!(isset($_SESSION["greeting"])) || $_SESSION{"user"}["user_type"] == INCOMPLETE_CLIENT || $_SESSION["user"]["user_type"] == DISABLED_CLIENT)
{
	header('Location: member_login.php');
}
else
{
	if ($_SERVER["REQUEST_METHOD"] == "GET")
	{
		$error = "";
		$upload_image = "";
		
		$user_id = $_SESSION["Logon_user_id"];
		$result = pg_execute($conn, "check_images", array($user_id));
		$number_of_images = pg_fetch_result($result, "user_picture");
		
		if ($number_of_images > MINIMUM_NUMBER_IMAGES)
		{
			// build images function
			$images_form = buildImages($user_id, $number_of_images);
		}
		
	}

	else if($_SERVER["REQUEST_METHOD"] == "POST")	
	{
		$error = "";
		$user_id = $_SESSION["Logon_user_id"];
		$result = pg_execute($conn, "check_images", array($user_id));
		$number_of_images = pg_fetch_result($result, "user_picture");
		if ($number_of_images > MINIMUM_NUMBER_IMAGES)
			{			
				// build images function
				$images_form = buildImages($user_id, $number_of_images);
			}
		$upload_image = $_FILES["upload_image"];
		$dir = "./user_images/".$user_id;
		if (isset($_POST['btnDelete']))
		{
			
				//print_r($_POST);
				if(isset($_POST["images"]))
				{
					// Count number of checked check boxes.
					$image_array = $_POST["images"];
					
					// For each checked, delete the matching record.
					for ($delete_counter = sizeof($image_array) - 1; $delete_counter >= 0; $delete_counter--)
					{
						// Delete image file.
						unlink($dir."/".$user_id."_".$image_array[$delete_counter] . ".jpg");
						
						// Decrease number of images counter by one.
						$number_of_images--;
						pg_execute($conn, "update_image_count", array($number_of_images, $user_id));
						// Rename files to previous deleted file name.
						for ($j = $image_array[$delete_counter]; $j <= $number_of_images; $j++)
						{
							rename($dir."/".$user_id."_".($j+1).".jpg",
									$dir."/".$user_id."_".($j).".jpg");
							
							// Update the number of images in the profile table.
							
						}
					}
					
					// If empty, delete the user image folder.
					if ($number_of_images == MINIMUM_NUMBER_IMAGES)
					{
						rmdir($dir);
					}
				}
				else
				{
					$error .= "No selections made. <br />";
				}

		}
		
		elseif (isset($_POST['btnUpload']))
		{
			// Image properties
			$temp_location = $_FILES["upload_image"]["tmp_name"];
			$image_error = $_FILES["upload_image"]["error"];
			$image_name = $_FILES["upload_image"]["name"];
			$image_type = $_FILES["upload_image"]["type"];
			$image_size = $_FILES["upload_image"]["size"];

			if(isset($_FILES["upload_image"]))
			{
				if ($number_of_images >= MAXIMUM_NUMBER_IMAGES)
				{
					// at image limit
					// error
					$error .= "At image capacity of ". MAXIMUM_NUMBER_IMAGES .". Please delete old images to upload new images.";
				}
				else
				{
					// check for user folder
					
					// User's directory name
					$dir = "./user_images/" . $user_id;
					
					if (is_dir($dir) == false)
					{
						mkdir($dir);
						chmod($dir, 0777);
							
					}
		
					// check file type and size 
					if ($image_type != IMAGE_TYPE)
					{
						$error .= "Wrong file type. The image must be a JPEG.";
					}
					
					if ($image_size > MAXIMUM_IMAGE_LIMIT)
					{
						$error .= "Image is too large. Image must be under 100000kB.";
					}
					
					// upload image(s) to user folder
					if ($error == "")
					{
						if ($image_error > 0)
						{
							$error .= "Error uploading file! Code " . $error;
						}
						else
						{
							move_uploaded_file($temp_location,$dir."/".$image_name);
							chmod($dir."/".$image_name, 0777);
							
							// Check for an unused file name and rename the image.
							$image_counter = 1;
							$filename_test = $user_id."_".$image_counter.".jpg";
							
							while (file_exists($dir."/".$filename_test))
							{
								$image_counter++;
								$filename_test = $user_id."_".$image_counter.".jpg";
							}
							
							rename($dir."/".$image_name ,$dir."/".$filename_test);
							
							// Update the number of images in the profile table.
							pg_execute($conn, "update_image_count", array($number_of_images+1, $user_id));
							
							// Change file permissions to read for user and read, write, execute for admin.
							
						}
					}
				
					
					// update number of images in profiles				
					
					if ($number_of_images > MINIMUM_NUMBER_IMAGES)
					{
						// build images function
						buildImages($user_id, $number_of_images);
					}
				}
			}
			else
			{
				$error .= "A file must be selected";
			}
		}
	}
}

?>
<form action="<?php echo $_SERVER['PHP_SELF'];  ?>" method="post" enctype="multipart/form-data">
	
<table cellspacing="15px" cellpadding="12px" style="text-align:left; margin: 0px auto 0px auto" width="100%">
	<tr>
		<td>
			<h3>
			<br/>
			Upload New Image
			</h3>
		</td>
	</tr>
	<tr>
		<td><?php echo $error; ?>
		<?php echo $images_form; ?>
		</td>
	</tr>
	<tr>
		<td class="para">
			<div id="table">
					<table style="margin-left:auto; margin-right:auto;" >
						<tr><td><strong>Select Image File (.jpg):</strong></td><td><input type="file" name="upload_image" />

						<tr><td colspan="3" align="center"><br/><br/><input type="submit" name="btnUpload" value="Upload image" size="20"></input>
																   <input type="submit" name="btnDelete" value="Delete Selected" size="20" ></input></td>
						</tr>
					</table>
							
			</div>
		</td>
	</tr>
</table>
</form>
<?php include ("footer.php"); ?>