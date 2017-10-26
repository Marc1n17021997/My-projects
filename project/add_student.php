<?php

require_once 'connection/databaseConnect.php';
$student_name = filter_input(INPUT_POST, "student_name");
$student_dob = filter_input(INPUT_POST, "student_age");
$student_mark = filter_input(INPUT_POST, "student_mark");
$student_course = filter_input(INPUT_POST, "course_id");

if ($student_name == NULL || $student_dob == NULL || $student_mark == NULL || $student_course == NULL)
{
    $error = "Error , all fields have to be filled";
    require_once 'displayStudents.php';
    exit();
}
if (preg_match('~[0-9]~', $student_name))
{
    $error = "Error , numbers   not allowed for student's name field"; 
    require_once 'displayStudents.php';
    exit();
}

if (!preg_match('~[0-9]~' , $student_dob))
{
    $error = "Error , only numbers allowed for birthday field";
    require_once 'displayStudents.php';
    exit();
}

if(is_numeric($student_course))
{
    $error = "Error , combinations of numbers and letters required for student course field";
    require_once 'displayStudents.php';
    exit();
}

if (!preg_match('~[0-9]~', $student_course))
{
    $error = "Error , numbers and letters required for student's course field";
    require_once 'displayStudents.php';
    exit();
}

if (preg_match('/[\'^£$%&*()}{@#~?><>,|=_+¬-]/', $student_name))
{
    $error = "Invalid user input";
    require_once "displayStudents.php";
    exit();
}

if(preg_match('/[\'^£$%&*()}{@#~?><>,|=_+¬-]/', $student_dob))
{
    $error = "Invalid user input";
    require_once 'displayStudents.php';
    exit();
}

if(preg_match('/[\'^£$%&*()}{@#~?><>,|=_+¬-]/', $student_mark))
{
    $error = "Invalid user input";
    require_once 'displayStudents.php';
    exit();
}

if(preg_match('/[\'^£$%&*()}{@#~?><>,|=_+¬-]/', $student_course))
{
    $error = "Invalid user input";
    require_once 'displayStudents.php';
    exit();
}

    $query = "Insert into students (studentName , studentDOB , studentMark , courseId) values (:student_name , :student_dob , :student_mark , :course_id)";
    $statement = $db->prepare($query);
    $statement->bindValue(":student_name", $student_name);
    $statement->bindValue(":student_dob", $student_dob);
    $statement->bindValue(":student_mark", $student_mark);
    $statement->bindValue(":course_id", $student_course);
    $statement->execute();
    $statement->closeCursor();
    require_once 'displayStudents.php';
    exit();