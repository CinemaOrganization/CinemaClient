<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link href="/resources/css/style.css" type="text/css" rel="stylesheet"/>
    <link href="/resources/css/tables.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<h1> Личный кабинет </h1>
<h4> Это Ваш личный кабинет. Здесь Вы можете просматривать<br>забронированные Вами билеты в наш кинотеатр!</h4>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <a href="/manage">
        <h3>Перейти в панель управления</h3>
    </a>
</sec:authorize>
<table class="simple-little-table">
    <tr>
        <th>Фильм</th>
        <th>Кинотеатр</th>
        <th>Зал</th>
        <th>Дата сеанса</th>
        <th>Время сеанса</th>
        <th>Ряд</th>
        <th>Место</th>
        <th>Цена</th>
        <th>Снять бронь</th>
    </tr>
    <s:forEach items="${ticketList}" var="ticket1">
        <tr>
            <form method="POST" enctype="utf8">
                <td>${ticket1.film.name}</td>
                <td>${ticket1.cinema.name}</td>
                <td>${ticket1.hall.number}</td>
                <td>${ticket1.session.date}</td>
                <td>${ticket1.session.time}</td>
                <td>${ticket1.row}</td>
                <td>${ticket1.number}</td>
                <td>${ticket1.session.cost}</td>
                <input type="hidden" name="id" value="${ticket1.id}"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <td><input name="remove" type="submit" value="Удалить"></td>
            </form>
        </tr>
    </s:forEach>
</table>

</body>