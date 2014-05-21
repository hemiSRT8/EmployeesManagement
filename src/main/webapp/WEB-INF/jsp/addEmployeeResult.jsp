<%@ include file="header.jsp" %>

<body style="background: url(img/background.jpg);">

<div id="container" style="background : #FAEBD7;">
 <%@ include file="logotypeAndMenu.jsp" %>

    <center>
        <div id="employeesImg"> <img src="img/employees.png"> </div>
        <div id="signboard">Manage employees</div>
    </center>
    <p>
        ${addResult > 0
                ?
                "<div class = \"successOperationContainer\" style=\"display:block;\"> Employee was added successfully </div>"
                :
                "<div class = \"failOperationContainer\" style=\"display:block;\"> Error was occurred. Try again. </div>"}
    </p>

    <!-- Container end -->
</div>

<%@ include file="copyright.jsp" %>
</body>
</html>