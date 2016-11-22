<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Coffee</title>
</head>
<body>
<table width="600">
    <caption> Main Operations:</caption>
    <c:forEach items="${menu}" var="name" varStatus="loop">
        <tr>
            <td >
                <a href="${name}"> ${name}</a>
            </td>
        </tr>
    </c:forEach>
</table>
<%@include file="footer_copyright.jsp" %>
</body>
</html>
