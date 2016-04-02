<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Affiche our Cinema</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="resources/css/style.css" />">
</head>
<body>
<h1>Let's watch something...</h1>
<c:forEach items="${filmList}" var="film">
    <li>
        <div class="h1_title">
            <c:out value="${film.name}"/>
        </div>
        <div class="content">
            <c:out value="${film.length}"/>
        </div>
        <br>
    </li>
</c:forEach>

<a href="<c:url value="/" />">to to the Home Page...</a>
</body>
</html>
