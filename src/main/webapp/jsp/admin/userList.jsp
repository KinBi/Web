<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--todo--%>
<form method="post" action="controller">
    <input type="hidden" name="command" value="user">
    <input type="hidden" name="currentPage" value="${pageContext.request.requestURI}"/>
    <button class="btn btn-outline-dark" type="submit">GET USERS</button>
    <c:forEach items="${userList}" var="user">
        <p>User: ${user}</p>
    </c:forEach>
</form>
</body>
</html>
