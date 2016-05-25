<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Выберите фильм</title>
</head>
<body>

    <c:forEach items="${filmList}" var="film">
        <a href="/manage/film/chooseup/update?film_id=${film.id}">${film.name}</a><br>
    </c:forEach>
    <a href="/manage/film">Назад</a><br>
</body>
</html>
