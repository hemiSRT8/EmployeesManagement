<%@ include file="header.jsp" %>

<body style="background: url(img/background.jpg);">

<div id="container" style="background-color:#FAEBD7;height:500px;">

    <%@ include file="logotypeAndMenu.jsp" %>

    <center style="margin-bottom:50px;">
        <img src="img/search.png">

        <form action="searchEmployeeResult.html" method="POST">
            <input type="search" name="lastName" value=""
                   placeholder="Search by last name"
                   pattern="^[a-z]{1,15}$"
                   required="">
            <br><br>
            <button type="submit">I'm lucky</button>
        </form>
    </center>

    <!-- Container end -->
</div>
<%@ include file="copyright.jsp" %>
</body>
</html>