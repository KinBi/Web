<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<nav class="navbar navbar-expand-lg navbar-light bg-light" style="min-height: 80px; max-height: 80px">
    <a class="navbar-brand nav-m-p" style="padding-left: 5px"><fmt:message key="navbar.lang"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler"
            aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarToggler">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="padding-top: 10px">
            <c:if test="${sessionScope.role ne 'GUEST'}">
                <form class="nav-item nav-m-p" method="post"
                      action="${pageContext.request.contextPath}/user/main.do">
                    <input type="hidden" name="command" value="to_main_page"/>
                    <button type="submit" class="btn btn-outline-light nav-link">
                        <fmt:message key="navbar.home"/>
                    </button>
                </form>
            </c:if>

            <c:if test="${sessionScope.role eq 'ADMIN'}">
                <form class="nav-item nav-m-p" method="post"
                      action="${pageContext.request.contextPath}/admin/adminPage.do">
                    <input type="hidden" name="command" value="to_admin_page"/>
                    <button type="submit" class="btn btn-outline-light nav-link">
                        <fmt:message key="navbar.adminPage"/>
                    </button>
                </form>
            </c:if>
        </ul>

        <c:if test="${sessionScope.role ne 'GUEST'}">
            <form class="nav-item nav-m-p"  method="post"
                  action="${pageContext.request.contextPath}/user/profile.do">
                <input type="hidden" name="command" value="to_profile" />
                <button class="btn btn-outline-light nav-item">
                    <img class="round-img" alt="" src="${pageContext.request.contextPath}/images/image"/>
                </button>
            </form>
        </c:if>

        <form class="btn-group dropstart nav-m-p" method="post"
              action="${pageContext.request.contextPath}/${sessionScope.currentPageUrl}.do">
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