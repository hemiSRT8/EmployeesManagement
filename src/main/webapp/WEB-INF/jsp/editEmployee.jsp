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

<body style="background: url(img/background.jpg);margin-top:100px">

<div id="container" style="background : #FAEBD7;">

  <form action="editEmployeeResult.html" method="POST">
    <center>
        <table class="editEmployeeTable" cellspacing="0" style="border-left : 1px solid #51626f;">
            <thead>
                <tr>
                    <th colspan="2" >Edit employee</th>
                </tr>
            </thead>

            <tbody>
               <tr style="display:none;">
                     <td> <input type="text" name="id" value="${employee.getId()}"  /> </td>
               </tr>

               <tr style="font-weight:900;">
                    <td>Last Name</td>
                    <td><input type="text" name="lastName" value="" placeholder="${employee.getLastName()}" required /></td>
                </tr>

                <tr>
                    <td>First Name</td>
                    <td><input type="text" name="firstName" value="" placeholder="${employee.getFirstName()}" required /></td>
                </tr>

                <tr>
                    <td>Date of birthday</td>
                    <td><input type="text" name="dateOfBirth" value="" placeholder="${employee.getDateOfBirth()}" required  /></td>
                </tr>

                <tr>
                    <td>Wage</td>
                    <td><input type="text" name="wage" value="" placeholder="${employee.getWage()}" required  /></td>
                </tr>

                <tr>
                    <td>Bonus</td>
                    <td><input type="text" name="bonus" value="" placeholder="${employee.getBonus()}" required  /></td>
                </tr>

                <tr>
                    <td>Penalty</td>
                    <td><input type="text" name="penalty" value="" placeholder="${employee.getPenalty()} " required  /></td>
                </tr>

                <tr>
                    <td>Salary</td>
                    <td><input type="text" name="salary" value="" placeholder="${employee.getSalary()}" required  /></td>
                </tr>

                <c:choose>

                      <c:when test="${profession == 'manager'}">
                        <tr style="display:none;">
                            <td><input type="text" name="type" value="manager" /></td>
                        </tr>

                        <tr>
                            <td>Amount of Sales</td>
                            <td><input type="text" name="amountOfSales" value="" placeholder="${employee.getAmountOfSales()}" required  /></td>
                        </tr>

                        <tr>
                            <td>Percentage of sales</td>
                            <td><input type="text" name="percentageOfSales" value="" placeholder="${employee.getPercentageOfSales()}" required  /></td>
                        </tr>
                      </c:when>

                      <c:when test="${profession == 'developer'}">
                         <tr style="display:none;">
                            <td><input type="text" name="type" value="developer" /></td>
                         </tr>

                        <tr>
                            <td>Lines of code</td>
                            <td><input type="text" name="linesOfCode" value="" placeholder="${employee.getLinesOfCode()}" required  /></td>
                        </tr>
                      </c:when>

                      <c:when test="${profession == 'cleaner'}">
                        <tr style="display:none;">
                            <td><input type="text" name="type" value="cleaner" /></td>
                        </tr>

                        <tr>
                            <td>Amount of cleaned offices</td>
                            <td><input type="text" name="amountOfCleanedOffices" value="" placeholder="${employee.getAmountOfCleanedOffices()}" required  /></td>
                        </tr>
                      </c:when>

                </c:choose>
            </tbody>
        </table>

        <br>
        <input type="submit" value="Submit" style="color:green; font-weight:900;"/>
        <input type="submit" value="Reset" style="color:red; font-weight:900;" />
        <br><br>
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