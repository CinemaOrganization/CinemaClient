<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
</head>
<body>
    <form:form modelAttribute="film" method="POST" acceptCharset="UTF-8" enctype="utf-8">
        <table>
            <tr>
                <td>Введите название фильма:</td>
            </tr>
            <tr>
                <td>
                    <form:input path="name" value=""/>
                    <form:errors path="name" element="div"/>
                </td>
            </tr>
            <tr>
                <td>Введите название студии:</td>
            </tr>
            <tr>
                <td>
                    <form:input path="studio" value=""/>
                    <form:errors path="studio" element="div"/>
                </td>
            </tr>
            <tr>
                <td>Введите продолжительность фильма:</td>
            </tr>
            <tr>
                <td>
                    <form:input path="duration" value=""/>
                    <form:errors path="duration" element="div"/>
                </td>
            </tr>
            <tr>
                <td>Введите год выпуска фильма:</td>
            </tr>
            <tr>
                <td>
                    <form:input path="year" value=""/>
                    <form:errors path="year" element="div"/>
                </td>
            </tr>
            <tr>
                <td>Введите описание фильма:</td>

            </tr>
            <tr>
                <td>
                    <form:textarea path="description" element="div" cols="50" rows="7"/>
                    <form:errors path="description" element="div"/>
                </td>
            </tr>
            <tr>
                <td><input name="submit" type="submit" value="Создать"/></td>
            </tr>
            <tr>
                <td>
                    <a href="/manage/film">Назад</a>
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
