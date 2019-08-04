<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/resources/css/tableCss.css"/>" type="text/css">
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/pages/menu.jsp" %>
</head>
<body>
<table class="simple-little-table" cellspacing='0' style="text-align: center; margin: auto; margin-top: 50px">
    <thead>
    <tr>
        <th>Автор</th>
        <th>Назви книг</th>
    </tr><!-- Table Header -->
    </thead>
    <tbody>

    <tr>
        <td>${author.name}</td>
        <c:forEach var="book" items="${listBooks}">
            <td><c:out value="${book.title}"/></td>
        </c:forEach>
    </tr>

    </tbody>
</table>
</body>
</html>
