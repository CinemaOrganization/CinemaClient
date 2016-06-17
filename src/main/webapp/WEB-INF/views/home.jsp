<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
    <spring:url value="/resources/css/style.css" var="crunchifyCSS"/>
    <link href="${crunchifyCSS}" type="text/css" rel="stylesheet"/>

</head>
<body>
<h1><s:message code="home.welcome"/></h1>
<span style="float: right">
    <a href="?lang=en">en</a>
    |
    <a href="?lang=ru">ru</a>
</span>
<h3><s:message code="home.chooseFilm"/></h3>
<table>
    <c:forEach begin="0" end="${filmList.size()/3}" var="rCount">
        <tr>
            <c:forEach begin="${rCount*3}" end="${rCount*3+2}" var="CurFilm">
                <c:if test="${CurFilm < filmList.size()}">
                    <s:url value="/session?film_id=${filmList[CurFilm].id}" var="sessions"/>
                    <td>
                        <td>
                            <a class="h1_title" href="${sessions}">
                                <img src="resources/img/films/${filmList[CurFilm].imageId}.jpg"
                                     width="189"
                                     height="255">
                            </a>
                        </td>
                        <td>
                            <a class="h1_title" href="${sessions}">
                                <h4> ${filmList[CurFilm].name} </h4>
                            </a>
                            <h4> <s:message code="home.year"/> ${filmList[CurFilm].year}</h4>
                            <h4> <s:message code="home.duration"/> ${filmList[CurFilm].duration}</h4>
                            <h4> <s:message code="home.studio"/> ${filmList[CurFilm].studio}</h4>
                        </td>
                    </td>
                </c:if>
            </c:forEach>
        </tr>
    </c:forEach>
</table>

</body>
</html>