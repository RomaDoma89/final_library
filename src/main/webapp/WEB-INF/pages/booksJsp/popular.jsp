<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link rel="stylesheet" href="<c:url value="../../../resources/css/tableCss.css"/>" type="text/css">
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="/WEB-INF/pages/menu.jsp" %>
<table class="simple-little-table" cellspacing='0' style="text-align: center; margin: auto; margin-top: 50px">
    <thead>
    <tr>
        <th>Назва книги</th>
        <th>Рейтинг</th>
    </tr><!-- Table Header -->
    </thead>
    <tbody>
    <c:forEach var="entry" items="${map.map}">
        <tr>
            <td><c:out value="${entry.value.title}"/></td>
            <td><c:out value="${entry.key}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
