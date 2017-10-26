<?php
//Login function not working
include_once 'session.php';
include_once 'connection/databaseConnect.php';

$username = filter_input(INPUT_POST, "username");
$password = filter_input(INPUT_POST, "password");
$match = false;

if ($password == NULL || $username == NULL)
{
    $error = "Error , all fields have to be filled";
    include_once 'index.php';
    exit();
}
if (preg_match('/[\'^£$%&*()}{@#~?><>,|=_+¬-]/', $password) || preg_match('/[\'^£$%&*()}{@#~?><>,|=_+¬-]/', $username))
{
    $error = "Error , invalid user input";
    include_once 'index.php';
    exit();
}

$query = "SELECT * FROM teacher_login WHERE username = :username";
$query1 = "Select password From teacher_login";
$statement = $db->prepare($query);
$statement1 = $db->prepare($query);
$statement->bindValue(":username", $username);
$statement1->bindValue(":password", $password);
$statement->execute();
$statement1->execute();
$x = 0;
$y = 0;
while ($row = $statement->fetch())
{
    //get username
    $username = $row[$x];
    $x++;
}
$statement->closeCursor();
while ($row2 = $statement1->fetchAll())
{
    $hashed_password = $row2[$y];

    if (password_verify($password, $hashed_password))
    {
        $match = true;
        include_once 'displayStudents.php';
        exit();
    }
    else
    {
        $result = "<p> Invalid username or password</p>";
    }
}
$statement1->closeCursor();