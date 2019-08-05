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
<%@include file="../menu.jsp" %>

<c:choose>
    <c:when test="${bookStatisticDto.title==null}">
        <form:form action="getBookStatistic" method="post" modelAttribute="bookStatisticDto"
                   cssStyle="text-align: center">
            <p style="color: gainsboro" >Введіть назву книги :</p>
            <br>
            <input name="title" value="Effective Java"  required style="text-align: center"/>
            <input type="submit" value="Submit"/>
        </form:form>
    </c:when>
    <c:otherwise>

            <table class="simple-little-table" style="text-align: center; margin: auto; margin-top: 50px">
               <caption><h3 style="color: gainsboro">Cтатистика</h3>  </caption>
                <tr>
                    <th>Загальна </th>
                    <th>Середня </th>
                </tr>
                <tr>
                    <td>${bookStatisticDto.totalUsageCount}</td>
                    <td>${bookStatisticDto.getAvgTimeOfUsage}</td>
                </tr>
            </table>
            <table class="simple-little-table" style="text-align: center; margin: auto; margin-top: 50px">
                <caption><h3 style="color: gainsboro">По примірниках</h3></caption>
                <tr>
                    <th>N</th>
                    <th>Назва</th>
                    <th>Доступна</th>
                    <th>Кількість замовлень</th>
                </tr>
                <c:forEach  var="book" items="${bookStatisticDto.getUsageCountForCopies}" varStatus="loop" >
                    <tr>
                        <td>${loop.index+1}</td>
                        <td>${book.key.book.title}</td>
                        <td>${book.key.available}</td>
                        <td>${book.value}</td>
                    </tr>
                </c:forEach>
            </table>

    </c:otherwise>
</c:choose>
</body>
</html>
