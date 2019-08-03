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

<c:choose>
    <c:when test="${bookStatisticDto.title==null}">
        <form:form action="getBookStatistic" method="post" modelAttribute="bookStatisticDto"
                   cssStyle="text-align: center">
            <p>Введіть назву книги :</p>
            <br>
            <form:label path="title" style="text-align: center"></form:label>
            <input path="title" value="Effective Java"  required style="text-align: center"/>
            <input type="submit" value="Submit"/>
        </form:form>
    </c:when>
    <c:otherwise>
        <div style="text-align: center">
            <table class="simple-little-table" style="text-align: center; margin: auto; margin-top: 50px">
               <caption> Cтатистика </caption>
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
                <caption>По примірниках</caption>
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

        </div>
    </c:otherwise>
</c:choose>
</body>
</html>
