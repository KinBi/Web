<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="locale.content"/>
<%--todo--%>
<html>
<head>
    <title><fmt:message key="admin.adminPage"/></title>
</head>
<jsp:include page="/jsp/part/background.jsp"/>
<jsp:include page="/jsp/part/header.jsp"/>
<body>
<div style="padding: 5px">
    <h1><fmt:message key="admin.adminPage"/></h1>
    <a class="btn btn-outline-light" href="${pageContext.request.contextPath}/admin/userList.do?page=1"><fmt:message key="admin.getUsers"/></a>
</div>
</body>
</html>
