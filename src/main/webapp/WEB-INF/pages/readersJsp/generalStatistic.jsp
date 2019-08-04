<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/resources/css/tableCss.css"/>" type="text/css">
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="/WEB-INF/pages/menu.jsp" %>
<table class="simple-little-table" cellspacing='0' style="text-align: center; margin: auto; margin-top: 50px">
    <thead>
    <tr>
        <th>Середній час читання(днів)</th>
        <th>Середнвй вік читачів</th>
        <th>Кількість звернень</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${generalStatisticDto.avgDaysOfReading}</td>
        <td>${generalStatisticDto.avgAgeOfReaders}</td>
        <td>${generalStatisticDto.avgVisitOfLibrary}</td>
    </tr>
    </tbody>
</table>
</body>
</html>
