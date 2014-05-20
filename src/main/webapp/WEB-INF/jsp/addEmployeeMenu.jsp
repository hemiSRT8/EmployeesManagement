<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="" language="java" isELIgnored = "false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="keywords" content="Company, developer, manager, cleaner, department">
<meta name="description" content="Web project: Company management">

<title>Employees management</title>

<head>
    <link rel="stylesheet" type="text/css" href="style.css" />
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body style="background: url(img/background.jpg);">

<div id="container" style="background : #FAEBD7;">

    <center>
        <div id="employeesImg"> <img src="img/employees.png"> </div>
        <div id="signboard">Manage employees</div>
    </center>

    <ul class="professions">
        <li>
            <center>

                <form action="addEmployee.html" method="POST">
                    <button type="submit" name="type" value = "Manager">ADD MANAGER</button>
                </form>

            </center>
        </li>

        <li>
             <center>

                 <form action="addEmployee.html" method="POST">
                     <button type="submit" name="type" value = "Developer">ADD DEVELOPER</button>
                 </form>
             </center>
        </li>

        <li>
             <center>

                 <form action="addEmployee.html" method="POST">
                     <button type="submit" name="type" value = "Cleaner">ADD CLEANER</button>
                 </form>
             </center>
        </li>
    </ul>

	<!-- Container end -->
</div>

<center style="text-align:center;margin-top:20px;">
        <a href="index.html"> <img src="img/homePage.png"> </a>
</center>

<div id="copyright">
    by A69V &copy; 2014
<br>
    boss : Tolya
</div>

</body>
</html>