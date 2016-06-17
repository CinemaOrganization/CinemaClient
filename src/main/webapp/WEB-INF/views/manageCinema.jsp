<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<html>
<head>

</head>
<body>
    <h1><sp:message code="manage.cinema"/></h1>
    <table>
        <tr>
            <td>
                <a href="/manage/cinema/create"><sp:message code="manage.create"/></a>
            </td>
        </tr>
        <tr>
            <td>
                <a href="/manage/cinema/chooseup"><sp:message code="manage.update"/></a>
            </td>
        </tr>
        <tr>
            <td>
                <a href="/manage/cinema/choosedel"><sp:message code="manage.delete"/></a>
            </td>
        </tr>
        <tr>
            <td>
                <a href="/manage"><sp:message code="manage.GoBack"/></a>
            </td>
        </tr>
    </table>
</body>
</html>
