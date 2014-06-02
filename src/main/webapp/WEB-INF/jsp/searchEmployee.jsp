<%@ include file="header.jsp" %>
<body style="background: url(img/background.jpg);">

<div id="container" style="background-color:#FAEBD7;">
    <%@ include file="logotypeAndMenu.jsp" %>

    <center style="margin-bottom:50px;margin-top:20px;overflow:auto;">
        <c:choose>
            <c:when test="${foundEmployees.size() == 0}">
                <img src="img/tryagain.png">

                <div class="failOperationContainer" style="padding-bottom:30px;">
                    No employees were found with "${lastName}" last name
                </div>

                <img style="cursor:pointer;margin-top:20px;margin-bottom:20px;  " onclick="searchAgain();"
                     src="img/backButton.png">

            </c:when>

            <c:otherwise>
                <img src="img/result.png">

                <div class="successOperationContainer">
                    <b style="color:red">${foundEmployees.size()}</b> employee(s) was (were) found with last name <b
                        style="color:red"> ${lastName}</b>
                </div>

                <div class="mainTableContainer">
                    <table class="mainTable" cellspacing="0">
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
                            <th>Lines of code</th>
                            <th>Amount of cleaned offices</th>
                            <th>Action</th>
                        </tr>

                        <c:forEach var="employee" items="${foundEmployees}">
                            <tr>
                                <td>${employee.getLastName()}</td>
                                <td>${employee.getFirstName()}</td>
                                <td>${employee.getDateOfBirth()}</td>
                                <td>${employee.getWage()}</td>
                                <td>${employee.getBonus()}</td>
                                <td>${employee.getPenalty()}</td>
                                <td>${employee.getSalary()}</td>
                                <c:if test="${employee.getClass().name == 'ua.av.entities.Manager'}">
                                    <td>${employee.getAmountOfSales()}</td>
                                    <td>${employee.getPercentageOfSales()}</td>
                                    <td> -</td>
                                    <td> -</td>
                                </c:if>

                                <c:if test="${employee.getClass().name == 'ua.av.entities.Developer'}">
                                    <td> -</td>
                                    <td> -</td>
                                    <td>${employee.getLinesOfCode()}</td>
                                    <td> -</td>
                                </c:if>

                                <c:if test="${employee.getClass().name == 'ua.av.entities.Cleaner'}">
                                    <td> -</td>
                                    <td> -</td>
                                    <td> -</td>
                                    <td>${employee.getAmountOfCleanedOffices()}</td>
                                </c:if>

                                <td>
                                    <ul class="actionButtonsMenu">
                                        <li>
                                            <form class="editActionSingleEmployee" action="editEmployee.html"
                                                  method="POST">
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
                                            <form class="deleteActionSingleButton" action="deleteEmployee.html"
                                                  method="POST"
                                                  onsubmit="deleteConfirmation(${employee.getId()});return false;">
                                                <button type="submit" name="deleteEmployeeId"
                                                        value="${employee.getId()}"
                                                        title="Delete employee">
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
            </c:otherwise>
        </c:choose>
    </center>

    <!-- Container end -->
</div>
<%@ include file="copyright.jsp" %>
<a href="#" class="scrollup"></a>
</body>
</html>