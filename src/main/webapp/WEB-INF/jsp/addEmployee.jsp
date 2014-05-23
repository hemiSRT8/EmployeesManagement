<%@ include file="header.jsp" %>

<body style="background: url(img/background.jpg);">

<div id="container" style="background : #FAEBD7;">
 <%@ include file="logotypeAndMenu.jsp" %>

    <form action="addEmployeeResult.html" method="POST" onSubmit="return validateForm(this);">

        <center>
            <table class="addEmployee">
                <thead>
                <tr>
                    <h4>Fill in the form to add ${employeeFields.get(0)}</h4>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>First Name</td>
                    <td><input type="text" name="firstName"  value=""/></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" name="lastName"  value=""/></td>
                </tr>
                <tr>
                    <td>Date of birth [yyyy-mm-dd]</td>
                    <td><input type="text" name="dateOfBirth"
                        placeholder="yyyy-mm-dd"  value=""/></td>
                </tr>
                <tr>
                    <td>Wage</td>
                    <td><input
                    type="text" name="wage" min="0" value="0"
                    onFocus="if(this.value == '0') { this.value = '';}"
                     onBlur="if(this.value == '') { this.value = '0';}"/></td>
                </tr>
                <tr>
                    <td>Bonus</td>
                    <td><input type="text" name="bonus" min="0" value="0"
                    onFocus="if(this.value == '0') { this.value = '';}"
                    onBlur="if(this.value == '') { this.value = '0';}"/></td>
                </tr>
                <tr>
                    <td>Penalty</td>
                    <td><input type="text" name="penalty" min="0" value="0"
                    onFocus="if(this.value == '0') { this.value = '';}"
                    onBlur="if(this.value == '') { this.value = '0';}"/></td>
                </tr>
                <tr>
                    <td>Salary</td>
                    <td><input type="text" name="salary" min="0" value="0"
                    onFocus="if(this.value == '0') { this.value = '';}"
                    onBlur="if(this.value == '') { this.value = '0';}"/></td>
                </tr>

                <c:forEach begin="1" end="${employeeFields.size()-1}" var="j">

                    <tr><input type="hidden" name="type" value="${employeeFields[0]}">
                        <td>${employeeFields[j]}</td>
                        <td><input type="text" min="0" value="0"
                                   name="${employeeFields[j]}"
                                   onFocus="if(this.value == '0') { this.value = '';}"
                                   onBlur="if(this.value == '') { this.value = '0';}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div style="padding: 50px;">
                <button class="addEmployeesSubmit" type="submit"></button>
            </div>
        </center>
    </form>

    <!-- Container end -->
</div>
<%@ include file="copyright.jsp" %>
</body>
</html>