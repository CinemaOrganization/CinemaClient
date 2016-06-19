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
            <h4> <spring:message code="session.studio"/> ${film.studio}</h4>
            <h4> <spring:message code="session.year"/> ${film.year}</h4>
            <h4> <spring:message code="session.duration"/> ${film.duration}</h4>
            <h4> <spring:message code="session.description"/> ${film.description}</h4>
        </td>
    </tr>
</table>


<h1><spring:message code="session.text"/> <c:out value="${film.name}"/> на <c:out value="${sessionList[0].date}"/></h1>
<a href="/manage/session/filmsForDel"><spring:message code="manage.GoBack"/></a>
<div>
    <form action="">
        <input type="hidden" name="film_id" value="<c:out value="${param.film_id}"/>"><br/>
        <spring:message code="session.chooseDate"/> <input type="text" id="datepicker" name="strDate" readonly/>
        <input type="submit" value="<spring:message code="session.button.watchDate"/>">
    </form>
</div>
<table>
    <tr>
        <th><spring:message code="session.cinema"/></th>
        <th><spring:message code="session.sessions"/></th>
    </tr>
    <c:forEach items="${cinemaList}" var="cinema">
        <tr>
            <td><c:out value="${cinema.name}"/></td>
            <td>
                <table>
                    <tr>
                        <th><spring:message code="session.hall"/></th>
                    </tr>
                    <c:forEach items="${hallList}" var="hall">
                        <c:if test="${hall.cinema == cinema}">
                            <tr>
                                <td><c:out value="${hall.number}"/>
                                    <c:if test="${hall.threeD}">
                                        <br><spring:message code="session.3d"/>
                                    </c:if>
                                </td>
                                <c:forEach items="${sessionList}" var="session">
                                    <c:if test="${session.hall == hall}">
                                        <td>
                                            <a href="/manage/session/filmsForDel/sessions/delete?session_id=${session.id}">
                                                <spring:message code="session.time"/> ${session.time}
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
            <c:if test="${pageContext.response.locale eq 'ru'}">
                monthNames: ["Январь", "Февраль", "Март", "Апрель", "Май",
                    "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],
                dayNamesMin: ["Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"],
                firstDay: 1,
            </c:if>
            dateFormat: "yy-mm-dd",
            defaultDate: new Date(Date.parse(stringDateList[0])),
            beforeShowDay: function (date) {
                for (var i = 0; i < stringDateList.length; i = i + 1) {
                    var curDate = new Date(Date.parse(stringDateList[i]));
                    if (date.getFullYear() == curDate.getFullYear()
                            && curDate.getMonth() == date.getMonth()
                            && curDate.getDate() == date.getDate()) {
                        return [true];
                    }
                }
                return [false];
            }
        });

    });
</script>
</html>
