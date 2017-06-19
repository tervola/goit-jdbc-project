<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Caffee</title>
</head>
<body>
<table bordercolor="black" border="5" align="center" cellpadding="50" width=50%>
    <tr>
        <td>
            <table border="0" width="100%">
                <tr align=left bgcolor="#808080">
                    <td><h2>${tableTitle}</h2></td>
                </tr>
            </table>
            <table border="1" width="100%" cellpadding="5">
                <c:forEach items="${prepared_dishes}" var="item" varStatus="loop">
                    <tr align=left>
                        <td width="5">${loop.index+1}</td>
                        <td>${item}</td>
                    </tr>
                </c:forEach>
            </table>

            <br/><br/>
        </td>
    </tr>
</table>

<%@include file="footer_back_menu.jsp" %>
<%@include file="footer_copyright.jsp" %>
</body>
</html>
