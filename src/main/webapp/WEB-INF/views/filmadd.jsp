<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создать новый фильм</title>
</head>
<body>
    <form action="/manage/film/add/create" method="post">
        Введите название фильма:<br>
        <input type="text" name="name"/><br>
        Введите название студии:<br>
        <input type="text" name="studio"/><br>
        Введите продолжительность фильма:<br>
        <input type="datetime" name="duration"/><br>
        Введите год фильма:<br>
        <input type="text" name="year"><br>
        Введите описание фильма:<br>
        <textarea cols="50" rows="7" wrap="physical" name="description"></textarea><br>
        <input type="submit" value="Создать"><br>
    </form>
    <a href="/manage/film">Назад</a>
    <br>
</body>
</html>
