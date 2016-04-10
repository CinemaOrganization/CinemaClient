<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Places</title>
</head>
<body>
    <c:forEach items="${hallList}" var="hall">
        <h1> Зал № <c:out value = "${hall.number}"/> </h1>
        <br>
        <table>
            <c:forEach items="${hall.placeSet}" var="place">
            <tr>
                <td> <c:out value="${place.number}"/> </td>
            </tr>
            </c:forEach>
        </table>
    </c:forEach>
</body>
</html>
