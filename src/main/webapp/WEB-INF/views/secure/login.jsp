<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
    <spring:url value="/resources/css/style.css" var="crunchifyCSS" />
    <link href="${crunchifyCSS}" type="text/css" rel="stylesheet" />
    <title>Sessions by film and cinema </title>
</head>
<body>
<h1><s:message code="login.Login"/></h1>
<%-------------------------------------Исправить сообщение об ошибке--------------------------------------%>
<c:if test="${not empty param.error}">
    <div class="error">
        <s:message code="login.err.login"/> <br/><br/>
    </div>
</c:if>
<%-------------------------------------Исправить сообщение об ошибке--------------------------------------%>

<form name='f' action="login" method='POST'>
    <table>
        <tr>
            <td><s:message code="login.User"/></td>
            <td><input type='text' name='username' value=''></td>
        </tr>
        <tr>
            <td><s:message code="login.Password"/></td>
            <td><input type='password' name='password' /></td>
        </tr>
        <tr>
            <td><s:message code="login.RemeberMe"/></td>
            <td><input type='checkbox' name='remember-me' /></td>
        </tr>
        <tr>
            <td></td>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <td><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>
</form>
</body>
</html>


