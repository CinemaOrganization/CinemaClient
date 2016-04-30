<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Создание нового сеанса</title>
</head>
<body>
    <form action="/manage/session/add/create" method="post">
        Выберите фильм:<br>
        <select name="film_id" \>
            <c:forEach items="${films}" var="film">
                <option value="${film.id}">${film.name}</option>
            </c:forEach>
        </select><br>
        Выберите зал:<br>
        <select name="hall_id" \>
            <c:forEach items="${halls}" var="hall">
                <option value="${hall.id}">${hall.number} ${hall.cinema.name}</option>
            </c:forEach>
        </select><br>
        Введите стоимость:<br>
        <input type="text" name="cost"><br>
        Введите время начала:<br>
        <input type="datetime" name="time"><br>
        Введите дату:<br>
        <input type="date" name="date"><br>
        <input type="submit" name="Создать"><br>
    </form>
    <a href="/manage/session">Назад</a>
    <br>
</body>
</html>