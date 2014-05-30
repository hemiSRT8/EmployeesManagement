<%@ include file="header.jsp" %>

<body style="background: url(img/background.jpg);">

<div id="container" style="background-color:#FAEBD7;height:650px;padding-bottom:15px;">

    <%@ include file="logotypeAndMenu.jsp" %>

    <form action="editEmployeeResult.html" method="POST">
        <center style="margin-top:50px;">
            <table class="editEmployeeTable" cellspacing="0"
                   style="border-left : 1px solid #51626f;border-top : 1px solid #51626f;">
                <thead>
                <tr>
                    <th colspan="2"><span style="color:red;">E</span>dit employee</th>
                </tr>
                </thead>

                <tbody>
                <tr style="display:none;">
                    <td><input type="text" name="id" value="${employee.getId()}"/></td>
                </tr>

                <tr style="font-weight:900;">
                    <td>Last Name</td>
                    <td><input pattern="^[A-z][a-z]{1,14}$" type="text" name="lastName" value=""
                               placeholder="${employee.getLastName()}"/></td>
                </tr>

                <tr>
                    <td>First Name</td>
                    <td><input pattern="^[A-z][a-z]{1,14}$" type="text" name="firstName" value=""
                               placeholder="${employee.getFirstName()}"/></td>
                </tr>

                <tr>
                    <td>Date of birthday <br> <span style="font-weight:300;">( yyyy-mm-dd )</span></td>
                    <td><input pattern="^(19|20)\d\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$"
                               type="text" name="dateOfBirth"
                               value=""
                               placeholder="${employee.getDateOfBirth()}"/>
                    </td>
                </tr>

                <tr>
                    <td>Wage</td>
                    <td><input
                            pattern="^([0-9]{0,7}?[.]?[0-9]{0,3})$"
                            type="text" name="wage" value=""
                            placeholder="${employee.getWage()}"/></td>
                </tr>

                <tr>
                    <td>Bonus</td>
                    <td><input
                            pattern="^([0-9]{0,7}?[.]?[0-9]{0,3})$"
                            type="text" name="bonus" value=""
                            placeholder="${employee.getBonus()}"/></td>
                </tr>

                <tr>
                    <td>Penalty</td>
                    <td><input
                            pattern="^([0-9]{0,7}?[.]?[0-9]{0,3})$"
                            type="text" name="penalty" value=""
                            placeholder="${employee.getPenalty()} "/></td>
                </tr>

                <tr>
                    <td>Salary</td>
                    <td><input
                            pattern="^([0-9]{0,7}?[.]?[0-9]{0,3})$"
                            type="text" name="salary" value=""
                            placeholder="${employee.getSalary()}"/></td>
                </tr>

                <c:choose>

                    <c:when test="${profession == 'manager'}">
                        <tr style="display:none;">
                            <td><input type="text" name="type" value="manager"/></td>
                        </tr>

                        <tr>
                            <td>Amount of Sales</td>
                            <td><input type="text" name="amountOfSales" value=""
                                       pattern="^([0-9]{0,7}?[.]?[0-9]{0,3})$"
                                       placeholder="${employee.getAmountOfSales()}"/>
                            </td>
                        </tr>

                        <tr>
                            <td>Percentage of sales</td>
                            <td><input type="text" name="percentageOfSales" value=""
                                       pattern="^([0-9]{0,7}?[.]?[0-9]{0,3})$"
                                       placeholder="${employee.getPercentageOfSales()}"/>
                            </td>
                        </tr>
                    </c:when>

                    <c:when test="${profession == 'developer'}">
                        <tr style="display:none;">
                            <td><input type="text" name="type" value="developer"/>
                            </td>
                        </tr>

                        <tr>
                            <td>Lines of code</td>
                            <td><input type="text" name="linesOfCode" value=""
                                       pattern="^([0-9]{1,7})$"
                                       placeholder="${employee.getLinesOfCode()}"/>
                            </td>
                        </tr>
                    </c:when>

                    <c:when test="${profession == 'cleaner'}">
                        <tr style="display:none;">
                            <td><input type="text" name="type" value="cleaner"/></td>
                        </tr>

                        <tr>
                            <td>Amount of cleaned offices</td>
                            <td><input type="text" name="amountOfCleanedOffices" value=""
                                       pattern="^([0-9]{1,7})$"
                                       placeholder="${employee.getAmountOfCleanedOffices()}"/>
                            </td>
                        </tr>
                    </c:when>
                </c:choose>
                </tbody>
            </table>

            <button type="submit" class="saveEmployeeEditResults"
                    title="Save employee">
                <img src="img/save.png">
            </button>

        </center>
        <!-- Container end -->
</div>
<%@ include file="copyright.jsp" %>
</body>
</html>