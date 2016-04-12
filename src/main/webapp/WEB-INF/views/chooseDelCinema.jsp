<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Выберите фильм</title>
</head>
<body>
<c:forEach items="${cinemaList}" var="cinema">
    <a href="/manage/cinema/choosedel/delete?cinema_id=${cinema.id}">${cinema.name}</a><br>
</c:forEach>
<a href="/manage/cinema">Назад</a><br>
</body>
</html>
