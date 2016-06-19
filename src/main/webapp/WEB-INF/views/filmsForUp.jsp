<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<html>
<head>

</head>
<body>
    <table>
        <c:forEach items="${films}" var="film">
            <tr>
                <td>
                    <a href="/manage/session/filmsForUp/sessions?film_id=${film.id}">${film.name} ${film.year} ${film.studio}</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td>
                <a href="/manage/session"><sp:message code="manage.GoBack"/></a><br>
            </td>
        </tr>
    </table>
</body>
</html>
