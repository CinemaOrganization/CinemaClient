<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Выберите фильм</title>
</head>
<body>
    <table>
        <c:forEach items="${cinemaList}" var="cinema">
            <tr>
                <td>
                    <a href="/manage/cinema/chooseup/update?cinema_id=${cinema.id}">${cinema.name}</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td>
                <a href="/manage/cinema">Назад</a><br>
            </td>
        </tr>
    </table>
</body>
</html>
