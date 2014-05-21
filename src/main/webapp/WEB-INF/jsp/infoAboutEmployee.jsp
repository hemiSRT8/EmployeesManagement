<%@ include file="header.jsp" %>

<body style="background: url(img/background.jpg);">

<div id="container" style="background-color:#FAEBD7;height:560px;overflow: auto;">

    <%@ include file="logotypeAndMenu.jsp" %>

    <center style="margin-top: 50px;">
        <div style="font-family: cuprum;font-weight:900;font-size:20px;margin-bottom:10px;">
            Information about <span style="color:red">e</span>mployee :
        </div>

        <div class="singleEmployeeTableBlock">
            <table class="singleEmployeeTable" cellspacing="0" style="border-left : 1px solid #51626f;border-top : 1px solid #51626f;">
                <tr>
                    <td style="font-weight:900;font-family:cuprum;">Profession</td>
                        <c:if test="${employee.get(0).getClass().name == 'ua.av.entities.Manager'}"> <td>Manager</td> </c:if>
  						<c:if test="${employee.get(0).getClass().name == 'ua.av.entities.Developer'}"><td>Developer</td></c:if>
    					<c:if test="${employee.get(0).getClass().name == 'ua.av.entities.Cleaner'}"><td>Cleaner</td></c:if>
                </tr>

                <tr>
                    <td style="font-weight:900;font-family:cuprum;">Last name</td>
                    <td>${employee.get(0).getLastName()}</td>
                </tr>
                <tr>
                    <td style="font-weight:900;font-family:cuprum;">First name</td>
                    <td>${employee.get(0).getFirstName()}</td>
                </tr>
                <tr>
                    <td style="font-weight:900;font-family:cuprum;">Date of birth</td>
                    <td>${employee.get(0).getDateOfBirth()}</td>
                </tr>
                <tr>
                    <td style="font-weight:900;font-family:cuprum;">Wage</td>
                    <td>${employee.get(0).getWage()}</td>
                </tr>
                <tr>
                    <td style="font-weight:900;font-family:cuprum;">Bonus</td>
                    <td>${employee.get(0).getBonus()}</td>
                </tr>
                <tr>
                    <td style="font-weight:900;font-family:cuprum;">Penalty</td>
                    <td>${employee.get(0).getPenalty()}</td>
                </tr>
                <tr>
                    <td style="font-weight:900;font-family:cuprum;">Salary</td>
                    <td>${employee.get(0).getSalary()}</td>
                </tr>
                <tr>
                    <td style="font-weight:900;font-family:cuprum;">Departments</td>
                    <td>
                        <c:choose>
                            <c:when test="${employee.get(0).getDepartment().size() > 0}">
                                <ul class="singleEmployeeUL">
                                    <c:forEach items="${employee.get(0).getDepartment()}" var="department">
                                        <li>- ${department.getName()} <li>
                                    </c:forEach>
                                </ul>
                            </c:when>

                            <c:otherwise>
                               -
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>

            </table>
        </div>
           <div>
                <form class="editActionSingleEmployee" action="editEmployee.html" method="POST">
                    <input type="hidden" name="profession" value="${employee.get(0).getClass()}"/>
                    <button type = "submit" name = "editEmployeeId" value = "${employee.get(0).getId()}" title="Edit employee">
                        <img src="img/edit.png">
                    </button>
                </form>

                <form class="deleteActionSingleButton" action="deleteEmployee.html" method="POST" onsubmit="deleteConfirmation(${employee.get(0).getId()});return false;">
                    <button type = "submit" name = "deleteEmployeeId" value = "${employee.get(0).getId()}" title="Delete employee">
                        <img src="img/delete.png">
                    </button>
                </form>
           </div>
    </center>

            <!-- Container end -->
</div>
<%@ include file="copyright.jsp" %>
</body>
</html>