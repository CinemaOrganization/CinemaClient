<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Изменение зала</title>
</head>
<body>
<form action="/manage/hall/chooseup/change/update" method="post" >
    <input type="hidden" name="hall_id" value="${hall.id}">
    Измените номер зала:<br>
    <input type="text" name="hall_number" value="${hall.number}"/><br>
    Измените количесвто рядов:<br>
    <input type="text" name="rows" value="${hall.rows}"/><br>
    Измените количеств мест в ряду:<br>
    <input type="text" name="numberInRow" value="${hall.numberInRows}"/><br>
    измените тип кинотеатра 3d:<br>
    <input type="checkbox" name="3d" value="${hall.threeD}"/><br>
    Измените кинотеатр:<br>
    <select name="cinema_id"\>
        <c:forEach items="${cinemaList}" var="cinema">
    <%--        <c:if test="${hall.cinema eq cinema}">
                <option selected value="${cinema.id}">${cinema.name}</option>
            </c:if> --%>
            <option value="${cinema.id}">${cinema.name}</option>
        </c:forEach>
    </select><br>
    <input type="submit" value="Изменить"><br>
</form>
<a href="/manage/hall">Назад</a><br>
</body>
</html>
