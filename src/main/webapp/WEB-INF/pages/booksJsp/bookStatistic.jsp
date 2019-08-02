<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../menu.jsp" %>
<form:form action="" method="post" modelAttribute="bookDto" cssStyle="text-align: center">
    <p>Введіть назву книги :</p>
    <br>${ }
    <form:label path="title" style="text-align: center"  >Title </form:label>
    <form:input  path="title" value="Effective Java" style="text-align: center"/>
    <input type="submit" value="Submit"/>
</form:form>
<c:choose>
    <c:when test="${bookDto.dateFrom==null}">
        <form:form action="getCountBookByPeriod" modelAttribute="bookStatisticDto" method="post"
                   style=" text-align: center; padding-top: 50px">
            Input first date in format (yyyy-MM-dd)
            <br>
            <input type="datetime-local" name="dateFrom" required>
            <br>
            Input second date in format (yyyy-MM-dd)
            <br>
            <input type="datetime-local" name="dateTo" required>
            <br>
            <input type="submit" value="Submit">
        </form:form>
    </c:when>
    <c:otherwise>
        <div style="text-align: center">
            <table class="simple-little-table" cellspacing='0' style="text-align: center; margin: auto; margin-top: 50px">
                <p>З ${bookByPeriodDto.dateFrom} по ${bookByPeriodDto.dateTo} було  видано ${bookByPeriodDto.countOfBookByPeriod}</p>
            </table>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>
