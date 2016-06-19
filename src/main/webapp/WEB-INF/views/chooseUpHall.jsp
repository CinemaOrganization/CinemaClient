<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<html>
<head>

</head>
<body>
    <table>
        <c:forEach items="${hallList}" var="hall">
            <tr>
                <td>
                    <a href="/manage/hall/chooseup/update?hall_id=${hall.id}" >${hall.cinema.name} ${hall.number}</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td>
                <a href="/manage/hall"><sp:message code="manage.GoBack"/></a><br>
            </td>
        </tr>
    </table>
</body>
</html>
