<%@ include file="header.jsp" %>

<body style="background: url(img/background.jpg);">

<div id="container" style="background : #FAEBD7;height:720px">

    <%@ include file="logotypeAndMenu.jsp" %>

    <form action="addEmployeeResult.html" method="POST" onSubmit="return validateForm(this);">
        <input type="hidden" name="profession" value="${profession}">

        <center>
            <div style="font-family: cuprum;font-weight:900;font-size:20px;margin-bottom:10px;margin-top:50px;">
                <span style="color:red">A</span>dd ${profession}:
            </div>

            <table class="singleEmployeeTable" cellspacing="0"
                   style="border-top: 1px solid #51626f;border-left: 1px solid #51626f;">
                <tbody>
                <tr>
                    <td>Last Name</td>
                    <td><input required pattern="^[A-Za-z]{1,15}$" type="text" name="lastName" value=""/></td>
                </tr>
                <tr>
                    <td>First Name</td>
                    <td><input required pattern="^[A-Za-z]{1,15}$" type="text" name="firstName" value=""/></td>
                </tr>
                <tr>
                    <td>Date of birthday <br> <span style="font-weight:300;">( year / month / day )</span></td>
                    <td><input pattern="^(19|20)\d\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$"
                               required
                               type="text" name="dateOfBirth"
                               value=""/>
                    </td>
                </tr>
                <tr>
                    <td>Wage</td>
                    <td><input type="text" name="wage" value="0"
                               pattern="^([0-9]{0,7}?[.]?[0-9]{0,3})$"
                               onFocus="if(this.value == '0') { this.value = '';}"
                               onBlur="if(this.value == '') { this.value = '0';}"/>
                    </td>
                </tr>
                <tr>
                    <td>Bonus</td>
                    <td><input type="text" name="bonus" value="0"
                               pattern="^([0-9]{0,7}?[.]?[0-9]{0,3})$"
                               onFocus="if(this.value == '0') { this.value = '';}"
                               onBlur="if(this.value == '') { this.value = '0';}"/>
                    </td>
                </tr>
                <tr>
                    <td>Penalty</td>
                    <td><input type="text" name="penalty" value="0"
                               pattern="^([0-9]{0,7}?[.]?[0-9]{0,3})$"
                               onFocus="if(this.value == '0') { this.value = '';}"
                               onBlur="if(this.value == '') { this.value = '0';}"/>
                    </td>
                </tr>
                <tr>
                    <td>Salary</td>
                    <td><input type="text" name="salary" min="0" value="0"
                               pattern="^([0-9]{0,7}?[.]?[0-9]{0,3})$"
                               onFocus="if(this.value == '0') { this.value = '';}"
                               onBlur="if(this.value == '') { this.value = '0';}"/>
                    </td>
                </tr>

                <c:choose>
                    <c:when test="${profession == 'Manager'}">
                        <tr>
                            <td> Amount of sales</td>
                            <td><input type="text" name="amountOfSales" min="0" value="0"
                                       pattern="^([0-9]{0,7}?[.]?[0-9]{0,3})$"
                                       onFocus="if(this.value == '0') { this.value = '';}"
                                       onBlur="if(this.value == '') { this.value = '0';}"/>
                            </td>
                        </tr>
                        <tr>
                            <td> Percentage of sales</td>
                            <td><input type="text" name="percentageOfSales" min="0" value="0"
                                       pattern="^([0-9]{0,7}?[.]?[0-9]{0,3})$"
                                       onFocus="if(this.value == '0') { this.value = '';}"
                                       onBlur="if(this.value == '') { this.value = '0';}"/>
                            </td>
                        </tr>
                    </c:when>

                    <c:when test="${profession == 'Developer'}">
                        <tr>
                            <td> Lines of code</td>
                            <td><input type="text" name="linesOfCode" min="0" value="0"
                                       pattern="^([0-9]{1,7})$"
                                       onFocus="if(this.value == '0') { this.value = '';}"
                                       onBlur="if(this.value == '') { this.value = '0';}"/>
                            </td>
                        </tr>
                    </c:when>

                    <c:when test="${profession == 'Cleaner'}">
                        <tr>
                            <td> Amount of cleaned offices</td>
                            <td><input type="text" name="amountOfCleanedOffices" min="0" value="0"
                                       pattern="^([0-9]{1,7})$"
                                       onFocus="if(this.value == '0') { this.value = '';}"
                                       onBlur="if(this.value == '') { this.value = '0';}"/>
                            </td>
                        </tr>
                    </c:when>
                </c:choose>

                </tbody>
            </table>

            <button type="submit" class="addEmployeeButton"></button>

        </center>
    </form>

    <!-- Container end -->
</div>
<%@ include file="copyright.jsp" %>
</body>
</html>