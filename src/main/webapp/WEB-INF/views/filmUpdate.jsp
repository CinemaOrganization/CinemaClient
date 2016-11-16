<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<html>
<head>

</head>
<body>
    <form:form modelAttribute="film" method="POST" acceptCharset="UTF-8" class="form form-inline" enctype="multipart/form-data">
        <table>
            <tr>
                <td><sp:message code="manage.film.update.name"/></td>
            </tr>
            <tr>
                <td>
                    <form:input path="name"/>
                    <form:errors path="name" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td><sp:message code="manage.film.update.studio"/></td>
            </tr>
            <tr>
                <td>
                    <form:input path="studio"/>
                    <form:errors path="studio" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td><sp:message code="manage.film.update.time"/></td>
            </tr>
            <tr>
                <td>
                    <form:input path="duration"  value="${film.duration}"/>
                    <form:errors path="duration"  cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td><sp:message code="manage.film.update.year"/></td>
            </tr>
            <tr>
                <td>
                    <c:set var="yearHasBindError">
                        <form:errors path="year"/>
                    </c:set>
                    <form:input  path="year" type="number" min="1"/>
                    <c:if test="${not empty yearHasBindError}">
                        <span class="error"><sp:message code="manage.film.update.year.err"/></span>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td><sp:message code="manage.film.update.description"/></td>

            </tr>
            <tr>
                <td>
                    <form:textarea path="description" cols="50" rows="7"/>
                    <form:errors path="description" cssClass="error"/>
                </td>
            </tr>
            <tr>
                 <td>
                    <input name="file" type="file"/>
                 </td>
            </tr>
            <tr>
                <form:input path="id" type="hidden"/>
                <td>
                    <input name="submit" type="submit" value="<sp:message code="manage.film.update.button.submit"/>"/>
                    <c:if test="${not empty filmExistError}">
                        <span class="error"><sp:message code="manage.film.update.submit.err"/></span>
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
