<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="locale.content"/>
<html>
<head>
    <title><fmt:message key="admin.userListPage"/></title>
</head>
<jsp:include page="/jsp/part/background.jsp"/>
<jsp:include page="/jsp/part/header.jsp"/>
<body>
<%--todo--%>
<div style="padding: 20px">
    <form method="post" action="${pageContext.request.contextPath}/admin/userList.do?page=${currentPage}">
        <input type="hidden" name="command" value="user_list_changes"/>
        <table class="table table-active" style="min-width: 560px; max-width: 560px;">
            <thead>
            <tr>
                <th scope="col" class="text-secondary"><fmt:message key="userList.userId"/></th>
                <th scope="col" class="text-dark"><fmt:message key="userList.userNickname"/></th>
                <th scope="col" class="text-white"><fmt:message key="userList.userScore"/></th>
                <th scope="col" class="text-success"><fmt:message key="userList.userCash"/></th>
                <th scope="col" class="text-info"><fmt:message key="userList.userEnabled"/></th>
                <th scope="col" class="text-warning"><fmt:message key="userList.userRole"/></th>
            </tr>
            </thead>

            <c:forEach var="user" items="${userList}">
                <td>${user.userId}</td>
                <input type="hidden" name="id" value="${user.userId}"/>
                <td>${user.nickname}</td>
                <td>${user.score}</td>
                <td>${user.cash}</td>
                <td>
                    <c:choose>
                        <c:when test="${user.enabled}">
                            <input class="form-check-input" type="checkbox" id="checkboxNoLabel" value="${user.userId}"
                                   aria-label="..." name="enabled" checked>
                        </c:when>
                        <c:otherwise>
                            <input class="form-check-input" type="checkbox" id="checkboxNoLabel" value="${user.userId}"
                                   aria-label="..." name="enabled">
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${user.role}</td>
                </tr>
            </c:forEach>
        </table>

        <table border="1" cellpadding="5" cellspacing="5" style="max-width: 560px;">
            <tr>
                <c:forEach begin="1" end="${countOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a class="text-primary" href="${pageContext.request.contextPath}/admin/userList.do?page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </table>

        <c:if test="${currentPage != 1}">
            <td><a class="text-primary" href="${pageContext.request.contextPath}/admin/userList.do?page=${currentPage - 1}">Previous</a></td>
        </c:if>
        <c:if test="${currentPage lt countOfPages}">
            <td><a href="${pageContext.request.contextPath}/admin/userList.do?page=${currentPage + 1}">Next</a><br/></td>
        </c:if>
        <button class="btn btn-outline-dark" type="submit"><fmt:message key="userList.acceptChanges"/></button>
    </form>
</div>
</body>
</html>
