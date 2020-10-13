<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>FILMS</title>
</head>
<body>
<table border="4" width="75%">
    <caption>Фильмы</caption>
    <tr>
        <th>№</th>
        <th>Название</th>
        <th>Год выпуска</th>
        <th>Жанр</th>
        <th colspan="2">action</th>
    </tr>
    <c:forEach var="user" items="${usersList}" varStatus="i">
        <tr>
            <td>${i.index + 1 + (page - 1) * 10}</td>
            <td>${user.title}</td>
            <td>${user.year}</td>
            <td>${user.genre}</td>
            <td><a href="<c:url value="/edit/${user.id}"/>">edit</a></td>
            <td><a href="<c:url value="/delete/${user.id}"/>">delete</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="6">
            <a href="<c:url value="/add"/>">Add new user</a>
            <c:forEach begin="${1}" end="${pagesCount}" step="1" varStatus="i">
                <c:url value="/" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                <a href="${url}">${i.index}</a>
            </c:forEach>
        </td>
    </tr>
</table>
</body>
</html>


