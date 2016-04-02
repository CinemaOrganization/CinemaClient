
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Sessions by film and cinema </title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="resources/css/style.css" />">
</head>
<body>
<h1>Let's choose a session...</h1>
<c:forEach items="${sessionList}" var="session">
    <li>
        <c:out value="Session:"/>
        <div class="h1_title">
            <c:out value="Cost: ${session.cost}"/>
        </div>
        <div class="content">
            <c:out value="Date and time: ${session.time}"/>
        </div>
        <br>
    </li>
</c:forEach>

<a href="<c:url value="/" />">go to the Home Page...</a>
</body>
</html>
