<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <spring:url value="/resources/css/style.css" var="crunchifyCSS" />
    <link href="${crunchifyCSS}" type="text/css" rel="stylesheet" />
    <title>Регистрация</title>
</head>
<body>
<h1>
    Регистрация завершена!
</h1>
<h3>Теперь вы можете осуществить вход!</h3>

<a href="<c:url value="/" />">go to the Home Page...</a>
</body>
</html>
