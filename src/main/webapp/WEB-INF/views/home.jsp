<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
    <link href="/resources/css/style.css" type="text/css" rel="stylesheet"/>
    <link href="/resources/css/datepicker.css" type="text/css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <%--<script src="/resources/js/datemultipicker.js"></script>--%>
    <style>
        .dp-highlight .ui-state-default {
            background: #0099ff;
            color: #FFF;
        }

        .ui-datepicker.ui-datepicker-multi {
            width: 100% !important;
        }

        .ui-datepicker-multi .ui-datepicker-group {
            float: none;
        }

        #datepicker {
            height: 250px;
            overflow-x: scroll;
        }

        .ui-widget {
            font-size: 70%
        }
    </style>

    <title>Кинотеатр!</title>
</head>
<body>
<h1>Добро пожаловать на сайт нашего Кинотеатра!</h1>
<form>
    Когда бы Вы хотели посетить наш кинотеатр:
    <input type="hidden" id="date1" name="date1">
    <input type="hidden" id="date2" name="date2">
    <div class="datepicker"></div>
    <input type="submit" value="Выбрать">
</form>
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
                            <img src="resources/img/films/${filmList[CurFilm].imageId}.jpg"
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
</body>
<script type="text/javascript">
    $(".datepicker").datepicker({
        monthNames: ["Январь", "Февраль", "Март", "Апрель", "Май",
            "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],
        dayNamesMin: ["Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"],
        firstDay: 1,
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