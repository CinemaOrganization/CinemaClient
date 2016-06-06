<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

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
            <input name="add" type="submit" value="Отправить"/>
        </form:form>
    </sec:authorize>

    <table class="simple-little-table" width="50%">
        <c:forEach items="${commentList}" var="current_comment">
            <tr>
                <td>${current_comment.user.username}<br>
                    <my:localDateTime date="${current_comment.time}"/></td>
                <td width="60%">${current_comment.text}</td>
                <td width="5%">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <form:form modelAttribute="comment"
                                   method="POST" enctype="utf8">
                            <form:hidden path="id" value="${current_comment.id}"/>
                            <form:hidden path="text" value="${current_comment.text}"/>
                            <form:hidden path="film.id" value="${film.id}"/>
                            <input name="remove" type="submit" value="Удалить">
                        </form:form>
                    </sec:authorize>
                    <sec:authorize access="!isAnonymous()">
                        <sec:authorize access="!hasRole('ROLE_ADMIN')">
                            <c:if test="${current_comment.user.username == username}">
                                <form:form modelAttribute="comment"
                                           method="POST" enctype="utf8">
                                    <form:hidden path="id" value="${current_comment.id}"/>
                                    <form:hidden path="text" value="${current_comment.text}"/>
                                    <form:hidden path="film.id" value="${film.id}"/>
                                    <input name="remove" type="submit" value="Удалить">
                                </form:form>
                            </c:if>
                        </sec:authorize>
                    </sec:authorize>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

