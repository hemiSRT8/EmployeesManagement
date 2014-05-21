<%@ include file="header.jsp" %>

<body style="background: url(img/background.jpg);">

<div id="container" style="background-color:#FAEBD7;height:600px;padding-bottom:15px;">

   <%@ include file="logotypeAndMenu.jsp" %>

  <form action="editEmployeeResult.html" method="POST" onSubmit="return editFormDateOfBirthValidation(this)">
    <center  style="margin-top:50px;">
        <table class="editEmployeeTable" cellspacing="0" style="border-left : 1px solid #51626f;border-top : 1px solid #51626f;">
            <thead>
                <tr>
                    <th colspan="2" > <span style="color:red;">E</span>dit employee</th>
                </tr>
            </thead>

            <tbody>
               <tr style="display:none;">
                     <td> <input type="text" name="id" value="${employee.get(0).getId()}"/> </td>
               </tr>

               <tr style="font-weight:900;">
                    <td>Last Name</td>
                    <td><input type="text" name="lastName" value="" placeholder="${employee.get(0).getLastName()}"/></td>
                </tr>

                <tr>
                    <td>First Name</td>
                    <td><input type="text" name="firstName" value="" placeholder="${employee.get(0).getFirstName()}"/></td>
                </tr>

                <tr>
                    <td>Date of birthday <br> <span style="font-weight:300;">( year / month / day )</span> </td>
                    <td><input type="text" name="dateOfBirth" value="" placeholder="${employee.get(0).getDateOfBirth()}"/> </td>
                </tr>

                <tr>
                    <td>Wage</td>
                    <td><input type="text" name="wage" value="" placeholder="${employee.get(0).getWage()}"/></td>
                </tr>

                <tr>
                    <td>Bonus</td>
                    <td><input type="text" name="bonus" value="" placeholder="${employee.get(0).getBonus()}"/></td>
                </tr>

                <tr>
                    <td>Penalty</td>
                    <td><input type="text" name="penalty" value="" placeholder="${employee.get(0).getPenalty()} "/></td>
                </tr>

                <tr>
                    <td>Salary</td>
                    <td><input type="text" name="salary" value="" placeholder="${employee.get(0).getSalary()}"/></td>
                </tr>

                <c:choose>

                      <c:when test="${profession == 'manager'}">
                        <tr style="display:none;">
                            <td><input type="text" name="type" value="manager" /></td>
                        </tr>

                        <tr>
                            <td>Amount of Sales</td>
                            <td><input type="text" name="amountOfSales" value="" placeholder="${employee.get(0).getAmountOfSales()}"/></td>
                        </tr>

                        <tr>
                            <td>Percentage of sales</td>
                            <td><input type="text" name="percentageOfSales" value="" placeholder="${employee.get(0).getPercentageOfSales()}"/></td>
                        </tr>
                      </c:when>

                      <c:when test="${profession == 'developer'}">
                         <tr style="display:none;">
                            <td><input type="text" name="type" value="developer" /></td>
                         </tr>

                        <tr>
                            <td>Lines of code</td>
                            <td><input type="text" name="linesOfCode" value="" placeholder="${employee.get(0).getLinesOfCode()}"/></td>
                        </tr>
                      </c:when>

                      <c:when test="${profession == 'cleaner'}">
                        <tr style="display:none;">
                            <td><input type="text" name="type" value="cleaner" /></td>
                        </tr>

                        <tr>
                            <td>Amount of cleaned offices</td>
                            <td><input type="text" name="amountOfCleanedOffices" value="" placeholder="${employee.get(0).getAmountOfCleanedOffices()}" /></td>
                        </tr>
                      </c:when>

                </c:choose>
            </tbody>
        </table>

        <br>
        <input type="submit" value="Submit" style="color:green; font-weight:900;"/>
        <br><br>
    </center>
	<!-- Container end -->
</div>
<%@ include file="copyright.jsp" %>
</body>
</html>