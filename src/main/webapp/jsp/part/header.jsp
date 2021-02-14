<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="locale.content"/>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <html>
<body>
<form method="post" action="controller">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" style="padding-left: 5px"><fmt:message key="navbar.lang"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler"
                aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarToggler">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item active">
                    <a class="nav-link"><fmt:message key="navbar.home"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"><fmt:message key="navbar.not_home"/></a>
                </li>
            </ul>
        </div>

        <div class="nav-item btn-group dropstart" style="padding-right: 10px">
            <button type="button" class="btn btn-secondary dropdown-toggle"
                    data-bs-toggle="dropdown" aria-expanded="false">
                <fmt:message key="navbar.lang" />
            </button>
            <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                <input type="hidden" name="command" value="locale"/>
<%--                fixme--%>
                <input type="hidden" name="currentPage" value="${pageContext.request.requestURI}" />
                <li>
                    <button type="submit" class="dropdown-item" name="localeButton" value="en"><fmt:message key="global.en" /></button>
                </li>
                <li>
                    <button type="submit" class="dropdown-item" name="localeButton" value="ru"><fmt:message key="global.ru" /></button>
                </li>
            </ul>
        </div>
    </nav>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>

</body>
</html>
