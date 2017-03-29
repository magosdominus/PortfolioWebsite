<?php
/*
Author: Matthew Cormier
Date: 10/09/2015
Description: This page holds constants used for data validation and determining user types.
*/
?>

<?php

define("ADMIN", "a");
define("CLIENT", "c");
define("INCOMPLETE_CLIENT", "i");
define("DISABLED_CLIENT", "d");

define("RESULT_PER_PAGE" , "10");
define("RESULT_LIMIT", 200);

define("MIN_AGE", "18");
define("MAX_AGE", "120");
define("COOKIE_EXPIRE_DATE", "25920000");

define("MINIMUM_ID_LENGTH","6");
define("MAXIMUM_ID_LENGTH","20");
define("MINIMUM_PASSWORD_LENGTH","6");
define("MAXIMUM_PASSWORD_LENGTH","8");
define("MAX_FIRST_NAME_LENGTH","20");
define("MAX_LAST_NAME_LENGTH","128");
define("MAXIMUM_EMAIL_LENGTH","256");

define("MAXIMUM_PROFILE_LIMIT","200");

define("MAXIMUM_IMAGE_LIMIT","999999kB");

define("MAXIMUM_NUMBER_IMAGES","100");
define("MINIMUM_NUMBER_IMAGES","0");

define("UPLOAD_DIR", "/temp/");
define("IMAGE_TYPE", "image/jpeg");

define("OPEN", "o");
define("CLOSED", "c");

?>

