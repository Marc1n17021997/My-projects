<?php
include 'connection/databaseConnect.php';

$username = filter_input(INPUT_POST, "teacher_user");

if($username == NULL)
{
    $error = "Error , username field has to be filled in";
    require_once 'displayStudents.php';
    exit();
}

if(is_numeric($username))
{
    $error = "Error , username can't be all numbers";
    require_once 'displayStudents.php';
    exit();
}

if(preg_match('/[\'^£$%&*()}{@#~?><>,|=_+¬-]/', $username))
{ 
    $error = "Error , invalid user input";
    require_once 'displayStudents.php';
    exit();
}

    $query = "Delete from teacher_login where username = :username";
    $statement = $db->prepare($query);
    $statement->bindValue(":username" , $username);
    $statement->execute();
    $statement->closeCursor();
    include_once 'displayStudents.php';
    exit();
