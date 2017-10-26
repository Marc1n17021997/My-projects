<?php

$database = "mysql:host=127.0.0.1;dbname=marcin_duszynski_ca2";
$username = "root";
$passsword = "";

try
{
   $db = new PDO($database , $username , $passsword);
   $db->setAttribute(PDO::ATTR_EMULATE_PREPARES , FALSE);
   $db->setAttribute(PDO::ATTR_ERRMODE , PDO::ERRMODE_EXCEPTION);
   error_reporting(E_ALL);
} 

catch (Exception $ex) 
{
      echo "There was an error connecting to your database";
      exit();
}
