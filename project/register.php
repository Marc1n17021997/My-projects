<?PHP

include 'connection/databaseConnect.php';


//Create variables for the information submitted by the user
$email = filter_input(INPUT_POST, "email");
$username = filter_input(INPUT_POST, "username");
$password = filter_input(INPUT_POST, "password");

if ($email == NULL || $password == NULL || $username == NULL)
{
    $error = "Error , all fields have to be filled";
    include_once 'index.php';
    exit();
}
if (preg_match('/[\'^£$%&*()}{#~?><>,|=_+¬-]/', $email))
{
    $error = "Error , invalid user input";
    include_once 'index.php';
    exit();
}

if (preg_match('/[\'^£$%&*()}{@#~?><>,|=_+¬-]/', $password))
{
    $error = "Error , invalid user input";
    include_once 'index.php';
    exit();
}

if (preg_match('/[\'^£$%&*()}{@#~?><>,|=_+¬-]/', $username))
{
    $error = "Error , invalid user input";
    include_once 'index.php';
    exit();
}

if (!preg_match('/[A-Z]+[a-z]+[0-9]+/', $password))
{
    $error = "Error , a password must contain at least: one capital letter , one number , must be 8 characters long or over";
    include_once'index.php';
    exit();
}

if (strlen($password) < 8)
{
    $error = "Error , a password must contain at least: one capital letter , one number , must be 8 characters long or over";
    include_once'index.php';
    exit();
}

if (strlen($username) < 6)
{
    $error = "Error , username too short";
    include_once'index.php';
    exit();
}

if ($username . CASE_LOWER === $password . CASE_LOWER)
{
    $error = "Error , username and password can't match";
    include_once 'index.php';
    exit();
}

$hashed_password = password_hash($password, PASSWORD_DEFAULT);
$query = "Insert into teacher_login(email , username , password) values (:email , :username , :password)";
$statement = $db->prepare($query);
$statement->bindValue(":email", $email);
$statement->bindValue(":username", $username);
$statement->bindValue(":password", $hashed_password);
$statement->execute();
$statement->closeCursor();
include_once "index.php";
exit();
