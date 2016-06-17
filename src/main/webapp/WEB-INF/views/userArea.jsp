<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link href="/resources/css/style.css" type="text/css" rel="stylesheet"/>
    <link href="/resources/css/tables.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<h1> <spring:message code="userArea.PersonalArea"/> </h1>
<h4> <spring:message code="userArea.text1"/><br>
     <spring:message code="userArea.text2"/></h4>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <a href="/manage">
        <h3><spring:message code="userArea.Panel"/></h3>
    </a>
</sec:authorize>
<table class="simple-little-table">
    <tr>
        <th><spring:message code="userArea.film"/></th>
        <th><spring:message code="userArea.cinema"/></th>
        <th><spring:message code="userArea.hall"/></th>
        <th><spring:message code="userArea.time"/></th>
        <th><spring:message code="userArea.row"/></th>
        <th><spring:message code="userArea.place"/></th>
        <th><spring:message code="userArea.cost"/></th>
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