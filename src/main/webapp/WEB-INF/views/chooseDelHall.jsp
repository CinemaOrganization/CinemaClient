<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Выберите зал</title>
</head>
<body>
    <c:forEach items="${hallList}" var="hall">
        <a href="/manage/hall/choosedel/delete?hall_id=${hall.id}" >${hall.cinema.name} ${hall.number}</a><br>
    </c:forEach>
    <a href="/manage/hall">Назад</a><br>
</body>
</html>
