<?php

/* 
Name: Matthew Cormier, Gurpreet Maan, Maitri Gemlawala
WEDE3201
Date: 30th September,2015
*/

$title="WEDE3201 - Profile Page";
$fileName="disabled_users.php";
$date="12/07/2015";
$Description= "This page will display profile previews for all disabled users.";
$banner="Disabled Users";
include ("header.php");
?> 

<?php

if(!(isset($_SESSION["greeting"])))
{
	header('Location: member_login.php');
}
elseif($_SESSION{"user"}["user_type"] == INCOMPLETE_CLIENT || $_SESSION["user"]["user_type"] == DISABLED_CLIENT || $_SESSION["user"]["user_type"] == CLIENT)
{
	header('Location: user_dashboard.php');
}
else
{
	$result = pg_execute($conn, "select_disabled_users", array(DISABLED_CLIENT));
	
	$per_page = RESULT_PER_PAGE;
	$page = (isset($_GET['page'])) ? (int)htmlspecialchars($_GET["page"]) : 0;
	
	if(isset($_GET['page'])){
	$c =(int)htmlspecialchars($_GET["page"]);
	if($c!=0){
	$start_counter = $c+ $per_page ;
	}else{
	$start_counter = 0 ;
	}
	}
	else{
	$start_counter =0;
	}
	$results = ($_SESSION['search_results']);
	
	//echo count($results);
	//$startAt = $per_page * ($page - 1);
	$result_count = count($results);
	$total_pages = ceil($result_count / $per_page);
	$links = "";
	
	for ($page_counter = 0; $page_counter < $total_pages; $page_counter++) 
	{
	  $links .=  "<a href='search_results.php?page=".$page_counter."'>Page". ($page_counter + 1) ."</a> ";
				
				
			
	}
	
	echo $links;
	
$count =$start_counter;
		for($counter=$start_counter;$counter<($count+$per_page) && $counter<(count($results));$counter++ ){
		createProfilePreview($results[$start_counter]["user_id"]);
		$start_counter++;
}		
}



?>
<?php
include ("footer.php");
?>