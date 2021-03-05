<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="locale.content"/>
<html>
<head>
    <title><fmt:message key="error.pageTitle" /></title>
</head>
<body>
<h1 style="font-size: 60px; color: red"><fmt:message key="error.error404" /></h1>
<p style="font-size: 20px"><fmt:message key="error.error404text" /></p>
</body>
</html>
