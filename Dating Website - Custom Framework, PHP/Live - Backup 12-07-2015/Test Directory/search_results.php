<?php

/* 
Name:Maitri Gemlawala, Matthew Cormier, Gurpreet Maan
WEDE3201
Date: 11/4/2015

Description: This page will load results from the search page. 10 results per page up to 200 results. Results will be previews of user profiles.
*/

$title="WEDE3201 - Search Results";
$fileName="search_results.php";
$date="11/04/2015";
$Description= "This page allows the user to view search results.";
$banner="Search Results";
include ("header.php");
?> 

<p>
	<br/><br/>
	<h2 align="center">
		Results
	</h2>
</p>

<?php 
if(!isset($_SESSION['search_results']))
{
	header('Location: search.php');
}
else
{
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