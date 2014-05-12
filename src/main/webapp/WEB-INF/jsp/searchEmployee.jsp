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

<body style="background: url(img/background.jpg);margin-top:100px">

<div id="container" style="background : #FAEBD7;">

    <center style="margin-bottom:50px;">
    <img src="img/search.png">
        <form action="searchEmployeeResult.html" method="POST">
            <input type="search" name="lastName" value="" placeholder= "Search by last name">
            <br><br>
            <button type="submit">I'm lucky</button>
        </form>
    </center>

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