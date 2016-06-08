<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Создание нового кинотеатра</title>
</head>
<body>
    <sec:form modelAttribute="cinema" method="POST" acceptCharset="UTF-8" enctype="utf-8">
        <table>
            <tr>
                <td>Введите название кинотеатра:</td>
            </tr>
            <tr>
                <td>
                    <sec:input path="name"/>
                    <sec:errors path="name" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input name="submit" type="submit" value="Создать"/>
                    <c:if test="${not empty existedCinemaError}">
                        <span class="error">Данный кинотеатр уже существует</span>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>
                    <a href="/manage/cinema">Назад</a>
                </td>
            </tr>
        </table>
    </sec:form>
</body>
</html>
