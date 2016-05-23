<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
    <form:form modelAttribute="film" method="POST" acceptCharset="UTF-8" enctype="utf-8">
        <table>
            <tr>
                <td>Введите название фильма:</td>
            </tr>
            <tr>
                <td>
                    <form:input path="name"/>
                    <form:errors path="name" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td>Введите название студии:</td>
            </tr>
            <tr>
                <td>
                    <form:input path="studio"/>
                    <form:errors path="studio" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td>Введите продолжительность фильма (в формате ЧЧ:ММ):</td>
            </tr>
            <tr>
                <td>
                    <form:input path="duration"  type="time"/>
                    <form:errors path="duration"  cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td>Введите год выпуска фильма:</td>
            </tr>
            <tr>
                <td>
                    <c:set var="yearHasBindError">
                        <form:errors path="year"/>
                    </c:set>
                    <form:input  path="year" type="number" min="1"/>
                    <c:if test="${not empty yearHasBindError}">
                        <span class="error">Поле не может быть пустым</span>
                    </c:if>
                 <%--   <form:errors path="year" cssClass="error" /> --%>
                </td>
            </tr>
            <tr>
                <td>Введите описание фильма:</td>

            </tr>
            <tr>
                <td>
                    <form:textarea path="description" cols="50" rows="7"/>
                    <form:errors path="description" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td><input name="submit" type="submit" value="Создать"/></td>
            </tr>
            <tr>
                <td>
                    <a href="/manage/film">Назад</a>
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
