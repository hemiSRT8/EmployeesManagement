<%@ include file="header.jsp" %>

<body style="background: url(img/background.jpg);">

<div id="container" style="background-color:#FAEBD7;height:550px;">

    <%@ include file="logotypeAndMenu.jsp" %>

    <center style="margin-top:20px;margin-bottom:40px;">
        <img src="img/salary.png">
        <table class="salaryTable" cellspacing="0"
               style="border-left : 1px solid #51626f;border-top : 1px solid #51626f;">
            <tr style="font-size:15px;font-weight:900;font-family: cuprum;letter-spacing: 1px;word-spacing: 2px;">
                <th> <span style="color:red;">S</span>alary expense</th>
                <th> <span style="color:red;">A</span>verage salary</th>
                <th> <span style="color:red;">M</span>ax salary</th>
                <th> <span style="color:red;">M</span>in salary</th>
            </tr>

            <tr>
                <td> ${expense} </td>
                <td> ${averageSalary} </td>
                <td> ${maxSalary} </td>
                <td> ${minSalary} </td>
            </tr>
        </table>
        <center>

            <!-- Container end -->
</div>
<%@ include file="copyright.jsp" %>
</body>
</html>