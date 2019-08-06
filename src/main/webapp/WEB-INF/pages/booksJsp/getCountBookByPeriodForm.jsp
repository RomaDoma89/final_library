<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="/WEB-INF/pages/menu.jsp" %>
<c:choose>
    <c:when test="${bookByPeriodDto.dateFrom==null}">
        <form:form action="getCountBookByPeriod" method="post"
                   style=" text-align: center; padding-top: 50px">
            <p style="color: gainsboro">Input first date in format
            <br>
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
        <div style="text-align: center">
        <table class="simple-little-table" cellspacing='0' style="text-align: center; margin: auto; margin-top: 50px">
            <p style="color: gainsboro">З ${bookByPeriodDto.dateFrom} по ${bookByPeriodDto.dateTo} було  видано ${bookByPeriodDto.countOfBookByPeriod}</p>
        </table>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>
