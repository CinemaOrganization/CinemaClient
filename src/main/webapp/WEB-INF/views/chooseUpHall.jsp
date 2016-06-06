<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Выберите зал</title>
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
                <a href="/manage/hall">Назад</a><br>
            </td>
        </tr>
    </table>
</body>
</html>
