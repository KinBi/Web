<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="locale.content"/>
<html>
<head>
    <title>Registration</title>
    <link href="${pageContext.request.contextPath}/css/upgrade.css" type="text/css" rel="stylesheet">
</head>
<body>
<jsp:include page="../jsp/part/background.jsp"/>
<%@include file="../jsp/part/header.jsp" %>
<div style="width: 400px;
            padding: 20px; alignment: left">
    <form method="post" action="controller">
        <input type="hidden" name="command" value="to_login"/>
        <button type="submit" class="btn btn-outline-light">
            <fmt:message key="global.login_button"/></button>
    </form>
    <form method="post" action="controller">
        <input type="hidden" name="command" value="registration"/>
        <p class="text-log-reg"><fmt:message key="global.nickname"/><strong style="color: red"><fmt:message
                key="global.asterisk"/></strong></p>
        <input class="textarea-log-reg" type="text" name="nickname" placeholder="KillAllHumans228"
               required pattern="^[а-яА-Яa-zA-Z0-9]{2,20}$" title=<fmt:message key="registration.nickname_title"/>/>
        <p class="text-log-reg"><fmt:message key="global.email"/><strong style="color: red"><fmt:message
                key="global.asterisk"/></strong></p>
        <input class="textarea-log-reg" type="text" name="email" placeholder="killallhumans@bender.com"
               required pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" title=
                <fmt:message key="registration.email_title"/>/>
        <p class="text-log-reg"><fmt:message key="global.password"/><strong style="color: red"><fmt:message
                key="global.asterisk"/></strong></p>
        <input class="textarea-log-reg" type="password" name="password" placeholder="password"
               required pattern="^(?=.*[a-z])(?=.*[A-Z]+)(?=.*[0-9])[a-zA-Z0-9]{6,30}$" title=<fmt:message
                key="registration.password_title"/>/>
        <br/>${errorRegistrationMessage}<br/>
        <button class="btn btn-outline-dark" style="margin-left: auto; display: block" type="submit">
            <fmt:message key="global.register_button"/></button>
    </form>

<jsp:include page="secured/userList.jsp" />
</div>
</body>
</html>
