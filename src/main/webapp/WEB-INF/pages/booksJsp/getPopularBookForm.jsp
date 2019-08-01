<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/pages/menu.jsp"%>
</head>
<body>
<%@include file="../menu.jsp" %>
<form:form action="getPopularBookForm" method="post" modelAttribute="bookDto" cssStyle="text-align: center">
    <p>Введіть назву книги :</p>
    <br>
    <form:label path="title" style="text-align: center"  >Title </form:label>
    <form:input  path="title" value="Effective Java" style="text-align: center"/>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>
