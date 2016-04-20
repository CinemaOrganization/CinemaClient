<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Создание зала</title>
</head>
<body>
    <form action="/manage/hall/add/create" method="post" id="form">
        Введите номер зала:<br>
        <input type="text" name="hall_number"/><br>
        Введите количесвто рядов:<br>
        <input type="text" name="rows"/><br>
        Введите количеств мест в ряду:<br>
        <input type="text" name="numberInRow"/><br>
        Введите тип кинотеатра 3d:<br>
        <input type="checkbox" name="3d"/><br>
        Выберите кинотеатр:<br>
        <select name="cinema_id" \>
            <c:forEach items="${cinemaList}" var="cinema">
                <option value="${cinema.id}">${cinema.name}</option>
            </c:forEach>
        </select><br>
        <input type="submit" value="Создать"><br>
    </form>
    <a href="/manage/hall">Назад</a><br>
</body>
</html>
