<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<meta http-equiv=Content-Type content='text/html; charset=utf-8'>
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
    <img src="img/salary.png">
      <table class="salaryTable" cellspacing="0" style="margin-top:100px;margin-bottom:40px; background-color:#D8CEC1;border-left : 1px solid #51626f;border-top : 1px solid #51626f;">
                <tr style="font-weight:900;">
                    <th> Salary expense </th>
                    <th> Average salary </th>
                    <th> Max salary </th>
                    <th> Min salary </th>
                </tr>

                <tr>
                    <td> ${expense} </td>
                    <td> ${averageSalary} </td>
                    <td> ${maxSalary} </td>
                    <td> ${minSalary} </td>
                </tr>
      </table>
<center>

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