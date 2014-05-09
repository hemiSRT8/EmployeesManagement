<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<meta http-equiv=Content-Type content='text/html; charset=utf-8'>
<meta name="keywords" content="Company, developer, manager, cleaner, com.av.department">
<meta name="description" content="Web project: Company management">

<title>Employees management</title>

<head>
    <link rel="stylesheet" type="text/css" href="style.css" />
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="js/hideAndShow.js"></script>
    <script src="js/infoBox.js"></script>
</head>

<body style="background: url(img/background.jpg);">

<div id="container">

    <center>
        <div id="employeesImg"> <img src="img/employees.png"> </div>
        <div id="signboard"> Manage employees</div>
    </center>

    <ul class="professions">
        <li>
            <center>
                    <div id="nameOfProfession"> Managers </div>
                    <br>
                    <img src="img/manager.png">
                    <br>
                    <button onClick=show1_onclick()> SHOW </button>
                    <button onClick=hide1_onclick()> HIDE </button>
            </center>
        </li>

        <li>
             <center>
                        <div id="nameOfProfession"> Developers </div>
                        <br>
                        <img src="img/developer.jpg">
                        <br>
                        <button onClick=show2_onclick()> SHOW </button>
                        <button onClick=hide2_onclick()> HIDE </button>
             </center>
        </li>

        <li>
             <center>
                    <div id="nameOfProfession"> Cleaners </div>
                    <br>
                    <img src="img/cleaner.png">
                    <br>
                    <button onClick=show3_onclick()> SHOW </button>
                    <button onClick=hide3_onclick()> HIDE </button>
             </center>
        </li>
    </ul>

    <br>
    <br>
    <br>
    <br>
    <br>

    <div id="managersMainTableContainer" style="display:none;">
		<table class="employeesMainTable" cellspacing="0" cellpadding="1px">
			<tr>
				<th>Last name</th>
				<th>First name</th>
				<th>Date of birth</th>
				<th>Wage</th>
				<th>Bonus</th>
				<th>Penalty</th>
				<th>Salary</th>
				<th>Amount of sales</th>
				<th>Percentage of sales</th>
				<th>Action</th>
			</tr>

			<c:forEach var="manager" items="${managers}" >
				<tr>
				   <td>${manager.getLastName()}</td>
				   <td>${manager.getFirstName()}</td>
				   <td>${manager.getDateOfBirth()}</td>
				   <td>${manager.getWage()}</td>
				   <td>${manager.getBonus()}</td>
				   <td>${manager.getPenalty()}</td>
				   <td>${manager.getSalary()}</td>
				   <td>${manager.getAmountOfSales()}</td>
				   <td>${manager.getPercentageOfSales()}</td>
				   <td>
						<form class="viewAction" action="" method="POST">
							<button type = "submit" name = "viewEmployee" value = ""> <img src="img/view.png"> </button>
					    </form>

					    <form class="deleteAction" action="deleteEmployee.html" method="POST">
                        	<button type = "submit" name = "deleteEmployee" value = "${manager.getId()}" onclick="infoBox()">
                        	        <img src="img/delete.png">
                        	</button>
                        </form>
				   </td>
				</tr>
			  </c:forEach>

		</table>
    </div>


    <div id="developersMainTableContainer" style="display:none;">
        <table class="employeesMainTable" cellspacing="0">
            <tr>
                            <th>Last name</th>
                            <th>First name</th>
                            <th>Date of birth</th>
                            <th>Wage</th>
                            <th>Bonus</th>
                            <th>Penalty</th>
                            <th>Salary</th>
                            <th>Lines of code</th>
                            <th>Action</th>
            </tr>
			<c:forEach var="developer" items="${developers}" >
				<tr>
				    <td>${developer.getLastName()}</td>
					<td>${developer.getFirstName()}</td>
					<td>${developer.getDateOfBirth()}</td>
					<td>${developer.getWage()}</td>
					<td>${developer.getBonus()}</td>
					<td>${developer.getPenalty()}</td>
					<td>${developer.getSalary()}</td>
					<td>${developer.getLinesOfCode()}</td>
					<td>
                    	<form class="viewAction" action="" method="POST">
                    	    <button type = "submit" name = "viewEmployee" value = ""> <img src="img/view.png"> </button>
                    	</form>

                        <form class="deleteAction" action="deleteEmployee.html" method="POST">
                            <button type = "submit" name = "deleteEmployee" value = "${developer.getId()}" onclick="infoBox()">
                                <img src="img/delete.png">
                            </button>
                        </form>
                    </td>
				</tr>
			</c:forEach>
        </table>
    </div>

	<div id="cleanersMainTableContainer" style="display:none;">
        <table class="employeesMainTable" cellspacing="0">
            <tr>
                            <th>Last name</th>
                            <th>First name</th>
                            <th>Date of birth</th>
                            <th>Wage</th>
                            <th>Bonus</th>
                            <th>Penalty</th>
                            <th>Salary</th>
                            <th>Cleaned offices</th>
                            <th>Action</th>
            </tr>

			<c:forEach var="cleaner" items="${cleaners}" >
				<tr>
					<td>${cleaner.getLastName()}</td>
					<td>${cleaner.getFirstName()}</td>
					<td>${cleaner.getDateOfBirth()}</td>
					<td>${cleaner.getWage()}</td>
					<td>${cleaner.getBonus()}</td>
					<td>${cleaner.getPenalty()}</td>
					<td>${cleaner.getSalary()}</td>
					<td>${cleaner.getAmountOfCleanedOffices()}</td>
					<td>
							<form class="viewAction" action="" method="POST">
                                <button type = "submit" name = "viewEmployee" value = ""> <img src="img/view.png"> </button>
                            </form>

                            <form class="deleteAction" action="deleteEmployee.html" method="POST">
                                <button type = "submit" name = "deleteEmployee" value = "${cleaner.getId()}" onclick="infoBox()">
                                    <img src="img/delete.png">
                                </button>
                            </form>
					</td>
				</tr>
			</c:forEach>
        </table>
    </div>	
	
	<!-- Container end -->
</div>

<div id="copyright">
    by A69V &copy; 2014
<br>
    boss : Tolya
</div>

</body>
</html>