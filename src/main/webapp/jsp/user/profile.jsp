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
    <label><fmt:message key="profile.uploadImage"/></label>
    <form action="${pageContext.request.contextPath}/upload/load" enctype="multipart/form-data" method="POST">
        <fmt:message key="profile.uploadFile"/> <input type="file" name="content" accept="image/*">
        <input type="submit" value="Upload File">
        <br/>${uploadResult}
    </form>
    <label class="text-dark text-center"><fmt:message key="global.nickname"/> ${sessionScope.nickname}</label><br/>
    <label><fmt:message key="global.email"/> ${sessionScope.email}</label><br/>
    <label><fmt:message key="global.score"/> ${sessionScope.score}</label><br/>
    <label><fmt:message key="global.cash"/> ${sessionScope.cash}</label><br/>
</div>
</body>
</html>
