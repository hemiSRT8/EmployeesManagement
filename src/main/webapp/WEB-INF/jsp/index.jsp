<%@ include file="header.jsp" %>
<body style="background: url(img/background.jpg);">
<div id="container">
    <%@ include file="logotypeAndMenu.jsp" %>

    <div id="searchForm">
        <form action="searchEmployee.html" method="POST">
            <input type="search" id="searchFormBlock" name="lastName" value=""
                   placeholder="Search by last name"
                   pattern="^[A-Za-z]{1,15}$"
                   required="">

    </div>

    <div id="searchFormButton">
        <button style="margin-left:-10px;margin-top:5px;" type="submit"><img src="img/searchLoop.gif"></button>
    </div>

    </form>
    <div class="sortEmployeesButton">
        SORT BY :
    </div>

    <div class="fullNameSortOption">
        <form method="POST">
            <input type="hidden" name="sortType" value="fullName"/>
            <button type="submit" title="sort by full name">
                full name
            </button>
        </form>
    </div>

    <div class="dateOfBirthSortOption">
        <form method="POST">
            <input type="hidden" name="sortType" value="dateOfBirth"/>
            <button type="submit" title="sort by date of birth">
                date of birth
            </button>
        </form>
    </div>

    <div class="salarySortOption">
        <form method="POST">
            <input type="hidden" name="sortType" value="salary"/>
            <button type="submit" title="sort by salary">
                salary
            </button>
        </form>
    </div>

    <div id="mainTableContainerToHide">
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

                <c:forEach var="employee" items="${employees}">
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
                                        <c:if test="${employee.getClass().name == 'ua.av.entities.Manager'}">
                                            <input type="hidden" name="profession" value="Cleaner"/>
                                        </c:if>
                                        <button type="submit" name="editEmployeeId" value="${employee.getId()}"
                                                title="Edit employee">
                                            <img src="img/edit.png">
                                        </button>
                                    </form>
                                </li>
                                <li>
                                    <form class="deleteAction" action="deleteEmployee.html" method="POST"
                                          onsubmit="deleteConfirmation(${employee.getId()});return false;">
                                        <button type="submit" name="deleteEmployeeId" value="${employee.getId()}"
                                                title="Delete employee">
                                            <img src="img/delete.png">
                                        </button>
                                    </form>
                                <li>
                                    <form class="infoAction" action="infoAboutEmployee.html" method="POST">
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
</html>