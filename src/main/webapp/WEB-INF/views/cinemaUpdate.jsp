<%@ taglib prefix="sec" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Измените кинотеатр</title>
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
                <sec:input path="id" type="hidden"/>
                <td><input name="submit" type="submit" value="Изменить"/></td>
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
