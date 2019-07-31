<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/resources/style.css"/>" type="text/css">
    <title>Error</title>
    <jsp:include page="menu.jsp"/>
</head>
<body>
<div style="text-align: center" >
    <h1 >Input data is not correct, please try again</h1>
    <img src="${pageContext.request.contextPath}/resources/homer_doh.png">
</div>
</body>
</html>
