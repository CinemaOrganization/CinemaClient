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
<h1> <spring:message code="userArea.PersonalArea"/> </h1>
<h4> <spring:message code="userArea.text1"/><br>
     <spring:message code="userArea.text2"/></h4>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <a href="/manage">
        <h3><spring:message code="userArea.Panel"/></h3>
    </a>
</sec:authorize>
<table class="table table-hover">
    <tr>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <th>
                <spring:message code="userArea.user"/>
            </th>
        </sec:authorize>
        <th><spring:message code="userArea.film"/></th>
        <th><spring:message code="userArea.cinema"/></th>
        <th><spring:message code="userArea.hall"/></th>
        <th><spring:message code="userArea.date"/></th>
        <th><spring:message code="userArea.time"/></th>
        <th><spring:message code="userArea.row"/></th>
        <th><spring:message code="userArea.place"/></th>
        <th><spring:message code="userArea.cost"/></th>
        <th><spring:message code="userArea.removeBooking"/></th>
        <th><spring:message code="userArea.accept"/></th>
    </tr>
    <s:forEach items="${ticketList}" var="ticket1">
        <tr>
            <form method="POST" enctype="utf8">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <th>${ticket1.user.username}</th>
                </sec:authorize>
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
                <td><input name="remove" type="submit" value="<spring:message code="userArea.removeBooking"/>"></td>
            </form>
            <form method="POST">
                <input type="hidden" name="id" value="${ticket1.id}"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <s:if test="${ticket1.accepted}">
                      <td><input name="accept" type="submit" value="<spring:message code="userArea.accept"/>" disabled></td>
                </s:if>
                      <s:if test="${!ticket1.accepted}">
                      <td><input name="accept" type="submit" value="<spring:message code="userArea.accept"/>"></td>
                </s:if>
            </form>
        </tr>
    </s:forEach>
</table>

</body>