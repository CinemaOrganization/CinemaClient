<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
<h3>Комментарии к фильму:</h3>
    <sec:authorize access="isAnonymous()">
        Для того, чтобы оставить комментарий осуществите <a href="<c:url value="/user/login"/>">вход</a>.
    </sec:authorize>
    <sec:authorize access="!isAnonymous()">
        <form:form modelAttribute="comment" method="POST" enctype="utf8">
            <form:textarea path="text" value=""/>
            <form:errors path="text" element="div"/>
            <form:hidden path="film.id" value="${film.id}"/>
            <sec:authentication property="principal.username" var="username"/>
            <form:hidden path="user.username" value="${username}"/>
            <input name="submit" type="submit" value="Отправить"/>
        </form:form>
    </sec:authorize>
</div>

