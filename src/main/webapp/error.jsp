<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Caffee - Error!</title>
</head>
<body>

<table border="1" width="500" cellpadding="10">
        <tr>
            <td width="100">
                Error Description:
            </td>
            <td>
                <c:out value="${error}"/>
            </td>
        </tr>

</table>

<p>You can go to <a href="coffee">main Page</a>
    <%@include file="footer_copyright.jsp" %>
</body>
</html>