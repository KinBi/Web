<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="locale.content"/>
<html>
<head>
    <title>Registration</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/upgrade.css" type="text/css" rel="stylesheet">
</head>
<body>
<jsp:include page="../jsp/part/background.jsp"/>
<%@include file="../jsp/part/header.jsp" %>

<div style="width: 400px;
            padding: 20px; alignment: left">
    <form name="loginForm" method="post" action="controller">
        <input type="hidden" name="command" value="registration"/>
        <button type="submit" name="login_button" class="btn btn-outline-light" ><fmt:message key="global.login_button" /></button>
    </form>
    <form name="registrationForm" method="post" action="controller">
        <input type="hidden" name="command" value="registration"/>
        <p class="text-log-reg"><fmt:message key="global.nickname" /><b style="color: red"><fmt:message key="global.asterisk" /></b></p>
        <input class="textarea-log-reg" type="text" name="nickname" placeholder="KillAllHumans228"
               required pattern="[а-яА-Яa-zA-Z0-9]{2,}" title="Only letters and digits(2 min)"/>
        <p class="text-log-reg"><fmt:message key="global.email" /><b style="color: red"><fmt:message key="global.asterisk" /></b></p></p>
        <input class="textarea-log-reg" type="text" name="email" placeholder="killallhumans@bender.com"
               required pattern="^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-z]+$" title="Must be like email"/>
        <p class="text-log-reg"><fmt:message key="global.password" /><b style="color: red"><fmt:message key="global.asterisk" /></b></p></p>
        <input class="textarea-log-reg" type="password" name="password" placeholder="password"
               required pattern="^[a-zA-Z0-9._-]+$" title="Must meet safety requirements"/>
        <br/>${errorRegistrationMessage}<br/>
        <button class="btn btn-outline-dark" style="margin-left: auto; display: block" type="submit" name="register_button"><fmt:message key="global.register_button" /> </button>
    </form>
</div>
</body>
</html>
