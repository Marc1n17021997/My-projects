<?php
include 'connection/databaseConnect.php';

$course_id = filter_input(INPUT_POST, "course_id");

if($course_id == NULL)
{
    $error = "Error , please fill in all the appropirate fields";
    include_once 'displayStudents.php';
    exit();
}

if(preg_match('/[\'^£$%&*()}{@#~?><>,|=_+¬-]/', $course_id))
{
    $error = "Error , invalid user input";
    include_once "displayStudents.php";
    exit();
}

if(is_numeric($course_id))
{
    $error = "Error , combination of letters and numbers required for course id field"; 
    include_once "displayStudents.php";
    exit();
}

if(!preg_match('~[0-9]~', $course_id))
{
    $error = "Error , combinations of letters and numbers required for course id";
    include_once 'displayStudents.php';
    exit();
}
    
$query = "Delete from courses where courseId = :course_id";
$statement = $db->prepare($query);
$statement->bindValue(":course_id" , $course_id);
$statement->execute();
$statement->closeCursor();
require_once 'displayStudents.php';
exit();