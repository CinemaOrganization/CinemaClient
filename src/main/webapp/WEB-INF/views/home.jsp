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
    <title>Кинотеатр!</title>
</head>
<body>
<h1>Добро пожаловать на сайт нашего Кинотеатра!</h1>
<h3>Выберите фильм</h3>
<table>
    <c:forEach begin="0" end="${filmList.size()/3}" var="rCount">
        <tr>
            <c:forEach begin="${rCount*3}" end="${rCount*3+2}" var="CurFilm">
                <c:if test="${CurFilm < filmList.size()}">
                    <s:url value="/session?film_id=${filmList[CurFilm].id}" var="sessions"/>
                    <td>
                        <td>
                            <a class="h1_title" href="${sessions}">
                                <img src="resources/img/films/${filmList[CurFilm].id}.jpg"
                                     width="189"
                                     height="255">
                            </a>
                        </td>
                        <td>
                            <a class="h1_title" href="${sessions}">
                                <h4> ${filmList[CurFilm].name} </h4>
                            </a>
                            <h4> Год выхода: ${filmList[CurFilm].year}</h4>
                            <h4> Продолжительность: ${filmList[CurFilm].duration}</h4>
                            <h4> Студия: ${filmList[CurFilm].studio}</h4>
                        </td>
                    </td>
                </c:if>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
<%--
<table>
    <c:forEach items="${filmList}" var="film">
        <s:url value="/session?film_id=${film.id}" var="sessions"/>
        <tr>
            <td>
                <a class="h1_title" href="${sessions}">
                    <img src="resources/img/films/${film.id}.jpg"
                         width="189"
                         height="255">
                </a>
            </td>
            <td>
                <a class="h1_title" href="${sessions}">
                    <h4> ${film.name} </h4>
                </a>
                <h4> Год выхода: ${film.year}</h4>
                <h4> Продолжительность: ${film.duration}</h4>
            </td>
        </tr>
    </c:forEach>
</table> --%>
</body>
</html>