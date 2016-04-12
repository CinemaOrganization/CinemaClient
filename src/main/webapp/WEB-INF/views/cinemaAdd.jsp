<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создайте новый кинотеатр</title>
</head>
<body>
    <form action="/manage/cinema/add/create" method="post">
        Введите название кинотеатра:<br>
        <input type="text" name="name"/><br>
        <input type="submit" value="Создать"><br>
    </form>
    <a href="/manage/cinema">Назад</a>
    <br>
</body>
</html>
