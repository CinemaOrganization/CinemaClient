<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
    <spring:url value="/resources/css/style.css" var="crunchifyCSS" />
    <link href="${crunchifyCSS}" type="text/css" rel="stylesheet" />
</head>
<body>
<h1><spring:message code="delete.session.err"/></h1>
<a href="/manage/session"><spring:message code="manage.GoBack"/></a>
</body>
</html>
