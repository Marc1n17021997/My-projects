<?php

require 'connection/databaseConnect.php';

$update_course_name = filter_input(INPUT_POST, "update_course_name");
$course_id = filter_input(INPUT_POST, "update_course");

if($update_course_name == NULL || $course_id == NULL)
{
    $error = "Error , all fields have to be filled in";
    require_once 'displayStudents.php';
    exit();
}

if(is_numeric($update_course_name))
{
    $error = "Error , course name can't be all numbers";
    require_once 'displayStudents.php';
    exit();
}

if(is_numeric($course_id))
{
    $error = "Error , course id can't be all numbers";
    require_once 'displayStudents.php';
    exit();
}

if(!preg_match('~[0-9]~', $update_course_name))
{
    $error = "Error , a field should contain a combination of letters and numbers";
    require_once 'displayStudents.php';
    exit();
}

if(!preg_match('~[0-9]~', $course_id))
{
    $error = "Error , a field should contain a combination of letters and numbers";
    require_once 'displayStudents.php';
    exit();
}

if(preg_match('/[\'^£$%&*()}{@#~?><>,|=_+¬-]/', $update_course_name))
{
    $error = "Error , invalid user input";
    require_once 'displayStudents.php';
    exit();
}

if(preg_match('/[\'^£$%&*()}{@#~?><>,|=_+¬-]/', $course_id))
{
    $error = "Error , invalid user input";
    require_once 'displayStudents.php';
    exit();
}

$query = "Update courses set courseName = :update_course_name where courseId = :update_course";
$statement = $db->prepare($query);
$statement->bindValue(":update_course_name" , $update_course_name);
$statement->bindValue(":update_course" , $course_id);
$statement->execute();
$statement->closeCursor();
include_once 'displayStudents.php';
exit();