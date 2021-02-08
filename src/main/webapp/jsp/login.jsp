<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="locale.content"/>
<html>
<head>
    <title>Login</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/upgrade.css" type="text/css" rel="stylesheet">
</head>
<body>
<jsp:include page="../jsp/part/background.jsp"/>
<%@ include file="../jsp/part/header.jsp" %>
<div style="width: 400px;
            padding: 20px; alignment: left">
    <form name="loginForm" method="post" action="controller">
        <input type="hidden" name="command" value="login"/>
        <button class="btn btn-outline-light" type="submit" name="registration_button"><fmt:message
                key="global.registration_button"/></button>
    </form>
    <form name="loginForm" method="post" action="controller">
        <input type="hidden" name="command" value="login"/>
        <p class="text-log-reg"><fmt:message key="global.login"/></p>
        <input class="textarea-log-reg" type="text" name="login" placeholder="somelogin"/>
        <p class="text-log-reg"><fmt:message key="global.password"/></p>
        <input class="textarea-log-reg" type="password" name="password" placeholder="password"/>
        <br/>${errorLoginPassMessage}<br/>
        <button class="btn btn-outline-dark" style="margin-left: auto; display: block" type="submit"
                name="login_button"><fmt:message key="global.login_button"/></button>
    </form>
</div>
</body>
</html>
