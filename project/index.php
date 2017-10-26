<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Index page</title>
        <link href="Css/main.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <header>
            <img src="Images/img1.jpg" id="header">
        </header>
        <main>
            <form action="register.php" method="post">
                <textarea><?php if(isset($error)){echo $error;} ?></textarea>
                <h2>Create account(teacher)</h2>
                <label>Please type in your email address here</label>
                <br>
                <input type="email" name="email">
                <br>
                <label>Please type in the password you want to set for your account</label>
                <br>
                <input type="password" name="password">
                <br>
                <label>Please type in your username here</label>
                <br>
                <input type="text" name="username">
                <br>
                <input type="submit" value="Create account">
                <br>
            </form>

            <h2>Login</h2>
            <form action="login.php" method="post">
                <label>Type in your username here</label>
                <br>
                <input type="text" name="username">
                <br>
                <label>Type in your password here</label>
                <br>
                <input type="password" name="password">
                <br>
                <input type="submit" value="login">
                <br>
            </form>
            <footer>Copyright &copy; <?php echo date("Y"); ?>  My school of technology</footer>
        </form>
</body>
</html>
