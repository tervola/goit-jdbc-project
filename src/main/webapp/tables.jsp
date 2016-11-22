<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Caffee</title>
</head>
<body>
Hi All,</br> Coming soon!!

<table border="1" width="100" cellpadding="10">
    <c:forEach items="${result}" var="name" varStatus="loop">
    <tr>
        <td>
            ${loop.index+1}
        </td>
        <td>
            ${name}
        </td>
    </tr>
    </c:forEach>
</table>
<%@include file="footer_back_menu.jsp" %>
<%@include file="footer_copyright.jsp" %>
</body>
</html>
