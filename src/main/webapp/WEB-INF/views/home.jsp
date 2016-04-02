<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Home Page our Cinema</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="resources/css/style.css" />" >
</head>
<body>
<h1>Welcome to the Cinema!</h1>
<a href="<c:url value="/affiche" />">Affiche</a>
</body>
</html>