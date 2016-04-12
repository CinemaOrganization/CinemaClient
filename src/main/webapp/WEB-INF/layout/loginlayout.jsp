<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="float: right">
    <table>
        <tr>
            <sec:authorize access="isAnonymous()">
                <td><a href="<c:url value="/user/login"/>">Войти</a></td>
                <td>|<a href="<c:url value="/user/registration"/>">Регистрация</a></td>
            </sec:authorize>
            <sec:authorize access="!isAnonymous()">
                Вы зашли под пользователем:
                <sec:authentication property="principal.username"/>
                <td><c:url var="logoutUrl" value="/logout"/>
                    <form action="${logoutUrl}" method="post">
                        <input type="submit" value="Выйти"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </td>
            </sec:authorize>
        </tr>
    </table>
</div>