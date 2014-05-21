<%@ include file="header.jsp" %>

<body style="background: url(img/background.jpg);">

<div id="container" style="background : #FAEBD7;height:580px;overflow:auto;">

 <%@ include file="logotypeAndMenu.jsp" %>

<center style="margin-top:50px;">

   <div style="float:left;margin-left:40px;position:fixed;">
       <span style="font-weight:900;padding-left:23px;"> <span style="color:red;">D</span>epartments</span>
       <br>
       <span style="padding-left:23px;">(use CTRL for multiple selection)</span>

       <br><br>

       <div style="margin-top:220px;margin-left:100px;position:absolute;height:20px;width:20px;">
            <form action="addEmployeesToDepartmentResult.html" method="POST">
            <button type="submit" class="addEmployeesToDepartmentButton"></button>
            <button type="button" class="cancelAddEmployeesToDepartmentButton" onclick="cancelAddEmployeesToDepartmentOperation();"></button>
       </div>

       <select required name="department" multiple="multiple" style="height:200px;margin-left:30px;width:160px;">
            <c:forEach items="${departments}" var="department">
                <option value = "${department.getName()}"> ${department.getName()} </option>
            </c:forEach>
       </select>
   </div>

  <div style="float:right;margin-left:50px;border-left: 1px dotted grey;border-top: 1px dotted grey;">
      <div style="padding-left:100px;padding-right:70px;padding-bottom:15px;overflow:auto;">
        <span style="font-weight:900;"><span style="color:red;">E</span>mployees</span>
        <br>
        (select employee which you want to add into the department)

<br><br><br>

        <table class="addEmployeesToDepartmentTable" cellspacing="0" style="border-left : 1px solid #51626f;border-top : 1px solid #51626f;">
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
<%@ include file="copyright.jsp" %>
<a href="#" class="scrollup"></a>
</body>
</html>