<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
    <link href="/resources/css/style.css" type="text/css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <link href="/resources/css/home.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<h1><s:message code="home.welcome"/></h1>
<span style="float: right">
    <a href="?lang=en">en</a>
    |
    <a href="?lang=ru">ru</a>
</span>
<form>
    <s:message code="home.chooseDates"/>
    <input type="hidden" id="date1" name="date1">
    <input type="hidden" id="date2" name="date2">
    <div class="datepicker"></div>
    <input type="submit" value="<s:message code="home.button.choose"/>">
</form>
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
<script type="text/javascript">
    $(".datepicker").datepicker({
        <c:if test="${pageContext.response.locale eq 'ru'}">
        monthNames: ["Январь", "Февраль", "Март", "Апрель", "Май",
            "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],
        dayNamesMin: ["Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"],
        firstDay: 1,
        </c:if>
        dateFormat: "yy-mm-dd",
        minDate: new Date(),
        beforeShowDay: function (date) {
            var date1 = $.datepicker.parseDate("yy-mm-dd", $("#date1").val());
            var date2 = $.datepicker.parseDate("yy-mm-dd", $("#date2").val());
            return [true, date1 && ((date.getTime() == date1.getTime())
            || (date2 && (date >= date1 && date <= date2
            || date >= date2 && date <= date1))) ? "dp-highlight" : ""];
        },
        onSelect: function (dateText, inst) {
            var date1 = $.datepicker.parseDate("yy-mm-dd", $("#date1").val());
            var date2 = $.datepicker.parseDate("yy-mm-dd", $("#date2").val());
            if (!date1 || date2) {
                $("#date1").val(dateText);
                $("#date2").val("");
                $(this).datepicker();
            } else {
                $("#date2").val(dateText);
                $(this).datepicker();
            }
        }
    });
</script>
</html>