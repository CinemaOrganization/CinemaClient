<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<html>
<head>

</head>
<body>
    <sec:form modelAttribute="cinema" method="POST" acceptCharset="UTF-8" enctype="utf-8">
        <table>
            <tr>
                <td><sp:message code="manage.cinema.create.name"/></td>
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
                        <span class="error"><sp:message code="manage.cinema.create.err"/></span>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>
                    <a href="/manage/cinema"><sp:message code="manage.GoBack"/></a>
                </td>
            </tr>
        </table>
    </sec:form>
</body>
</html>
