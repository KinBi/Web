<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="locale.content"/>
<html>
<head>
    <title><fmt:message key="login.pageTitle" /></title>
    <link href="${pageContext.request.contextPath}/css/upgrade.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/jsp/part/background.jsp"/>
<jsp:include page="/jsp/part/header.jsp" />
<div style="width: 400px;
            padding: 20px; alignment: left">
    <a href="${pageContext.request.contextPath}/registration" class="btn btn-outline-light">
        <fmt:message key="global.registration_button"/></a>
    <form method="post" action="${pageContext.request.contextPath}/login.do">
        <input name="ctoken" type="hidden" value="${stoken}"/>
        <input type="hidden" name="command" value="login"/>
        <p class="text-log-reg"><fmt:message key="global.login"/></p>
        <input class="textarea-log-reg" type="text" name="login" placeholder="somelogin"/>
        <p class="text-log-reg"><fmt:message key="global.password"/></p>
        <input class="textarea-log-reg" type="password" name="password" placeholder="password"/>
        <br/><p>${errorLoginPassMessage}</p><br/>
        <button class="btn btn-outline-dark" style="margin-left: auto; display: block" type="submit">
            <fmt:message key="global.login_button"/></button>
    </form>
</div>
</body>
</html>
