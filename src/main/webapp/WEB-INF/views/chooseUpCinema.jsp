<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<html>
<head>

</head>
<body>
    <table>
        <c:forEach items="${cinemaList}" var="cinema">
            <tr>
                <td>
                    <a href="/manage/cinema/chooseup/update?cinema_id=${cinema.id}">${cinema.name}</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td>
                <a href="/manage/cinema"><sp:message code="manage.GoBack"/></a><br>
            </td>
        </tr>
    </table>
</body>
</html>
