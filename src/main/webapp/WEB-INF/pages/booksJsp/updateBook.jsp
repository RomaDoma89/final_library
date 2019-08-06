<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link rel="stylesheet" href="<c:url value="/resources/css/tableCss.css"/>" type="text/css">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Available book</title>
</head>
<body>
<%@include file="../menu.jsp" %>

<c:choose>
    <c:when test="${book.title==null}">
        <table class="simple-little-table" cellspacing='0' style="text-align: center; margin: auto; margin-top: 50px">
            <thead>
            <tr>
                <th>Назва</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                    <form:form action="updateBook" modelAttribute="bookDto" method="post" cssStyle="text-align: start">
                        <input type="hidden" name="id" value=${bookDto.id} />
                        <textarea  name="title"  required  >${bookDto.title}</textarea>
                        <input type="submit" value="Оновити"/>
                    </form:form>
                </td>
            </tr>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <table class="simple-little-table" cellspacing='0' style="text-align: center; margin: auto; margin-top: 50px">
            <thead>
            <tr>
                <th>Назва книги: ${book.title} is updated </th>
            </tr>
            </thead>
        </table>
    </c:otherwise>
</c:choose>

</body>
</html>
