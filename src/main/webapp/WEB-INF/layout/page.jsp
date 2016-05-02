<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
    <title><tiles:getAsString name="title" /></title>
</head>

<body>
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