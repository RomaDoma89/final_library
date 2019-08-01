<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../menu.jsp" %>
    <form:form action="availableBook" method="post" modelAttribute="bookDto" cssStyle="text-align: center">
        <p>Введіть назву книги :</p>
        <br>
        <form:label path="title" style="text-align: center"  >Title </form:label>
        <form:input  path="title" value="Effective Java" style="text-align: center"/>
        <input type="submit" value="Submit"/>
    </form:form>
</body>
</html>
