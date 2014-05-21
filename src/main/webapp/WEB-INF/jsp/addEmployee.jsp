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

    <form action="addEmployeeResult.html" method="POST" onsubmit="return validateForm(this);">

        <center>
            <table class="addEmployee">
                <thead>
                <tr>
                    <h4>Fill in the form to add ${employeeFields.get(0)}</h4>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>First Name</td>
                    <td><input type="text" name="firstName"  value=""/></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" name="lastName"  value=""/></td>
                </tr>
                <tr>
                    <td>Date of birthday [yyyy-mm-dd]</td>
                    <td><input type="text" name="dateOfBirth"
                        placeholder="yyyy-mm-dd"  value=""/></td>
                </tr>
                <tr>
                    <td>Wage</td>
                    <td><input type="text" name="wage" min="0" value="0.0"/></td>
                </tr>
                <tr>
                    <td>Bonus</td>
                    <td><input type="text" name="bonus" min="0" value="0.0" /></td>
                </tr>
                <tr>
                    <td>Penalty</td>
                    <td><input type="text" name="penalty" min="0" value="0.0" /></td>
                </tr>
                <tr>
                    <td>Salary</td>
                    <td><input type="text" name="salary" min="0" value="0.0" /></td>
                </tr>


                <c:forEach begin="1" end="${employeeFields.size()-1}" var="j">

                  <tr><input type="hidden" name="type" value="${employeeFields[0]}">
                        <td>${employeeFields[j]}</td>
                        <td><input type="text" min="0"  value="0.0"
                                   name="${employeeFields[j]}"/></td>
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