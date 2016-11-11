<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
<form:form action="./create?${_csrf.parameterName}=${_csrf.token}" modelAttribute="film" method="POST" acceptCharset="UTF-8" class="form form-inline" enctype="multipart/form-data">
    <table>
        <tr>
            <td><sp:message code="manage.film.create.name"/></td>
        </tr>
        <tr>
            <td>
                <form:input type="text" path="name"/>
                <form:errors path="name" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td><sp:message code="manage.film.create.studio"/></td>
        </tr>
        <tr>
            <td>
                <form:input type="text" path="studio"/>
                <form:errors path="studio" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td><sp:message code="manage.film.create.time"/></td>
        </tr>
        <tr>
            <td>
                <form:input path="duration" value="${film.duration}"/>
                <form:errors path="duration" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td><sp:message code="manage.film.create.year"/></td>
        </tr>
        <tr>
            <td>
                <c:set var="yearHasBindError">
                    <form:errors path="year"/>
                </c:set>
                <form:input path="year" type="number" min="1"/>
                <c:if test="${not empty yearHasBindError}">
                    <span class="error"><sp:message code="manage.film.create.year.err"/></span>
                </c:if>
            </td>
        </tr>
        <tr>
            <td><sp:message code="manage.film.create.description"/></td>

        </tr>
        <tr>
            <td>
                <form:textarea path="description" type="textarea" cols="50" rows="7"/>
                <form:errors path="description" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>
                <input name="file" type="file"/>
            </td>
        </tr>
        <tr>
            <td>
                <input name="submit" type="submit" value="<sp:message code="manage.film.create.button.submit"/>"/>
                <c:if test="${not empty filmExistError}">
                    <span class="error"><sp:message code="manage.film.create.submit.err"/></span>
                </c:if>
            </td>
        </tr>
        <tr>
            <td>
                <a href="/manage/film"><sp:message code="manage.GoBack"/></a>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
