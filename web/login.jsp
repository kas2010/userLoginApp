<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sign in</title>
    <style>
        <%@include file="resources/static/css/style.css" %>
    </style>
    <%--    <link rel="stylesheet" href="resources/css/style.css"/>--%>
</head>
<body>
<c:if test="${errorMessage} != null">
    <p class="error">${errorMessage}</p>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/login" id="login_form">
    <div class="main">
        <h1>Sign in</h1>
        <div class="field">
            <label for="loginField">Login</label>
            <input id="loginField" type="text" name="login">
        </div>
        <div class="field">
            <label for="passwordField">Password</label>
            <input id="passwordField" type="password" name="password">
        </div>
        <input type="submit" value="Sign in"/>
        <p><a href="${pageContext.request.contextPath}/registration">Registration</a></p>
    </div>
</form>
</body>
</html>
