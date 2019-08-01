<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link rel="stylesheet" href="<c:url value="/resources/tableCss.css"/>" type="text/css"><html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/pages/menu.jsp"%>
</head>
<body>
    <form:form action="booksByAuthor" method="post" modelAttribute="author" cssStyle="text-align: center">
    <p>Введіть ім'я автора:</p>
    <br>
        <form:label path="name" style="text-align: center" >Author </form:label>
        <form:input  path="name" value="Steve McConnell"  style="text-align: center"/>
           <input type="submit" value="Submit"/>
    </form:form>
</body>
</html>
