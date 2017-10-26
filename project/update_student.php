<?php

include 'connection/databaseConnect.php';

$update_name = filter_input(INPUT_POST, "update_name");
$update_birth = filter_input(INPUT_POST, "update_birth");
$student_id = filter_input(INPUT_POST, "student_id");
$update_mark = filter_input(INPUT_POST , "update_mark");
$update_course = filter_input(INPUT_POST, "update_course");

if($update_name == NULL || $update_birth == NULL || $student_id == NULL || $update_mark == NULL || $update_course == NULL)
{
    $error = "Error , all fields have to be filled in";
    require_once 'displayStudents.php';
    exit();
}

if(!is_numeric($student_id))
{
    $error = "Student's id has to be in numbers only";
    require_once 'displayStudents.php';
    exit();
}

if(!is_numeric($update_mark))
{
    $error = "Student's mark has to be in numbers only";
    require_once 'displayStudents.php';
    exit();
}

if(!preg_match('~[0-9]~', $update_course))
{
    $error = "Error , a combination of letters and numbers required for course id";
    require_once 'displayStudents.php';
    exit();
}

if(preg_match('~[0-9]~', $update_name))
{
    $error = "Error , no numbers allowed in student's name";
    require_once 'displayStudents.php';
    exit();
}

if(preg_match('/[\'^£$%&*()}{@#~?><>,|=_+¬-]/', $update_name))
{
    $error = "Error , invalid user input";
    require_once 'displayStudents.php';
    exit();
}

if(preg_match('/[\'^£$%&*()}{@#~?><>,|=_+¬]/', $update_birth))
{
    $error = "Error , invalid user input";
    require_once 'displayStudents.php';
    exit();
}

if(preg_match('/[\'^£$%&*()}{@#~?><>,|=_+¬-]/', $student_id))
{
    $error = "Error , invalid user input";
    require_once 'displayStudents.php';
    exit();
}

if(preg_match('/[\'^£$%&*()}{@#~?><>,|=_+¬-]/', $update_mark))
{
    $error = "Error , invalid user input";
    require_once 'displayStudents.php';
    exit();
}

if(preg_match('/[\'^£$%&*()}{@#~?><>,|=_+¬-]/', $update_course))
{
    $error = "Error , invalid user input";
    require_once 'displayStudents.php';
    exit();
}

$query = "Update students set studentName = :update_name , studentDOB = :update_birth , studentMark = :update_mark , courseId = :update_course  where studentID = :student_id";
$statement = $db->prepare($query);
$statement->bindValue(":update_name" , $update_name);
$statement->bindValue(":update_birth" , $update_birth);
$statement->bindValue(":update_mark" , $update_mark);
$statement->bindValue(":update_course" , $update_course);
$statement->bindValue(":student_id" , $student_id);
$statement->execute();
$statement->closeCursor();
include_once 'displayStudents.php';
exit();