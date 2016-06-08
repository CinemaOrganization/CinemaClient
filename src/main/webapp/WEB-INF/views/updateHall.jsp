<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Изменение зала</title>
</head>
<body>
<sec:form modelAttribute="hall" method="POST" acceptCharset="UTF-8" enctype="utf-8">
    <sec:input path="id" type="hidden"/>
    <table>
        <tr>
            <td>Введите номер зала:</td>
        </tr>
        <tr>
            <td>
                <sec:input path="number" type="number" min="0" required = "true"/>
                <sec:errors path="number" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>Введите количесвто рядов:</td>
        </tr>
        <tr>
            <td>
                <sec:input path="rows" type="number" min="1" required = "true"/>
                <sec:errors path="rows" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>Введите количеств мест в ряду:</td>
        </tr>
        <tr>
            <td>
                <sec:input path="numberInRows" type="number" min="1" required = "true"/>
                <sec:errors path="numberInRows" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>Введите тип зала 3d:</td>
        </tr>
        <tr>
            <td>
                <sec:checkbox path="threeD"/>
                <sec:errors path="threeD" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>Выберите кинотеатр:</td>
        </tr>
        <tr>
            <td>
                <sec:select path="cinema.id">
                    <c:forEach items="${cinemaList}" var="cinemaFormList">
                        <c:choose>
                            <c:when test="${cinema.id eq cinemaFormList.id}">
                                <sec:option selected="true" value="${cinemaFormList.id}">${cinemaFormList.name}</sec:option>
                            </c:when>
                            <c:otherwise>
                                <sec:option value="${cinemaFormList.id}">${cinemaFormList.name}</sec:option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </sec:select>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Изменить">
                <c:if test="${not empty ExistedHallError}">
                    <span class="error">Данный зал уже существует</span>
                </c:if>
            </td>
        </tr>
        <tr>
            <td>
                <a href="/manage/hall">Назад</a>
            </td>
        </tr>
    </table>
</sec:form>
</body>
</html>
