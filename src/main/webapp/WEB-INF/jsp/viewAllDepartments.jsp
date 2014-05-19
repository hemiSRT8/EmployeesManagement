<%@ include file="header.jsp" %>

<body style="background: url(img/background.jpg);">

<div id="container" style="background-color:#FAEBD7;height:500px;">

    <%@ include file="logotypeAndMenu.jsp" %>
<center>
   <div class="mainTableContainer">
        <table class="departmentsTable" cellspacing="0" style="border-left : 1px solid #51626f;border-top : 1px solid #51626f;">
            <tr>
                <th> <span style="color:red;">D</span>epartment name </th>
                <th> <span style="color:red;">A</span>mount of employess </th>
            </tr>

           <c:forEach var="entry" items="${departmentsMap}">
                <tr>
                    <td> <c:out value="${entry.key}"/> </td>
                    <td> <c:out value="${entry.value.size()}"/> </td>
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