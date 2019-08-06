<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link rel="stylesheet" href="<c:url value="/resources/css/tableCss.css"/>" type="text/css">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Readers</title>
</head>
<body>
<%@include file="../menu.jsp" %>


<table class="simple-little-table" cellspacing='0' style="text-align: center; margin: auto; margin-top: 50px">
    <thead>
    <tr>
        <th>#</th>
        <th>Ім'я</th>
        <th>Народився</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="reader" items="${listReader}" varStatus="loop">
        <tr>
            <td>${loop.index+1}</td>
            <td>${reader.name}</td>
            <td>${reader.birthday}</td>
            <td><form:form action="deleteReader" method="post" modelAttribute="readerDto" cssStyle="text-align: start">
                <input type="submit" value="Видалити" />
                <input type="hidden" name="id" value=${reader.id} />
            </form:form></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<h1><p style="color: #ff958e; text-align: center;">Увага! Операція "Видалити" призводить до видалення ВСІЄЇ інформації
    про читачя та історію його звернень.</p></h1>

</body>
</html>
