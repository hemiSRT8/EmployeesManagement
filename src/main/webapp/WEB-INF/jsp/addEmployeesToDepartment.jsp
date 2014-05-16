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

<body style="background: url(img/background.jpg);margin-top:100px;">

<div id="container" style="background : #FAEBD7;height:550px;">

<center>

   <div style="float:left;margin-left:40px;position:fixed;">
       <span style="font-weight:900;padding-left:23px;"> <span style="color:red;">D</span>epartments</span>
       <br>
       <span style="padding-left:23px;">(use CTRL for multiple selection)</span>

       <br><br>

       <div style="margin-top:220px;margin-left:95px;position:absolute;height:20px;width:20px;">
            <form action="addEmployeesToDepartmentResult.html" method="POST">
            <input type="submit" name="Submit" value="Submit">
       </div>

       <select name="department" multiple="multiple" style="height:200px;float:left;margin-left:55px;">
            <c:forEach items="${departments}" var="department">
                <option value = "${department.getName()}"> ${department.getName()} </option>
            </c:forEach>
       </select>
   </div>

  <div style="float:right;margin-left:50px;border-left: 1px solid black;">
      <div style="padding-left:100px;padding-right:70px;overflow:auto;">
        <span style="font-weight:900;"><span style="color:red;">E</span>mployees</span>
        <br>
        (select employee which you want to add into the department)

<br><br><br>

        <table class="employeesMainTable" cellspacing="0">
                    <tr>
                        <th>Profession</th>
                        <th>Last name</th>
                        <th>First name</th>
                        <th>Date of birth</th>
                        <th>Departments</th>
                        <th style="font-weight:900;color:blue;">Select</th>
                    </tr>
                    <c:forEach var="manager" items="${managers}">
                        <tr>
                            <td>Manager</td>
                            <td>${manager.getLastName()}</td>

                           <td>${manager.getFirstName()}</td>
                            <td>${manager.getDateOfBirth()}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${manager.getDepartment().size() > 0}">
                                        <select multiple disabled>
                                            <c:forEach items="${manager.getDepartment()}" var="department">
                                                <option> ${department.getName()} </option>
                                            </c:forEach>
                                        </select>
                                    </c:when>
                                    <c:otherwise>
                                        <span style="color:red;font-weight:bold;"> No departments </span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <input type="checkbox" name="employeeId" value="${manager.getId()}">
                            </td>
                        </tr>
                    </c:forEach>

                    <c:forEach var="developer" items="${developers}">
                        <tr>
                            <td>Developer</td>
                            <td>${developer.getLastName()}</td>
                            <td>${developer.getFirstName()}</td>
                            <td>${developer.getDateOfBirth()}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${developer.getDepartment().size() > 0}">
                                        <select multiple disabled>
                                            <c:forEach items="${developer.getDepartment()}" var="department">
                                                <option> ${department.getName()} </option>
                                            </c:forEach>
                                        </select>
                                    </c:when>
                                    <c:otherwise>
                                        <span style="color:red;font-weight:bold;"> No departments </span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                             <td>
                                <input type="checkbox" name="employeeId" value="${developer.getId()}">
                             </td>
                        </tr>
                    </c:forEach>

                    <c:forEach var="cleaner" items="${cleaners}">
                        <tr>
                            <td>Cleaner</td>
                            <td>${cleaner.getLastName()}</td>
                            <td>${cleaner.getFirstName()}</td>
                            <td>${cleaner.getDateOfBirth()}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${cleaner.getDepartment().size() > 0}">
                                        <select multiple disabled>
                                            <c:forEach items="${cleaner.getDepartment()}" var="department">
                                                <option> ${department.getName()} </option>
                                            </c:forEach>
                                        </select>
                                    </c:when>
                                    <c:otherwise>
                                        <span style="color:red;font-weight:bold;"> No departments </span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <input type="checkbox" name="employeeId" value="${cleaner.getId()}">
                            </td>
                        </tr>
                    </c:forEach>
     </form>                </table>
        </div>
    </div>

        <!-- Container end -->
    </div>
</center>
<br><br><br>

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