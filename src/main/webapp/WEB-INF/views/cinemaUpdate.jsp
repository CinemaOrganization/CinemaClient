<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Измените кинотеатр</title>
</head>
<body>
    <form action="/manage/cinema/chooseup/change/update" method="post">
        <input type="hidden" name="cinema_id" value="${cinema.id}">
        Введите название кинотеатра:<br>
        <input type="text" name="cinema_name" value="${cinema.name}"/><br>
        <input type="submit" value="Изменить"><br>
    </form>
    <a href="/manage/cinema">Назад</a>
    <br>
</body>
</html>
