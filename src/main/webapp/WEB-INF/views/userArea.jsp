<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <th>Время сеанса</th>
        <th>Ряд</th>
        <th>Место</th>
        <th>Цена</th>
    </tr>
    <s:forEach items="${ticketList}" var="ticket">
        <tr>
            <td>${ticket.film.name}</td>
            <td>${ticket.cinema.name}</td>
            <td>${ticket.hall.number}</td>
            <td>${ticket.session.time}</td>
            <td>${ticket.row}</td>
            <td>${ticket.number}</td>
            <td>${ticket.session.cost}</td>
        </tr>
    </s:forEach>
</table>

</body>