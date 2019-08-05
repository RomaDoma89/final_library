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
<form:form  action="getPopularBook" method="post" style="text-align: center; margin-top: 50px">
    <p style="color: gainsboro">Введіть період для пошуку: </p>
    <input type="date"  name="dateFrom" required>
    <br>
    <input type="date" name="dateTo" required >
    <br>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>