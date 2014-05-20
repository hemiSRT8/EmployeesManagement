<%@ include file="header.jsp" %>

<body style="background: url(img/background.jpg);">

<div id="container" style="background-color:#FAEBD7;height:450px;overflow:auto;">

    <%@ include file="logotypeAndMenu.jsp" %>

    <ul class="buttonsForDepartmentsInfo">
        <li><button onClick=show2_onclick() style="font-weight:900;cursor: pointer;float:left;">
            Show all departments </button>
        </li>
        <li><button onClick=show1_onclick() style="font-weight:900;cursor: pointer;float:left;margin-left:20px;">
            Show departments which have employees </button>
        </li>
    </ul>

<center>

   <div id="departmentsFullInfoContainer" style="display:none;">
        <table class="departmentsTable" cellspacing="0" style="border-left : 1px solid #51626f;border-top : 1px solid #51626f;">
            <tr>
                <th> <span style="color:red;">D</span>epartment name </th>
                <th> <span style="color:red;">A</span>mount of employess </th>
                <th> <span style="color:red;">A</span>ction </th>
            </tr>

           <c:forEach var="entry" items="${departmentsMap}">
                <tr>
                    <td> ${entry.key} </td>
                    <td> ${entry.value.size()} </td>
                    <td id="editActionButton">
						<div id="editButton">
							<button type = "submit" name = "oldDepartmentName" value = "${entry.key}" title="Edit employee"
							onClick="editField_onclick();">
								<img src="img/edit.png">
							</button>
						</div>
						
						<form class="editAction" action="editDepartment.html" method="POST">
							<div id = "editField" style="display:none;">
								<input type="hidden" name="oldDepartmentName" value="${entry.key}">
								<input type="text" name="newDepartmentName" value="" placeholder= "New name">
								<button type="submit"> Submit </button>
							</div>
						</form>
	   
						<div id="deleteButton">
							<form class="deleteAction" action="deleteDepartment.html" method="POST"
							onsubmit="deleteConfirmation(${entry.key});return false;">

								<button type = "submit" name = "departmentName" value = "${entry.key}" title="Delete employee">
									<img src="img/delete.png">
								</button>
							</form>
						</div>
                    </td>
                </tr>
           </c:forEach>

        </table>
   </div>

   <div id="departmentsOnlyNamesContainer" style="display:none;">
           <table class="departmentsTable" cellspacing="0" style="border-left : 1px solid #51626f;border-top : 1px solid #51626f;">
               <tr>
                   <th> <span style="color:red;">D</span>epartment name </th>
                   <th> <span style="color:red;">A</span>ction </th>
               </tr>

              <c:forEach var="department" items="${departmentsNamesOnly}" varStatus="index">
                <tr>
                    <td>
						${department.getName()}
					</td>
                    <td id="editActionButton">

						<div id="${index.count}" style="display:none;">
							<form class="editAction" action="editDepartment.html" method="POST">
								<input type="hidden" name="oldDepartmentName" value="${department.getName()}">
								<input type="text" name="newDepartmentName" value="" placeholder= "New name">
								<button type="submit"> Submit </button>
							</form>
						</div>

						<div id="editButton">
							<button type = "submit" name = "oldDepartmentName" value = "${department.getName()}" title="Edit employee" onclick="return editField_onclick(${index.count});">
								<img src="img/edit.png">
							</button>
						</div>
							
						<div id="deleteButton">
							<form class="deleteAction" action="deleteDepartment.html" method="POST"
							onsubmit="deleteConfirmation(${entry.key});return false;">

								<button type = "submit" name = "departmentName" value = "${department.getName()}" title="Delete employee">
									<img src="img/delete.png">
								</button>
							</form>
						</div>
                    </td>
                </tr>
           </c:forEach>

           </table>
      </div>
</center>

	<!-- Container end -->
</div>
<%@ include file="copyright.jsp" %>
</body>
</html>