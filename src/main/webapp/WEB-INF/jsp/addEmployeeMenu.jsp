<%@ include file="header.jsp" %>

<body style="background: url(img/background.jpg);">

<div id="container" style="background : #FAEBD7;">
 <%@ include file="logotypeAndMenu.jsp" %>

    <center>
        <div id="employeesImg"> <img src="img/employees.png"> </div>
        <div id="signboard">Manage employees</div>
    </center>

    <ul class="professions">
        <li>
            <center>

                <form action="addEmployee.html" method="POST">
                    <button type="submit" name="type" value = "Manager">ADD MANAGER</button>
                </form>

            </center>
        </li>

        <li>
             <center>

                 <form action="addEmployee.html" method="POST">
                     <button type="submit" name="type" value = "Developer">ADD DEVELOPER</button>
                 </form>
             </center>
        </li>

        <li>
             <center>

                 <form action="addEmployee.html" method="POST">
                     <button type="submit" name="type" value = "Cleaner">ADD CLEANER</button>
                 </form>
             </center>
        </li>
    </ul>

	<!-- Container end -->
</div>

<%@ include file="copyright.jsp" %>
</body>
</html>