<%--
  Created by IntelliJ IDEA.
  User: dovga
  Date: 22.07.2019
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<c:url value="/resources/css/tableCss.css"/>" type="text/css">
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/pages/menu.jsp"%>
</head>
<body>
<table class="simple-little-table" cellspacing='0' style="text-align: center; margin: auto; margin-top: 50px">
    <thead>
    <tr>
        <th>Середній вік читачів по вказаній книзі: ${readerAvgDto.avgByBook}</th>
        <th>Середній вік читачів по вказаному автору: ${readerAvgDto.avgByAuthor}</th>
    </tr><!-- Table Header -->
    </thead>
</table>

</body>
</html>
