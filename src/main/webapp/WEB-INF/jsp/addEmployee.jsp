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
    <script src="js/validator.js"></script>
</head>

<body style="background: url(img/background.jpg);">

<div id="container" style="background : #FAEBD7;">

    <center>
        <div id="employeesImg"> <img src="img/employees.png"> </div>
        <div id="signboard">Manage employees</div>
    </center>

    <form action="saveEmployeeResult.html" method="POST" onsubmit="return ValidateForm(this);">

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
                    <td><input type="text" id="firstName"/></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" id="lastName"/></td>
                </tr>
                <tr>
                    <td>Date of birthday</td>
                    <td><input type="text" max="1995-01-01" min="1960-01-01" id="dateOfBirth"
                        placeholder="yyyy-mm-dd"/></td>
                </tr>
                <tr>
                    <td>Wage</td>
                    <td><input type="number" id="wage" min="0" value="" /></td>
                </tr>
                <tr>
                    <td>Bonus</td>
                    <td><input type="number" id="bonus" min="0" value="" /></td>
                </tr>
                <tr>
                    <td>Penalty</td>
                    <td><input type="number" id="penalty" min="0" value="" /></td>
                </tr>
                <tr>
                    <td>Salary</td>
                    <td><input type="number" id="salary" min="0" value="" /></td>
                </tr>

                <c:forEach var="field" items="${employeeFields}">

                  <tr>
                        <td>${field}</td>
                        <td><input type="number" id="${field}"/></td>
                  </tr>

                </c:forEach>

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
