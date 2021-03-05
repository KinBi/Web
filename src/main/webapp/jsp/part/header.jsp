<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="locale.content"/>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/upgrade.css" rel="stylesheet">
    <html>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand nav-m-p" style="padding-left: 5px"><fmt:message key="navbar.lang"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler"
            aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarToggler">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="padding-top: 10px">
            <li class="nav-item active nav-m-p">
                <a class="nav-link"><fmt:message key="navbar.home"/></a>
            </li>
            <li class="nav-item nav-m-p">
                <a class="nav-link"><fmt:message key="navbar.not_home"/></a>
            </li>

            <%--            fixme--%>
            <c:if test="${sessionScope.role ne 'GUEST'}">
                <form class="nav-item nav-m-p" method="post" action="controller">
                    <input type="hidden" name="command" value="to_profile"/>
                    <a type="submit" class="nav-link" href="${pageContext.request.contextPath}/user/profile">
                        <fmt:message key="global.profile"/>
                    </a>
                </form>
            </c:if>

            <c:if test="${sessionScope.role eq 'ADMIN'}">
                <form class="nav-item nav-m-p" method="post" action="controller">
                    <input type="hidden" name="command" value="to_admin_page"/>
                        <%--    fixme--%>
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/adminPage">
                        <fmt:message key="global.adminPage"/>
                    </a>
                </form>
            </c:if>
        </ul>
        <%--    fixme--%>
        <c:if test="${sessionScope.role ne 'GUEST'}">
            <div class="nav-item nav-m-p">
                <img class="round-img" alt="" src="${pageContext.request.contextPath}/img/42.jpg"/>
            </div>
        </c:if>

        <form class="btn-group dropstart nav-m-p" method="post" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="locale"/>
            <button type="button" class="btn btn-secondary dropdown-toggle"
                    data-bs-toggle="dropdown" aria-expanded="false"><fmt:message key="navbar.lang"/>
            </button>
            <ul class="dropdown-menu dropdown-menu-dark">
                <li>
                    <button type="submit" class="dropdown-item" name="localeButton" value="en"><fmt:message
                            key="global.en"/></button>
                </li>
                <li>
                    <button type="submit" class="dropdown-item" name="localeButton" value="ru"><fmt:message
                            key="global.ru"/></button>
                </li>
            </ul>
        </form>
    </div>
</nav>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>