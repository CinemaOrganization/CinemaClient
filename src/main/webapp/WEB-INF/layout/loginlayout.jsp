<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="float: right">
    <table>
        <tr>
            <sec:authorize access="isAnonymous()">
                <td><a href="<c:url value="/user/login"/>"><sp:message code="loginlayout.SingIn"/></a></td>
                <td>|<a href="<c:url value="/user/registration"/>"><sp:message code="loginlayout.Reg"/></a></td>
            </sec:authorize>
            <sec:authorize access="!isAnonymous()">
                <sp:message code="loginlayout.EnteredAs"/><br>
                <sec:authentication property="principal.username"/>
                <td><c:url var="usermyUrl" value="/user/my"/>
                    <form action="${usermyUrl}" method="post">
                        <input type="submit" value="<sp:message code="loginlayout.PersonalArea"/>"/>
                        <input type="hidden"  name="username"
                               value="<sec:authentication property="principal.username"/>"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </td>
                <td><c:url var="logoutUrl" value="/logout"/>
                    <form action="${logoutUrl}" method="post">
                        <input type="submit" value="<sp:message code="loginlayout.Exit"/>"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </td>
            </sec:authorize>
        </tr>
    </table>
</div>