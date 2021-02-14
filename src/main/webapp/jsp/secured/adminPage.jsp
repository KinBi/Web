<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
<h1>ADMIN PAGE</h1>
</body>
<form method="post" action="controller">
    <input type="hidden" name="command" value="to_user_list" />
    <button type="submit" value="UserList" />
</form>
</html>
