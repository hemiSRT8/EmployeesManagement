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

<div id="container" style="background : #FAEBD7;padding-bottom:50px;">

   <center style="margin-bottom:50px;margin-top:20px">
           <c:choose>
               <c:when test="${foundEmployees.size() == 0}">
                   <img src="img/tryagain.png"> <br>
 <br>
                   <div class="failOperationContainer" style="padding-bottom:30px;">
                     No employees were found with "${lastName}" last name
                   </div>
<br>
                   <a href="searchEmployee.html"> Try again ! </a>
               </c:when>

               <c:when test="${foundEmployees.size() == 1}">

                <div class="successOperationContainer">
                  <b style="color:red"> 1 </b> employee was found with last name <b style="color:red"> ${lastName} </b>
               </div>
<br>
                   <table class="searchResultsSingleTable" cellspacing="0" >
						
					<c:if test="${foundEmployees.get(0).getClass().name == 'ua.av.entities.Manager'}">
						<thead style="color:blue;">
							<tr>
								<th colspan="2" >Manager</th>
							</tr>
						</thead>
					</c:if>
					
					<c:if test="${foundEmployees.get(0).getClass().name == 'ua.av.entities.Developer'}">
						<thead style="color:blue;">
							<tr>
								<th colspan="2" >Developer</th>
							</tr>
						</thead>
					</c:if>
					
					<c:if test="${foundEmployees.get(0).getClass().name == 'ua.av.entities.Cleaner'}">
						<thead style="color:blue;">
							<tr>
								<th colspan="2" >Cleaner</th>
							</tr>
						</thead>
					</c:if>
				   
                       <tr>
                           <td style="font-weight:900;"> Last name </td>
                           <td> ${foundEmployees.get(0).getLastName()} </td>
                       </tr>
                       <tr>
                           <td style="font-weight:900;"> First name  </td>
                           <td> ${foundEmployees.get(0).getFirstName()} </td>
                       </tr>
                       <tr>
                           <td style="font-weight:900;"> Date of birth </td>
                           <td> ${foundEmployees.get(0).getDateOfBirth()} </td>
                       </tr>

                       <tr>
                           <td style="font-weight:900;"> Wage </td>
                           <td> ${foundEmployees.get(0).getWage()} </td>
                       </tr>

                       <tr>
                           <td style="font-weight:900;"> Bonus </td>
                           <td> ${foundEmployees.get(0).getBonus()} </td>
                       </tr>

                       <tr>
                           <td style="font-weight:900;"> Penalty </td>
                           <td> ${foundEmployees.get(0).getPenalty()} </td>
                       </tr>

                       <tr>
                           <td style="font-weight:900;"> Salary </td>
                           <td> ${foundEmployees.get(0).getSalary()} </td>
                       </tr>

                   <c:if test="${foundEmployees.get(0).getClass().name == 'ua.av.entities.Manager'}">
   						<tr>
   							<td style="font-weight:900;"> Amount of sales </td>
   							<td> ${foundEmployees.get(0).getAmountOfSales()} </td>
   						</tr>

   						<tr>
   							<td> Percentage of sales </td>
   							<td> ${foundEmployees.get(0).getPercentageOfSales()} </td>
   						</tr>
						
						<tr>
							<td style="font-weight:900;">
								Action
							</td>
						
							<td>
								<form class="editActionSearchForm" action="editEmployee.html" method="POST">
									<input type="hidden" name="profession" value="manager"/>
										<button type = "submit" name = "editEmployeeId" value = "${employee.getId()}" title="Edit employee">
											<img src="img/edit.png">
										</button>
									</form>
						
								<form class="deleteActionSearchForm" action="deleteEmployee.html" method="POST">
									<button type = "submit" name = "deleteEmployeeId" value = "${employee.getId()}" title="Delete employee">
										<img src="img/delete.png">
									</button>
								</form>
							</td>
						</tr>
   				   </c:if>

   				   <c:if test="${foundEmployees.get(0).getClass().name == 'ua.av.entities.Developer'}">
   						<tr>
   							<td style="font-weight:900;"> Lines of code </td>
   							<td style="font-weight:900;"> ${foundEmployees.get(0).getLinesOfCode()} </td>
   						</tr>
						
						<tr>
							<td style="font-weight:900;">
								Action
							</td>
						
							<td>
								<form class="editActionSearchForm" action="editEmployee.html" method="POST">
									<input type="hidden" name="profession" value="developer"/>
										<button type = "submit" name = "editEmployeeId" value = "${employee.getId()}" title="Edit employee">
											<img src="img/edit.png">
										</button>
									</form>
						
								<form class="deleteActionSearchForm" action="deleteEmployee.html" method="POST">
									<button type = "submit" name = "deleteEmployeeId" value = "${employee.getId()}" title="Delete employee">
										<img src="img/delete.png">
									</button>
								</form>
							</td>
						</tr>
   				   </c:if>

   				   <c:if test="${foundEmployees.get(0).getClass().name == 'ua.av.entities.Cleaner'}">
   						<tr>
   							<td style="font-weight:900;"> Amount of cleaned offices </td>
   							<td> ${foundEmployees.get(0).getAmountOfCleanedOffices()} </td>
   						</tr>
   						
   					    <tr>
							<td style="font-weight:900;">
								Action
							</td>
						
							<td>
								<form class="editActionSearchForm" action="editEmployee.html" method="POST">
									<input type="hidden" name="profession" value="cleaner"/>
										<button type = "submit" name = "editEmployeeId" value = "${employee.getId()}" title="Edit employee">
											<img src="img/edit.png">
										</button>
									</form>
						
								<form class="deleteActionSearchForm" action="deleteEmployee.html" method="POST">
									<button type = "submit" name = "deleteEmployeeId" value = "${employee.getId()}" title="Delete employee">
										<img src="img/delete.png">
									</button>
								</form>
							</td>
						</tr>
   				   </c:if>
   				</table>
               </c:when>

              <c:otherwise>
               <div class="successOperationContainer">
                  <b style="color:red">  ${foundEmployees.size()} </b> employees were found with last name <b style="color:red"> ${lastName} </b>
               </div>
