<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<div>
    <h3><sp:message code="comment.describe"/></h3>
    <sec:authorize access="isAnonymous()">
        <sp:message code="comment.anonymousText"/> <a href="<c:url value="/user/login"/>"><sp:message code="comment.ref.enter"/></a>.
    </sec:authorize>
    <sec:authorize access="!isAnonymous()">
        <form:form modelAttribute="comment" method="POST" enctype="utf8">
            <form:textarea required="true" path="text" value="" wrap="hard" cols="60" rows="7" maxlength="255"/><br>
            <form:hidden path="film.id" value="${film.id}"/>
            <form:errors path="text" class="error"/>
            <br>
            <sec:authentication property="principal.username" var="username"/>
            <form:hidden path="user.username" value="${username}"/>
            <input name="add" type="submit" value="<sp:message code="comment.button.submit"/>"/>
        </form:form>
    </sec:authorize>

    <table class="table table-hover" width="50%">
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
                            <input name="remove" type="submit" value="<sp:message code="comment.button.delete"/>">
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
                                    <input name="remove" type="submit" value="<sp:message code="comment.button.delete"/>">
                                </form:form>
                            </c:if>
                        </sec:authorize>
                    </sec:authorize>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

