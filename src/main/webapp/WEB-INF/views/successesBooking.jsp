<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <spring:url value="/resources/css/style.css" var="crunchifyCSS"/>
    <link href="${crunchifyCSS}" type="text/css" rel="stylesheet"/>
</head>
<body>
 <h1> <spring:message code="booking.success"/></h1>
</body>