<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <spring:url value="/resources/css/style.css" var="crunchifyCSS"/>
    <link href="${crunchifyCSS}" type="text/css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
</head>
<body>
<table>
    <tr>
        <td><img src="resources/img/films/${film.id}.jpg" width="189" height="255"></td>
        <td>
            <h1> ${film.name} </h1>
            <h4> Студия: ${film.studio}</h4>
            <h4> Год выхода: ${film.year}</h4>
            <h4> Продолжительность: ${film.duration}</h4>
            <h4> Описание: ${film.description}</h4>
        </td>
    </tr>
</table>


<h1>Сеансы на фильм <c:out value="${film.name}"/> на <c:out value="${sessionList[0].date}"/></h1>
<a href="/manage/session/filmsForDel">Назад</a>
<div>
    <form action="">
        <input type="hidden" name="film_id" value="<c:out value="${param.film_id}"/>"><br/>
        Выберите дату сеанса: <input type="text" id="datepicker" name="strDate" readonly/>
        <input type="submit" value="Посмотреть сеансы на выбранную дату">
    </form>
</div>
<table>
    <tr>
        <th>Кинотеатр</th>
        <th>Сеансы</th>
    </tr>
    <c:forEach items="${cinemaList}" var="cinema">
        <tr>
            <td><c:out value="${cinema.name}"/></td>
            <td>
                <table>
                    <tr>
                        <th>Зал</th>
                    </tr>
                    <c:forEach items="${hallList}" var="hall">
                        <c:if test="${hall.cinema == cinema}">
                            <tr>
                                <td><c:out value="${hall.number}"/>
                                    <c:if test="${hall.threeD}">
                                        <br>3d
                                    </c:if>
                                </td>
                                <c:forEach items="${sessionList}" var="session">
                                    <c:if test="${session.hall == hall}">
                                        <td>
                                            <a href="/manage/session/filmsForDel/sessions/delete?session_id=${session.id}">
                                                <c:out value="Время: ${session.time}"/>
                                            </a>
                                            <br>
                                            <c:out value="${session.cost}р."/>
                                                <%--<c:set var="film" value="${session.film}"/>--%>
                                        </td>
                                        <c:remove var="session"/>
                                    </c:if>
                                </c:forEach>
                                <c:remove var="hall"/>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </c:forEach>
</table>

</body>

<script type="text/javascript">
    $(document).ready(function () {
        var stringDateList = "${dateList}";
        stringDateList = stringDateList.substring(1, stringDateList.length - 1);
        stringDateList = stringDateList.split(', ');

        $("#datepicker").datepicker({
            monthNames: ["Январь", "Февраль", "Март", "Апрель", "Май",
                "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],
            dayNamesMin: ["Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"],
            firstDay: 1,
            dateFormat: "yy-mm-dd",
            minDate: new Date()
        });

    });
</script>
</html>
