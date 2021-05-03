<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="locale.content"/>
<html>
<head>
    <title><fmt:message key="main.pageTitle" /></title>
    <link href="${pageContext.request.contextPath}/css/upgrade.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/jsp/part/background.jsp"/>
<jsp:include page="/jsp/part/header.jsp" />
<form method="post" action="${pageContext.request.contextPath}/user/main.do">
    <input type="hidden" name="command" value="logout"/>
    <h3 align="center"><fmt:message key="main.main"/></h3>
    <p align="center"><fmt:message key="global.nickname" /> ${sessionScope.nickname}</p>
<%--    fixme--%>
    <input style="margin:0 auto; display: block" type="submit" value="Logout"/>
</form>
<form method="post" action="${pageContext.request.contextPath}/user/main.do">
    <input type="hidden" name="command" value="adv"/>
    <h1 align="center">Билл Гейтс был восхищён, когда узнал...</h1>
    <%--            <input style="margin:0 auto; display: block" type="image" img src="https://memepedia.ru/wp-content/uploads/2019/06/stonks-template.png"--%>
    <%--                 alt="Здесь могла бы быть ваша реклама"/>--%>
    <a href="https://learn.javascript.ru/ninja-code" target="_blank" title="Узнать секрет">
        <img style="opacity:75%; margin:0 auto; display:block"
             src="https://memepedia.ru/wp-content/uploads/2019/06/stonks-template.png"
             alt="Здесь могла бы быть ваша реклама" /></a>
</form>
</body>
</html>