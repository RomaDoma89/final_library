<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="/WEB-INF/pages/menu.jsp"%>
<form action="getByPeriod"  method="get" style=" text-align: center; padding-top: 50px">
    Input first date in format(yyyy-MM-dd)<br>
    <input type="date" name="firstInput" style="width: 200px">
    <br>
    Input second date in format(yyyy-MM-dd)
    <br>
    <input type="text" name="secondInput" style="width: 200px"><br>
    <input type="submit" value="Submit">
</form>



</body>
</html>
