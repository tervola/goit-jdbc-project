<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Caffee</title>
</head>
<body>
<h1>Select or modifying operations</h1>

<table border="0" width="300" cellpadding="5">
    <tr>
        <td>
            Inserting command:
        </td>
    </tr>
    <tr>
        <td>
            <form action="modifying" method="post">
                <input type="text" name="command" >
                <input type="submit" value="Run">
            </form>
        </td>
    </tr>

</table>
<%@include file="footer_back_menu.jsp" %>
<%@include file="footer_copyright.jsp" %>
</body>
</html>