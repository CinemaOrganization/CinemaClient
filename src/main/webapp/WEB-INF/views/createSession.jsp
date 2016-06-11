<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Создание нового сеанса</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
</head>
<body>
    <sec:form modelAttribute="session" method="POST" acceptCharset="UTF-8" enctype="utf-8">
        <table>
            <tr>
                <td>Выберите фильм:</td>
            </tr>
            <tr>
                <td>
                    <sec:select path="film.id">
                        <c:forEach items="${films}" var="film">
                            <sec:option value="${film.id}">${film.name} ${film.year} ${film.studio}</sec:option>
                        </c:forEach>
                    </sec:select>
                    <sec:errors path="film" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td>Выберите зал:</td>
            </tr>
            <tr>
                <td>
                    <sec:select path="hall.id">
                        <c:forEach items="${halls}" var="hall">
                            <sec:option value="${hall.id}">${hall.cinema.name} ${hall.number}</sec:option>
                        </c:forEach>
                    </sec:select>
                    <sec:errors path="hall" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td>Введите стоимость:</td>
            </tr>
            <tr>
                <td>
                    <c:set var="costHasBindError">
                        <sec:errors path="cost"/>
                    </c:set>
                    <sec:input  path="cost"/>
                    <c:if test="${not empty costHasBindError}">
                        <span class="error">Введите число >=0</span>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>Введите время начала:</td>
            </tr>
            <tr>
                <td>
                    <sec:input path="time" type="time" value="${session.time}"/>
                    <sec:errors path="time" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td>Введите дату:</td>
            </tr>
            <tr>
                <td>
                    <sec:input path="date" id="datepicker" readonly="true"/>
                    <sec:errors path="date"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input name="submit" type="submit" value="Создать"/>
                    <c:if test="${not empty ExistedSessionError}">
                        <span class="error">Данный сеанс уже существует</span>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>
                    <a href="/manage/session">Назад</a>
                </td>
            </tr>
        </table>
    </sec:form>
</body>

<script type="text/javascript">
    $(document).ready(function () {

        $("#datepicker").datepicker({
            monthNames: ["Январь", "Февраль", "Март", "Апрель", "Май",
                "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],
            dayNamesMin: ["Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"],
            firstDay: 1,
            dateFormat: "dd.mm.y",
            minDate: new Date()
        });
    });
</script>
</html>