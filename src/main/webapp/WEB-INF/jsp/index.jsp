<%@ include file="header.jsp" %>
<body style="background: url(img/background.jpg);">
<div id="container">
    <%@ include file="logotypeAndMenu.jsp" %>
      <div class="mainTableContainer">
		<table class="mainTable" cellspacing="0" style="border-left : 1px solid #51626f;border-top : 1px solid #51626f;" >
			<tr>
				<th>Last name</th>
				<th>First name</th>
				<th>Date of birth</th>
				<th>Wage</th>
				<th>Bonus</th>
				<th>Penalty</th>
				<th>Salary</th>
				<th>Departments</th>
				<th>Amount of sales</th>
				<th>Percentage of sales</th>
				<th>Lines of code</th>
				<th>Amount of cleaned offices</th>
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
				   <td>
						<c:choose>
						   <c:when test="${manager.getDepartment().size() > 0}">
								<select>
									<c:forEach items="${manager.getDepartment()}" var="department">
										<option disabled> ${department.getName()} </option>
									</c:forEach>
								</select>
						   </c:when>
						   
						   <c:otherwise>
								<select disabled>
									<c:forEach items="${manager.getDepartment()}" var="department">
										<option> ${department.getName()} </option>
									</c:forEach>
								</select>
						   </c:otherwise>
					   </c:choose>
                   </td>
				   <td>${manager.getAmountOfSales()}</td>
				   <td>${manager.getPercentageOfSales()}</td>
				   <td> - </td>
				   <td> - </td>
				   <td>
				        <form class="editAction" action="editEmployee.html" method="POST">
                        	<input type="hidden" name="profession" value="manager"/>
                        	<button type = "submit" name = "editEmployeeId" value = "${manager.getId()}" title="Edit employee">
                                    <img src="img/edit.png">
                            </button>
                        </form>

                        <form class="deleteAction" action="deleteEmployee.html" method="POST" onsubmit="deleteConfirmation(${manager.getId()});return false;">
                        	<button type = "submit" name = "deleteEmployeeId" value = "${manager.getId()}" title="Delete employee">
                        	        <img src="img/delete.png">
                        	</button>
                        </form>
				   </td>
				</tr>
			  </c:forEach>

			  <c:forEach var="developer" items="${developers}" >
				<tr>
				   <td>${developer.getLastName()}</td>
				   <td>${developer.getFirstName()}</td>
				   <td>${developer.getDateOfBirth()}</td>
				   <td>${developer.getWage()}</td>
				   <td>${developer.getBonus()}</td>
				   <td>${developer.getPenalty()}</td>
				   <td>${developer.getSalary()}</td>
				   <td>
						<c:choose>
						   <c:when test="${developer.getDepartment().size() > 0}">
								<select>
									<c:forEach items="${developer.getDepartment()}" var="department">
										<option disabled> ${department.getName()} </option>
									</c:forEach>
								</select>
						   </c:when>
						   
						   <c:otherwise>
								<select disabled>
									<c:forEach items="${developer.getDepartment()}" var="department">
										<option> ${department.getName()} </option>
									</c:forEach>
								</select>
						   </c:otherwise>
					   </c:choose>
                   </td>
				   <td> - </td>
                   <td> - </td>
				   <td>${developer.getLinesOfCode()}</td>
                   <td> - </td>

				   <td>
				        <form class="editAction" action="editEmployee.html" method="POST">
                        	<input type="hidden" name="profession" value="manager"/>
                        	<button type = "submit" name = "editEmployeeId" value = "${developer.getId()}" title="Edit employee">
                                    <img src="img/edit.png">
                            </button>
                        </form>

                        <form class="deleteAction" action="deleteEmployee.html" method="POST" onsubmit="deleteConfirmation(${developer.getId()});return false;">
                        	<button type = "submit" name = "deleteEmployeeId" value = "${developer.getId()}" title="Delete employee">
                        	        <img src="img/delete.png">
                        	</button>
                        </form>
				   </td>
				</tr>
			  </c:forEach>
			  
			  <c:forEach var="cleaner" items="${cleaners}" >
				<tr>
				   <td>${cleaner.getLastName()}</td>
				   <td>${cleaner.getFirstName()}</td>
				   <td>${cleaner.getDateOfBirth()}</td>
				   <td>${cleaner.getWage()}</td>
				   <td>${cleaner.getBonus()}</td>
				   <td>${cleaner.getPenalty()}</td>
				   <td>${cleaner.getSalary()}</td>
				   <td>
						<c:choose>
						   <c:when test="${cleaner.getDepartment().size() > 0}">
								<select>
									<c:forEach items="${cleaner.getDepartment()}" var="department">
										<option disabled> ${department.getName()} </option>
									</c:forEach>
								</select>
						   </c:when>
						   
						   <c:otherwise>
								<select disabled>
									<c:forEach items="${cleaner.getDepartment()}" var="department">
										<option> ${department.getName()} </option>
									</c:forEach>
								</select>
						   </c:otherwise>
					   </c:choose>
                   </td>
				   <td> - </td>
                   <td> - </td>
                   <td> - </td>
				   <td>${cleaner.getAmountOfCleanedOffices()}</td>
				   <td>
				        <form class="editAction" action="editEmployee.html" method="POST">
                        	<input type="hidden" name="profession" value="cleaner"/>
                        	<button type = "submit" name = "editEmployeeId" value = "${cleaner.getId()}" title="Edit employee">
                                    <img src="img/edit.png">
                            </button>
                        </form>

                        <form class="deleteAction" action="deleteEmployee.html" method="POST" onsubmit="deleteConfirmation(${cleaner.getId()});return false;">
                        	<button type = "submit" name = "deleteEmployeeId" value = "${cleaner.getId()}" title="Delete employee">
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
<%@ include file="copyright.jsp" %>
<a href="#" class="scrollup"></a>
</body>
</html>