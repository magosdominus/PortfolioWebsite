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
	$page = (isset($_GET['page'])) ? (int)$_GET['page'] : 1;
	
	
	$results = ($_SESSION['search_results']);
	
	
	$startAt = $per_page * ($page - 1);
	$result_count = count($results);
	$total_pages = ceil($result_count / $per_page);
	$links = "";
	
	for ($page_counter = 1; $page_counter <= $total_pages; $page_counter++) 
	{
	  $links .= ($page_counter != $page ) 
				? "<a href='index.php?page=$page_counter'>Page $page_counter</a> "
				: "$page ";
				
			
	}
	
	echo $links;
	echo $page;
	
	for ($counter = 1; $counter < $page; $counter++)
	{
		$user_id = pg_fetch_result($results, "user_id", $counter);
		createProfilePreview($user_id);	
	} 
	
}

?>

<?php
include ("footer.php");
?>