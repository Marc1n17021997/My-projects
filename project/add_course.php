<?php
require_once 'connection/databaseConnect.php';

$course_name = filter_input(INPUT_POST, "course_name");
$course_id = filter_input(INPUT_POST, "course_id");

if ($course_name == NULL || $course_id == NULL)
{
    $error = "Error , all fields haveto be filled in";
    include 'displayStudents.php';
    exit();
}

if(preg_match('/[\'^£$%&*()}{@#~?><>,|=_+¬-]/', $course_name))
{
    $error = "Invalid input";
    include_once 'displayStudents.php';
    exit();
}

if(preg_match('/[\'^£$%&*()}{@#~?><>,|=_+¬-]/' , $course_id))
{
    $error = "Invalid user input";
    include_once "displayStudents.php";
    exit();
}

if (is_numeric($course_name) || is_numeric($course_id))
{
    $error = "An error has occured while entering your information";
    include_once 'displayStudents.php';
    exit();
}

    $query = "Insert into courses(courseName , courseId) values (:course_name , :course_id)";
    $statement = $db->prepare($query);
    $statement->bindValue(":course_name" , $course_name);
    $statement->bindValue(":course_id" , $course_id);
    $statement->execute();
    $statement->closeCursor();
    include_once 'displayStudents.php';
    exit();
