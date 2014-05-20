<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="" language="java" isELIgnored = "false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<meta http-equiv=Content-Type content='text/html; charset=utf-8'>
<meta name="keywords" content="Company, developer, manager, cleaner, department">
<meta name="description" content="Web project: Company management">

<title>Add Employees service</title>

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
    <p>
        ${addResult > 0
                ?
                "<div class = \"successOperationContainer\" style=\"display:block;\"> Employee was added successfully </div>"
                :
                "<div class = \"failOperationContainer\" style=\"display:block;\"> Error was occurred. Try again. </div>"}
    </p>

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