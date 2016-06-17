<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/tags/form" %>
<html>
<head>

</head>
<body>
    <sec:form modelAttribute="hall" method="POST" acceptCharset="UTF-8" enctype="utf-8">
        <table>
            <tr>
                <td><sp:message code="manage.hall.create.name"/></td>
            </tr>
            <tr>
                <td>
                    <sec:input path="number" type="number" min="0" required = "true"/>
                    <sec:errors path="number" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td><sp:message code="manage.hall.create.rows"/></td>
            </tr>
            <tr>
                <td>
                    <sec:input path="rows" type="number" min="1" required = "true"/>
                    <sec:errors path="rows" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td><sp:message code="manage.hall.create.places"/></td>
            </tr>
            <tr>
                <td>
                    <sec:input path="numberInRows" type="number" min="1" required = "true"/>
                    <sec:errors path="numberInRows" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td><sp:message code="manage.hall.create.type"/></td>
            </tr>
            <tr>
                <td>
                    <sec:checkbox path="threeD"/>
                    <sec:errors path="threeD" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td><sp:message code="manage.hall.create.cinema"/></td>
            </tr>
            <tr>
                <td>
                  <sec:select path="cinema.id">
                        <c:forEach items="${cinemaList}" var="cinema">
                           <sec:option value="${cinema.id}">${cinema.name}</sec:option>
                        </c:forEach>
                  </sec:select>
                  <sec:errors path="cinema" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="<sp:message code="manage.hall.create.button.submit"/>">
                    <c:if test="${not empty ExistedHallError}">
                        <span class="error"><sp:message code="manage.hall.create.submit.err"/></span>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>
                    <a href="/manage/hall"><sp:message code="manage.GoBack"/></a>
                </td>
            </tr>
        </table>
    </sec:form>
</body>
</html>
