<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Афиша!</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="resources/css/style.css" />">
</head>
<body>
<h1>Let's watch something...</h1>
<c:forEach items="${filmList}" var="film">
    <li>
        <c:out value="Film name: "/>
        <a class="h1_title" href="<c:url value="/session?film_id=${film.id}"/>">
            <c:out value="${film.name}"/></a>
        <div class="content">
            <c:out value="Film duration: ${film.duration}"/>
        </div>
        <br>
    </li>
</c:forEach>

<a href="<c:url value="/" />">go to the Home Page...</a>
</body>
</html>
