<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#datepicker").datepicker({
                monthNames: ["Январь", "Февраль", "Март", "Апрель", "Май",
                    "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],
                dayNamesMin: ["Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"],
                firstDay: 1,
                dateFormat: "yy-mm-dd",
            });

        });
    </script>
    <title>Sessions by film and cinema </title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="resources/css/style.css" />">
</head>
<body>
<h1>Сеансы на фильм <c:out value="${sessionList[0].film.name}"/> на <c:out value="${sessionList[0].date}"/></h1>
<div>
    <form action="/session">
        <input type="hidden" name="film_id" value="<c:out value="${param.film_id}"/>"><br/>
        Выберите дату сеанса: <input type="text" id="datepicker" name="strDate"/>
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
                                        <td><c:out value="Время: ${session.time}"/><br>
                                            <c:out value="${session.cost}р."/>
                                            <c:set var="film" value="${session.film}"/>
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
<a href="<c:url value="/" />">go to the Home Page...</a>
</body>
</html>
