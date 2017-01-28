<%@ page import="ua.com.tervola.jdbc.model.Employee" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Caffee</title>
</head>
<body>
<h1>Result is:</h1>

<table border="1" width="300">
    <caption align="left">Result select command from table:</caption>
    <c:forEach items="${command_result}" var="row" varStatus="loop">

        <tr>
            <td> ${loop.index} </td>
            <c:forEach items="${row}" var="colums" varStatus="loop">
                <td>
                        ${colums}
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
<%@include file="footer_back_menu.jsp" %>
<%@include file="footer_copyright.jsp" %>
</body>
</html>