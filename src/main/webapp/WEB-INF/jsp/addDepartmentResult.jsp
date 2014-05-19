<%@ include file="header.jsp" %>

<body style="background: url(img/background.jpg);" onload="return departmentAddValidation(${result})">

<div id="container" style="background-color:#FAEBD7;height:380px;">

   <%@ include file="logotypeAndMenu.jsp" %>

    <center style="font-weight:900;color:green;font-family:cuprum;font-size:20px;margin-top:35px;">
            Add department<br><br>
            <form action="addDepartmentResult.html" method="POST" onSubmit="return addDepartmentValidation(this);">
                <input style="width:200px;height:30px;" type="text" name="departmentName" value="" placeholder= "Name of the department"/>
                <br><br>
              <button type="submit" class="addDepartmentButton"></button>
            </form>
    </center>


	<!-- Container end -->
</div>
<%@ include file="copyright.jsp" %>
</body>
</html>