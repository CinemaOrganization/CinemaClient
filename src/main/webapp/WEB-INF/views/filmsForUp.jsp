<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Выберите фильм</title>
</head>
<body>
    <table>
        <c:forEach items="${films}" var="film">
            <tr>
                <td>
                    <a href="/manage/session/filmsForUp/sessions?film_id=${film.id}">${film.name}</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td>
                <a href="/manage/session">Назад</a><br>
            </td>
        </tr>
    </table>
</body>
</html>
