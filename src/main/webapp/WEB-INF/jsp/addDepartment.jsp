<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="" language="java" isELIgnored = "false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="keywords" content="Company, developer, manager, cleaner, department">
<meta name="description" content="Web project: Company management">

<title>Employees management</title>

<html>

<head>
    <link rel="stylesheet" type="text/css" href="style.css" />
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body style="background: url(img/background.jpg);margin-top:100px">

<div id="container" style="background : #FAEBD7;">
    <center style="font-weight:900;color:green;padding-top:20px;font-family:cuprum;font-size:20px;">
        Add department
    </center>

    <div style="margin-bottom:50px;padding-left:55px;padding-top:20px;">
    <img src="img/department.png" style="float:right;">

        <form action="addDepartmentResult.html" method="POST">
            <input style="width:200px;height:30px;border:1px solid black;" type="text" name="departmentName" value="" placeholder= "Name of the department" required />
            <br><br>
            <input style="width:200px;height:30px;border:1px solid black;" type="text" name="amountOfEmployees" value="" placeholder= "Amount of employees" required />
            <br><br>
            <button type="submit"> Submit </button>
        </form>
    </div>

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