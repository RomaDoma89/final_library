<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link rel="stylesheet" href="<c:url value="/resources/css/tableCss.css"/>" type="text/css">
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="/WEB-INF/pages/menu.jsp" %>

<c:choose>
    <c:when test="${generalStatisticDto.dateFrom==null}">
        <form:form action="generalStatistic" method="post" style=" text-align: center; padding-top: 50px">
            <p style="color: gainsboro">Input first date in format<br>
            <input type="date" name="dateFrom" required>
            <br>
            <p style="color: gainsboro">Input second date in format
            <br>
            <input type="date" name="dateTo" required>
            <br>
            <input type="submit" value="Submit">
        </form:form>
    </c:when>
    <c:otherwise>
        <table class="simple-little-table" cellspacing='0' style="text-align: center; margin: auto; margin-top: 50px">
            <thead>
            <tr>
                <th>Середнвй вік читачів</th>
                <th>Середня кількість звернень</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${generalStatisticDto.avgAgeOfReaders}</td>
                <td>${generalStatisticDto.avgDaysOfReading}</td>
            </tr>
            </tbody>
        </table>
        <table class="simple-little-table" cellspacing='0' style="text-align: center; margin: auto; margin-top: 50px">
            <thead>
            <tr>
                <th>Читач</th>
                <th>Кількість днів використання бібліотеки</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${generalStatisticDto.avgVisitOfLibrary}" var="map">
                <tr>
                    <td>${map.key.name}</td>
                    <td>${map.value}</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </c:otherwise>
</c:choose>
</body>
</html>