<br>
                 <img src="img/result.png"> <br>
   				<c:forEach items="${foundEmployees}" var="employee">
   					<table class="searchResultsMultipleTable" cellspacing="0">
   					
					<c:if test="${foundEmployees.get(0).getClass().name == 'ua.av.entities.Manager'}">
						<thead style="color:blue;">
							<tr>
								<th colspan="2" >Manager</th>
							</tr>
						</thead>
					</c:if>
					
					<c:if test="${foundEmployees.get(0).getClass().name == 'ua.av.entities.Developer'}">
						<thead style="color:blue;">
							<tr>
								<th colspan="2" >Developer</th>
							</tr>
						</thead>
					</c:if>
					
					<c:if test="${foundEmployees.get(0).getClass().name == 'ua.av.entities.Cleaner'}">
						<thead style="color:blue;">
							<tr>
								<th colspan="2" >Cleaner</th>
							</tr>
						</thead>
					</c:if>

                    <tr>
                           <td style="font-weight:900;"> Last name </td>
                           <td> ${employee.getLastName()} </td>
                       </tr>
                       <tr>
                           <td style="font-weight:900;"> First name  </td>
                           <td> ${employee.getFirstName()} </td>
                       </tr>
                       <tr>
                           <td style="font-weight:900;"> Date of birth </td>
                           <td> ${employee.getDateOfBirth()} </td>
                       </tr>

                       <tr>
                          <td style="font-weight:900;"> Wage </td>
                           <td> ${employee.getWage()} </td>
                       </tr>

                       <tr>
                           <td style="font-weight:900;"> Bonus </td>
                           <td> ${employee.getBonus()} </td>
                       </tr>

                       <tr>
                           <td style="font-weight:900;"> Penalty </td>
                           <td> ${employee.getPenalty()} </td>
                       </tr>

                       <tr>
                           <td style="font-weight:900;"> Salary </td>
                           <td> ${employee.getSalary()} </td>
                       </tr>

                   <c:if test="${employee.getClass().name == 'ua.av.entities.Manager'}">
   						<tr>
   							<td style="font-weight:900;"> Amount of sales </td>
   							<td> ${employee.getAmountOfSales()} </td>
   						</tr>

   						<tr>
   							<td style="font-weight:900;"> Percentage of sales </td>
   							<td> ${employee.getPercentageOfSales()} </td>
   						</tr>
						
						<tr>
							<td style="font-weight:900;">
								Action
							</td>
						
							<td>
								<form class="editActionSearchForm" action="editEmployee.html" method="POST">
									<input type="hidden" name="profession" value="manager"/>
										<button type = "submit" name = "editEmployeeId" value = "${employee.getId()}" title="Edit employee">
											<img src="img/edit.png">
										</button>
									</form>
						
								<form class="deleteActionSearchForm" action="deleteEmployee.html" method="POST">
									<button type = "submit" name = "deleteEmployeeId" value = "${employee.getId()}" title="Delete employee">
										<img src="img/delete.png">
									</button>
								</form>
							</td>
						</tr>
   				   </c:if>


   				   <c:if test="${employee.getClass().name == 'ua.av.entities.Developer'}">
   						<tr>
   							<td style="font-weight:900;"> Lines of code </td>
   							<td> ${employee.getLinesOfCode()} </td>
   						</tr>
						
						<tr>
							<td style="font-weight:900;">
								Action
							</td>
						
							<td>
								<form class="editActionSearchForm" action="editEmployee.html" method="POST">
									<input type="hidden" name="profession" value="developer"/>
										<button type = "submit" name = "editEmployeeId" value = "${employee.getId()}" title="Edit employee">
											<img src="img/edit.png">
										</button>
									</form>
						
								<form class="deleteActionSearchForm" action="deleteEmployee.html" method="POST">
									<button type = "submit" name = "deleteEmployeeId" value = "${employee.getId()}" title="Delete employee">
										<img src="img/delete.png">
									</button>
								</form>
							</td>
						</tr>
   				   </c:if>

   				   <c:if test="${employee.getClass().name == 'ua.av.entities.Cleaner'}">
   						<tr>
   							<td style="font-weight:900;"> Amount of cleaned offices </td>
   							<td> ${employee.getAmountOfCleanedOffices()} </td>
   						</tr>

						<tr>
							<td style="font-weight:900;">
								Action
							</td>
						
							<td>
								<form class="editActionSearchForm" action="editEmployee.html" method="POST">
									<input type="hidden" name="profession" value="cleaner"/>
										<button type = "submit" name = "editEmployeeId" value = "${employee.getId()}" title="Edit employee">
											<img src="img/edit.png">
										</button>
									</form>
						
								<form class="deleteActionSearchForm" action="deleteEmployee.html" method="POST">
									<button type = "submit" name = "deleteEmployeeId" value = "${employee.getId()}" title="Delete employee">
										<img src="img/delete.png">
									</button>
								</form>
							</td>
						</tr>
				   </c:if>
   				</table>

   				</c:forEach>
               </c:otherwise>
           </c:choose>
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