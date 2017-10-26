<?php
include 'connection/databaseConnect.php';

$student_id = filter_input(INPUT_POST, "student_id");

if($student_id == NULL)
{
    $error = "Error , please fill in the appropirate field";
    include_once 'displayStudents.php';
    exit();
}

if(!is_numeric($student_id))
{
    $error = "Error , please type in an number for student id field";
    include_once 'displayStudents.php';
    exit();
}

if(preg_match('/[\'^£$%&*()}{@#~?><>,|=_+¬-]/' , $student_id))
{
    $error = "Error , invalid user input";
    include_once 'displayStudents.php';
    exit();
}

    $query = "delete from students where studentID = :student_id";
    $statement = $db->prepare($query);
    $statement->bindValue(":student_id", $student_id);
    $statement->execute();
    $statement->closeCursor();
    require_once 'displayStudents.php';
    exit();