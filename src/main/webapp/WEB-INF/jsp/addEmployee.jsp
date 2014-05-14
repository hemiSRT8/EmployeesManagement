<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false"%>

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
    <script src="js/dateValidator.js"></script>
</head>

<body style="background: url(img/background.jpg);">

<div id="container" style="background : #FAEBD7;">

    <center>
        <div id="employeesImg"> <img src="img/employees.png"> </div>
        <div id="signboard">Manage employees</div>
    </center>

    <form action="saveEmployeeResult.html" method="POST">
        <center>
            <table class="addEmployee">
                <thead>
                <tr>
                    <h4>Fill in the form</h4>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>First Name</td>
                    <td><input type="text" name="firstName" value="" required/></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" name="lastName" value="" required/></td>
                </tr>
                <tr>
                    <td>Date of birthday</td>
                    <td><input type="date" onblur="isDateValid()" required
                               name="dateOfBirth" value="" /></td>
                </tr>
                <tr>
                    <td>Wage</td>
                    <td><input type="number" name="wage" value="" /></td>
                </tr>
                <tr>
                    <td>Bonus</td>
                    <td><input type="number" name="bonus" value="" /></td>
                </tr>
                <tr>
                    <td>Penalty</td>
                    <td><input type="number" name="penalty" value="" /></td>
                </tr>
                <tr>
                    <td>Salary</td>
                    <td><input type="number" name="salary" value="" /></td>
                </tr>
                <tr>
                    <td>Amount of Sales</td>
                    <td><input type="number" name="amountOfSales" value="" /></td>
                </tr>
                <tr>
                    <td>Percentage of sales</td>
                    <td><input type="number" name="percentageOfSales" value="" /></td>
                </tr>

                </tbody>
            </table>
            <input type="submit" value="Submit" />

        </center>
    </form>

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
