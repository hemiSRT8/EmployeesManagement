<%@ include file="header.jsp" %>

<body style="background: url(img/background.jpg);">

<div id="container" style="background : #FAEBD7;height:780px;overflow:auto;">

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
                    <button type="button" class="cancelAddEmployeesToDepartmentButton"
                            onclick="cancelAddEmployeesToDepartmentOperation();"></button>
            </div>

            <select required name="department" multiple style="height:200px;margin-left:30px;width:160px;">
                <c:forEach items="${departments}" var="department">
                    <option value="${department.getName()}"> ${department.getName()} </option>
                </c:forEach>
            </select>
        </div>

        <div style="float:right;margin-left:50px;border-left: 1px dotted grey;">
            <div style="padding-left:100px;padding-right:70px;padding-bottom:15px;overflow:auto;">
                <span style="font-weight:900;"><span style="color:red;">E</span>mployees</span>
                <br>
                (select employee which you want to add into the department)

                <br><br><br>

                <table class="addEmployeesToDepartmentTable" cellspacing="0"
                       style="border-left : 1px solid #51626f;border-top : 1px solid #51626f;">
                    <tr>
                        <th>Profession</th>
                        <th>Last name</th>
                        <th>First name</th>
                        <th>Date of birth</th>
                        <th>Departments</th>
                        <th style="font-weight:900;color:blue;">Select</th>
                    </tr>
                    <c:forEach var="employee" items="${employees}">
                        <tr>
                            <c:choose>
                                <c:when test="${employee.getClass().name == 'ua.av.entities.Manager'}">
                                    <td> Manager</td>
                                </c:when>
                                <c:when test="${employee.getClass().name == 'ua.av.entities.Developer'}">
                                    <td> Developer</td>
                                </c:when>
                                <c:when test="${employee.getClass().name == 'ua.av.entities.Cleaner'}">
                                    <td> Cleaner</td>
                                </c:when>
                            </c:choose>
                            <td>${employee.getLastName()}</td>

                            <td>${employee.getFirstName()}</td>
                            <td>${employee.getDateOfBirth()}</td>
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
                            <td>
                                <input type="checkbox" name="employeeId" value="${employee.getId()}">
                            </td>
                        </tr>
                    </c:forEach>
                    </form>
                </table>
            </div>
        </div>

        <!-- Container end -->
</div>
</center>
<%@ include file="copyright.jsp" %>
<a href="#" class="scrollup"></a>
</body>