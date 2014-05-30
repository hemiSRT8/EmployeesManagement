<%@ include file="header.jsp" %>

<body style="background: url(img/background.jpg);">

<div id="container" style="background-color:#FAEBD7;height:780px;overflow:auto;">

    <%@ include file="logotypeAndMenu.jsp" %>

    <center>
        <ul class="editDepartmentMenu" style="border-bottom:1px solid grey;margin-top:30px;padding-bottom:25px;">
            <li style="width:160px;" onclick="addDepartment_onclick();"> ADD NEW DEPARTMENT</li>
            <li style="width:220px;" onclick="addEmployeesToDepartment_onclick();"> ADD EMPLOYEES TO DEPARTMENT</li>
        </ul>
    </center>

    <div id="addDepartment" style="display:none;border-bottom:1px dotted grey;padding-bottom:20px;">
        <center style="padding-top:35px;font-weight:900;color:green;font-family:cuprum;font-size:20px;">
            <form action="addDepartment.html" method="POST">

                <span style="color:black;font-weight:300;font-size:15px;font-family:none;"><i>example :
                    Logistic</i></span>

                <br>

                <input style="width:200px;height:30px;"
                       type="text" name="departmentName" value=""
                       placeholder="Name of the department"
                       required pattern="^[A-z][a-z]{1,14}$"/>
                <br><br>
                <button type="submit" class="addDepartmentButton"></button>
                <button type="button" class="cancelAddDepartmentButton" onclick="hideAddDepartmentBlock();"></button>
            </form>
        </center>
    </div>

    <div id="departmentsFullInfoContainer">
        <table class="departmentsTable" cellspacing="0"
               style="border-left : 1px solid #51626f;border-top : 1px solid #51626f;">
            <thead>
            <tr>
                <td colspan="3" style="font-weight:900;"><span style="color:red;;">D</span>epartments which have
                    employees
                </td>
            </tr>
            </thead>

            <tr>
                <th style="font-weight:500;"> Department name</th>
                <th style="font-weight:500;"> Amount of employess</th>
                <th style="font-weight:500;"> Salary expense</th>
            </tr>

            <c:forEach var="entry" items="${departmentsMap}">
                <tr>
                    <td> ${entry.key} </td>
                    <td> ${entry.value.size()} </td>
                    <td>${departmentSalaryExpense.get(entry.key)} </td>
                </tr>
            </c:forEach>

        </table>
    </div>

    <div id="departmentsOnlyNamesContainer">
        <table class="departmentsTable" cellspacing="0"
               style="border-left : 1px solid #51626f;border-top : 1px solid #51626f;">
            <tr>
                <th><span style="color:red;">A</span>ll departments</th>
                <th> Action</th>
            </tr>

            <c:forEach var="department" items="${departmentsNamesOnly}" varStatus="index">
                <tr>
                    <td>
                            ${department.getName()}
                    </td>
                    <td id="editDep">
                        <div id="${index.count}" style="display:none;position:absolute;">
                            <form class="hiddenEditField" action="editDepartment.html" method="POST">
                                <input type="hidden" name="oldDepartmentName" value="${department.getName()}">
                                <input type="text" name="newDepartmentName" value="" placeholder="New name"
                                       pattern="^[A-z][a-z]{1,14}$">
                                <button type="submit" title="Edit department">
                                    <img src="img/success.gif">
                                </button>
                                <button type="button" title="Cancel"
                                        onclick="hideEditOperationBlock(${index.count});"><img
                                        src="img/fail.png">
                                </button>
                            </form>
                        </div>

                        <div id="editActionButton">
                            <button type="submit" name="oldDepartmentName" value="${department.getName()}"
                                    title="Edit department" onclick="return hiddenEditField_onclick(${index.count});">
                                <img src="img/edit.png">
                            </button>
                        </div>

                        <div id="deleteActionButton">
                            <form class="deleteAction" action="deleteDepartment.html" method="POST"
                                  onsubmit="deleteConfirmation(${entry.key});return false;">

                                <button type="submit" name="departmentName" value="${department.getName()}"
                                        title="Delete department">
                                    <img src="img/delete.png">
                                </button>
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>

    <!-- Container end -->
</div>
<%@ include file="copyright.jsp" %>
</body>
</html>