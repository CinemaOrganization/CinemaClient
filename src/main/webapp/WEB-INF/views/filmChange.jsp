<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Изменение фильма</title>
</head>
<body>
    <form action="/manage/film/chooseup/change/update" method="post">
        <input type="hidden" name="film_id" value="${film.id}">
        Введите название фильма:<br>
        <input type="text" name="name" value="${film.name}"/><br>
        Введите название студии:<br>
        <input type="text" name="studio" value="${film.studio}"/><br>
        Введите продолжительность фильма:<br>
        <input type="datetime" name="duration" value="${film.duration}"/><br>
        Введите год фильма:<br>
        <input type="text" name="year" value="${film.year}"><br>
        Введите описание фильма:<br>
        <textarea cols="50" rows="7" name="description">${film.description}</textarea><br>
        <input type="submit" value="Изменить"><br>
    </form>
    <a href="/manage/film">Назад</a>
    <br>
</body>
</html>
