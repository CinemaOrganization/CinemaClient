<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><tiles:getAsString name="title" /></title>
    <spring:url value="/resources/css/style.css" var="crunchifyCSS"/>
    <link href="${crunchifyCSS}" type="text/css" rel="stylesheet"/>
</head>
<body background="black">
<table width="100%">
    <tr>
        <td width="80%"><tiles:insertAttribute name="header" /></td>
        <td width="20%"><tiles:insertAttribute name="login" /></td>
    </tr>
    <tr>
        <%--<td width="20%" nowrap="nowrap"><tiles:insertAttribute name="menu" /></td>--%>
        <td colspan="2"><tiles:insertAttribute name="body" /></td>
    </tr>
    <tr>
        <td colspan="2"><tiles:insertAttribute name="comment" /></td>
    </tr>
    <tr>
        <td colspan="2"><tiles:insertAttribute name="footer" /></td>
    </tr>
</table>
</body>
</html>