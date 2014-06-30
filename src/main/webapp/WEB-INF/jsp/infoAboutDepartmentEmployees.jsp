<%@ include file="header.jsp" %>
<body style="background: url(img/background.jpg);">
<div id="container">
    <%@ include file="logotypeAndMenu.jsp" %>

    <div id="departmentNameOnInfoPage">
        Employees of <span style="color:red;font-weight: 900">${departmentName}</span> department
        <br>
        <span style="font-family: none;font-size:16px;">Amount of employees :
            <span style="color:red;font-weight: 900">${departmentEmployees.size()}</span>
        </span>
    </div>

    <img style="cursor:pointer;margin-top:-40px;position:absolute;" onclick="backToViewAllDepartmentsPage();"
         src="img/backButton.png">

    <c:if test="${departmentEmployees.size() > 0}">
        <img style="margin-left:950px;;margin-top:-170px;position:absolute;" src="img/infoNote.png">
    </c:if>

    <div id="mainTableContainerToHide" style="margin-top:-50px;">
        <div class="mainTableContainer">
            <table class="mainTable" cellspacing="0"
                   style="border-left : 1px solid #51626f;border-top : 1px solid #51626f;">
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

                <c:forEach var="employee" items="${departmentEmployees}">
                    <tr>
                        <td>${employee.getLastName()}</td>
                        <td>${employee.getFirstName()}</td>
                        <td>${employee.getDateOfBirth()}</td>
                        <td>${employee.getWage()}</td>
                        <td>${employee.getBonus()}</td>
                        <td>${employee.getPenalty()}</td>
                        <td>${employee.getSalary()}</td>
                        <td>
                            <c:choose>
                                <c:when test="${employee.getDepartment().size() > 0}">
                                    <select>
                                        <c:forEach items="${employee.getDepartment()}" var="department">
                                            <option disabled> ${department.getName()} </option>
                                        </c:forEach>
                                    </select>
                                </c:when>

                                <c:otherwise>
                                    <select disabled>
                                        <c:forEach items="${employee.getDepartment()}" var="department">
                                            <option> ${department.getName()} </option>
                                        </c:forEach>
                                    </select>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <c:choose>
                            <c:when test="${employee.getClass().name == 'ua.av.entities.Manager'}">
                                <td>${employee.getAmountOfSales()}</td>
                                <td>${employee.getPercentageOfSales()}</td>
                                <td> -</td>
                                <td> -</td>
                            </c:when>

                            <c:when test="${employee.getClass().name == 'ua.av.entities.Developer'}">
                                <td> -</td>
                                <td> -</td>
                                <td> ${employee.getLinesOfCode()} </td>
                                <td> -</td>
                            </c:when>

                            <c:when test="${employee.getClass().name == 'ua.av.entities.Cleaner'}">
                                <td> -</td>
                                <td> -</td>
                                <td> -</td>
                                <td> ${employee.getAmountOfCleanedOffices()} </td>
                            </c:when>
                        </c:choose>
                        <td>
                            <ul class="actionButtonsMenu">
                                <li>
                                    <form class="editAction" action="editEmployee.html" method="POST">
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
                                    <form class="deleteAction" action="deleteEmployeeFromDepartment.html" method="POST"
                                          onsubmit="deleteConfirmation(${employee.getId()});return false;">
                                        <input type="hidden" name="departmentName" value="${departmentName}">
                                        <button type="submit" name="employeeId" value="${employee.getId()}"
                                                title="Delete employee from department">
                                            <img src="img/delete.png">
                                        </button>
                                    </form>
                                </li>
                                <li>
                                    <form class="infoAction" action="infoAboutEmployee.html" method="POST">
                                        <c:if test="${employee.getClass().name == 'ua.av.entities.Manager'}">
                                            <input type="hidden" name="profession" value="manager"/>
                                        </c:if>
                                        <c:if test="${employee.getClass().name == 'ua.av.entities.Developer'}">
                                            <input type="hidden" name="profession" value="developer"/>
                                        </c:if>
                                        <c:if test="${employee.getClass().name == 'ua.av.entities.Cleaner'}">
                                            <input type="hidden" name="profession" value="cleaner"/>
                                        </c:if>
                                        <button type="submit" name="infoActionId" value="${employee.getId()}"
                                                title="Info about employee">
                                            <img src="img/info.png">
                                        </button>
                                    </form>
                                </li>
                            </ul>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <!-- Container end -->
</div>
<%@ include file="copyright.jsp" %>
<a href="#" class="scrollup"></a>
</body>