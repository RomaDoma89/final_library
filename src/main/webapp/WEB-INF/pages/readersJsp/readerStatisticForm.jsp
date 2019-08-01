<%--
  Created by IntelliJ IDEA.
  User: Marian
  Date: 22.07.2019
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="/WEB-INF/pages/menu.jsp"%>

<form:form action="readerStatistic"  modelAttribute="readerStatisticDto" method="post" style=" text-align: center; padding-top: 50px">
    <p>Введіть ім'я користувача</p>
    <form:label path="name" style="text-align: center" >Title </form:label>
    <form:input  path="name" value="Jack Edwards"  style="text-align: center"/>    <br>
    <form:select path="select">
        <form:option selected="selected" value="read">Read book</form:option>
        <form:option value="ordered">Not returned</form:option>
        <form:option  value="date">registration date</form:option>
    </form:select>
    <br>
    <input type="submit" value="Submit">
</form:form>
</body>
</html>
