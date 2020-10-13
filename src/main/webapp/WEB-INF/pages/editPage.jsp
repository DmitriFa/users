<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:choose>
        <c:when test="${empty user.title}">
            <title>Add</title>
        </c:when>
        <c:otherwise>
            <title>Edit</title>
        </c:otherwise>
    </c:choose>
</head>
<body>
<c:url value="/add" var="addUrl"/>
<c:url value="/edit" var="editUrl"/>
<form action="${empty user.title ? addUrl : editUrl}" name="user" method="POST">
    <c:choose>
        <c:when test="${!empty user.title}">
            <p>Edit user</p>
            <input type="hidden" name="id" value="${user.id}">
        </c:when>
        <c:otherwise>
            <p>Add new user</p>
        </c:otherwise>
    </c:choose>
    <p><input type="text" name="title" placeholder="title" value="${user.title}" maxlength="100" required>
    <p><input type="number" name="year" placeholder="year" value="${user.year}" required>
    <p><input type="text" name="genre" placeholder="genre" value="${user.genre}" maxlength="20" required>
    <p>
        <c:set value="add" var="add"/>
        <c:set value="edit" var="edit"/>
        <input type="submit" value="${empty user.title ? add : edit}">
    </p>
    <p>${message}</p>
</form>
</body>
</html>
