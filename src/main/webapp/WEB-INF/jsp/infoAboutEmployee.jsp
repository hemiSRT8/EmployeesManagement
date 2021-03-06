<%@ include file="header.jsp" %>

<body style="background: url(img/background.jpg);">

<div id="container" style="background-color:#FAEBD7;height:720px;overflow: auto;">

    <%@ include file="logotypeAndMenu.jsp" %>

    <center style="margin-top: 50px;">
        <div style="font-family: cuprum;font-weight:900;font-size:20px;margin-bottom:10px;">
            Information about <span style="color:red">e</span>mployee :
        </div>

        <div class="singleEmployeeTableBlock">
            <table class="singleEmployeeTable" cellspacing="0"
                   style="border-left : 1px solid #51626f;border-top : 1px solid #51626f;">
                <tr>
                    <td style="font-weight:900;font-family:cuprum;">Profession</td>
                    <c:if test="${employee.getClass().name == 'ua.av.entities.Manager'}">
                        <td>Manager</td>
                    </c:if>
                    <c:if test="${employee.getClass().name == 'ua.av.entities.Developer'}">
                        <td>Developer</td>
                    </c:if>
                    <c:if test="${employee.getClass().name == 'ua.av.entities.Cleaner'}">
                        <td>Cleaner</td>
                    </c:if>
                </tr>

                <tr>
                    <td style="font-weight:900;font-family:cuprum;">Last name</td>
                    <td>${employee.getLastName()}</td>
                </tr>
                <tr>
                    <td style="font-weight:900;font-family:cuprum;">First name</td>
                    <td>${employee.getFirstName()}</td>
                </tr>
                <tr>
                    <td style="font-weight:900;font-family:cuprum;">Date of birth</td>
                    <td>${employee.getDateOfBirth()}</td>
                </tr>
                <tr>
                    <td style="font-weight:900;font-family:cuprum;">Wage</td>
                    <td>${employee.getWage()}</td>
                </tr>
                <tr>
                    <td style="font-weight:900;font-family:cuprum;">Bonus</td>
                    <td>${employee.getBonus()}</td>
                </tr>
                <tr>
                    <td style="font-weight:900;font-family:cuprum;">Penalty</td>
                    <td>${employee.getPenalty()}</td>
                </tr>
                <tr>
                    <td style="font-weight:900;font-family:cuprum;">Salary</td>
                    <td>${employee.getSalary()}</td>
                </tr>
                <c:if test="${employee.getClass().name == 'ua.av.entities.Manager'}">
                    <tr>
                        <td style="font-weight:900;font-family:cuprum;">Amount of sales</td>
                        <td>${employee.getAmountOfSales()}</td>
                    </tr>
                    <tr>
                        <td style="font-weight:900;font-family:cuprum;">Percentage of sales</td>
                        <td>${employee.getPercentageOfSales()}</td>
                    </tr>
                </c:if>
                <c:if test="${employee.getClass().name == 'ua.av.entities.Developer'}">
                    <tr>
                        <td style="font-weight:900;font-family:cuprum;">Lines of code</td>
                        <td>${employee.getLinesOfCode()}</td>
                    </tr>
                </c:if>
                <c:if test="${employee.getClass().name == 'ua.av.entities.Cleaner'}">
                    <tr>
                        <td style="font-weight:900;font-family:cuprum;">Amount of cleaned offices</td>
                        <td>${employee.getAmountOfCleanedOffices()}</td>
                    </tr>
                </c:if>

                <tr>
                    <td style="font-weight:900;font-family:cuprum;">Departments</td>
                    <td style="text-align:left;">
                        <c:choose>
                            <c:when test="${employee.getDepartment().size() > 0}">
                                <ul class="singleEmployeeDepartmentsUL">
                                    <c:forEach items="${employee.getDepartment()}" var="department">
                                        <li> ${department.getName()} </li>
                                    </c:forEach>
                                </ul>
                            </c:when>

                            <c:otherwise>
                                <div style="text-align: center">-</div>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>

            </table>
        </div>
    </center>

    <div id="singEmployeeEditButtons">
        <ul class="singleEmployeeButtons">
            <li>
                <form class="editActionSingleEmployee" action="editEmployee.html" method="POST">
                    <c:if test="${employee.getClass().name == 'ua.av.entities.Manager'}">
                        <input type="hidden" name="profession" value="Manager"/>
                    </c:if>
                    <c:if test="${employee.getClass().name == 'ua.av.entities.Developer'}">
                        <input type="hidden" name="profession" value="Developer"/>
                    </c:if>
                    <c:if test="${employee.getClass().name == 'ua.av.entities.Cleaner'}">
                        <input type="hidden" name="profession" value="Cleaner"/>
                    </c:if>
                    <button type="submit" name="editEmployeeId" value="${employee.getId()}"
                            title="Edit employee">
                        <img src="img/edit.png">
                    </button>
                </form>
            </li>
            <li>
                <form class="deleteActionSingleButton" action="deleteEmployee.html" method="POST"
                      onsubmit="deleteConfirmation(${employee.getId()});return false;">
                    <button type="submit" name="deleteEmployeeId" value="${employee.getId()}"
                            title="Delete employee">
                        <img src="img/delete.png">
                    </button>
                </form>
            </li>
        </ul>
    </div>

    <!-- Container end -->
</div>
<%@ include file="copyright.jsp" %>
</body>