<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
    <spring:url value="/resources/css/style.css" var="crunchifyCSS" />
    <link href="${crunchifyCSS}" type="text/css" rel="stylesheet" />
    <title>Регистрация</title>
</head>
<body>
<H1>
    Регистрация нового пользователя.
</H1>
<form:form modelAttribute="user" method="POST" acceptCharset="UTF-8" enctype="utf-8">
    <br>
    <table>
        <tr>
            <td>Введите псевдоним:</td>
            <td><form:input path="username" value=""/></td>
            <td><form:errors path="username" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Введите имя:</td>
            <td><form:input path="firstName" value=""/></td>
            <td><form:errors path="firstName" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Введите фамилию:</td>
            <td><form:input path="lastName" value=""/></td>
            <td><form:errors path="lastName" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Введите свой email адрес:</td>
            <td><form:input path="email" value=""/></td>
            <td><form:errors path="email" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Введите пароль:</td>
            <td><form:input path="password" value="" type="password"/></td>
            <td><form:errors path="password" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Повторите пароль:</td>
            <td><form:input path="matchingPassword" value="" type="password"/></td>
            <td><form:errors cssClass="error"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input name="submit" type="submit" value="Зарегистрировать"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>