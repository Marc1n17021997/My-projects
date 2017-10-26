<?php 
require_once 'connection/databaseConnect.php';
$query = "Select * from students Order by courseID";
$statement = $db->prepare($query);
$statement->execute();
//date("Y" , strtotime())
//$get_age = date("Y" , getYear($student_age));
//$student_age = date("Y") - $get_age;  
$students = $statement->fetchAll();
$statement->closeCursor();

$query1 = "Select * from courses Order by courseID";
$statement1 = $db->prepare($query1);
$statement1->execute();
$student_course = $statement1->fetchAll();
$statement1->closeCursor();
?>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Display students</title>
        <link href="Css/main.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <header>
            <img src="Images/img1.jpg" id="header">
        </header>
        <main>
            <h1>Students and Teachers Options</h1>
            <table id="display">
                <textarea><?php if(isset($error)){echo $error;} ?></textarea>
                <tr>
                    <th>Student name</th>
                    <th>Student Age</th>
                    <th>Student ID</th>
                    <th>Student Mark</th>
                    <th>Student course</th>
                </tr>
                <?php foreach ($students as $student): ?>
                    <tr>
                        <td><?php echo $student["studentName"] ?></td>
                        <td><?php echo $student["studentDOB"] ?></td>
                        <td><?php echo $student["studentID"] ?></td>
                        <td><?php echo $student["studentMark"] ?></td>
                        <td><?php echo $student["courseId"] ?></td>
                    </tr>
                <?php endforeach; ?>
                <br>
                <br>
                <h2>Add a student</h2>
                <form action="add_student.php" method="post">
                    <label>Please type in students name</label>
                    <br>
                    <input type="text" name="student_name"/>
                    <br>
                    <label>Please type in student's date of birth</label>
                    <br>
                    <input type="date" name="student_age">
                    <br>
                    <label>Please type in student's mark for the year</label>
                    <br>
                    <input type="number" name="student_mark" step="any"/>
                    <br>
                    <select name="course_id">
                        <?php foreach ($student_course as $course): ?>
                            <option value="<?php echo $course["courseId"]; ?>">
                                <?php echo $course["courseName"]; ?>
                            </option>
                        <?php endforeach; ?>
                    </select>
                    <br>
                    <input type="submit" value="Add student">
                    <br>
                </form>

                <h2>Add a course</h2>
                <form action="add_course.php" method="post">
                    <label>Please type in the name of the course you want to add</label>
                    <br>
                    <input type="text" name="course_name">
                    <br>
                    <label>Please type in the course ID</label>
                    <br>
                    <input type="text" name="course_id">
                    <br>
                    <input type="submit" value="Add course">
                    <br>
                </form>

                <h2>Remove students</h2>
                <form action="delete_student.php" method="post">
                    <label>Please type in the id of the student you want to delete</label>
                    <br>
                    <input type="number" name="student_id">
                    <br>
                    <input type="submit" value="Remove student">
                    <br>
                </form>

                <h2>Remove a course</h2>
                <form action="delete_course.php" method="post">
                    <label>Please type in the course of the id you want to remove</label>
                    <br>
                    <input type="text" name="course_id">
                    <br>
                    <input type="submit" value="Remove course">
                    <br>
                </form>

                <h2>Remove a teacher</h2>
                <form action="delete_teacher.php" method="post">
                    <label>Please type in the username of the teacher you want to delete</label>
                    <br>
                    <input type="text" name="teacher_user">
                    <br>
                    <input type="submit" value="Remove teacher">
                    <br>
                </form>

                <h2>Update student details</h2>
                <form action="update_student.php" method="post">
                    <label>Please type in new student name</label>
                    <br>
                    <input type="text" name="update_name">
                    <br>
                    <label>Update student's date of birth</label>
                    <br>
                    <input type="date" name="update_birth">
                    <br>
                    <label>Please type in student's id</label>
                    <br>
                    <input type="number" name="student_id">
                    <br>
                    <label>Please type in updated student's mark</label>
                    <br>
                    <input type="number" name="update_mark" step="any">
                    <br>
                    <select name="update_course">
                        <br>
                        <?php foreach ($student_course as $course): ?>
                            <option value="<?php echo $course["courseId"]; ?>">
                                <?php echo $course["courseName"]; ?>
                            </option>
                        <?php endforeach; ?>
                        <br>
                    </select>
                    <input type="submit" value="Update student">
                    <br>

                    <h2>Update course</h2>
                </form>
                <form action="update_course.php" method="post">
                    <label>Please type in course name</label>
                    <br>
                    <input type="text" name="update_course_name">
                    <label>Please select course id</label>
                    <br>
                    <select name="update_course">
                        <br>
                        <?php foreach ($student_course as $course): ?>
                            <option value="<?php echo $course["courseId"]; ?>">
                                <?php echo $course["courseId"]; ?>
                            </option>
                        <?php endforeach; ?>
                    </select>
                    <br>
                    <input type="submit" value="Update course">
                    <br>
                </form>
            </table>
            <footer>Copyright &copy; <?php echo date("Y"); ?>  My school of technology</footer>
        </main>
    </body>
</html>