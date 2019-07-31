<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="<c:url value="/resources/tableCss.css"/>" type="text/css">

<html>
<head>
    <title>Available book</title>
</head>
<body>
<%@include file="../menu.jsp"%>
<table class="simple-little-table" cellspacing='0' style="text-align: center; margin: auto; margin-top: 50px">
    <thead>
    <tr>
        <th>Назва книги: ${bookDto.title} </th>
    </tr>
    <tr>
        <th>Кількість доступних екземплярів: ${bookDto.available}</th>
    </tr>
    </thead>
</table>


</body>
</html>
