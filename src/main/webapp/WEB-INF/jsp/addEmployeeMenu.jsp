<%@ include file="header.jsp" %>

<body style="background: url(img/background.jpg);">
<div id="container" style="background : #FAEBD7;">
 <%@ include file="logotypeAndMenu.jsp" %>
<div id="addEmployeeMenu">
    <center>
        <li class="addEmployeeList">
         <img src="img/manager.png">
            <form action="addEmployee.html" method="POST">
                 <button class="addEmployeeButton" type="submit" name="type" value="Manager">ADD MANAGER</button>
            </form>
        </li>

        <li class="addEmployeeList">
         <img src="img/developer.png">
             <form action="addEmployee.html" method="POST">
                 <button class="addEmployeeButton" type="submit" name="type" value="Developer">ADD DEVELOPER</button>
              </form>
        </li>

        <li class="addEmployeeList">
         <img src="img/cleaner.png">
             <form action="addEmployee.html" method="POST">
                  <button class="addEmployeeButton" type="submit" name="type" value = "Cleaner">ADD CLEANER</button>
              </form>
        </li>
    </center>
</div>
	<!-- Container end -->
</div>

<%@ include file="copyright.jsp" %>
</body>
</html>