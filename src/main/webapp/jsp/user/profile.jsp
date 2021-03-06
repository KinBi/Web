<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="locale.content"/>
<html>
<head>
    <title><fmt:message key="profile.pageTitle"/></title>
</head>
<jsp:include page="/jsp/part/background.jsp"/>
<jsp:include page="/jsp/part/header.jsp"/>
<body>
<div>
    <%--        fixme--%>
    <label>UPLOAD IMAGE</label>
    <form action="${pageContext.request.contextPath}/upload/load" enctype="multipart/form-data" method="POST">
        Upload File: <input type="image" name="content" height="130">
        <input type="submit" value="Upload File">
    </form>
    <label><fmt:message key="global.nickname"/> ${sessionScope.nickname}</label><br/>
    <label><fmt:message key="global.email"/> ${sessionScope.email}</label><br/>
    <label><fmt:message key="global.score"/> ${sessionScope.score}</label><br/>
    <label><fmt:message key="global.cash"/> ${sessionScope.cash}</label><br/>
</div>
</body>
</html>
