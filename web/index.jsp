<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Main page</title>
    <style><%@include file="resources/static/css/style.css" %></style>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css" media="all"/>--%>
  </head>
  <body>
    <h1>Main Page for ${username}</h1>
    <hr>
    <div class="big" id="counterField">
      ${user_counter}
    </div>
    <form method="post" action="${pageContext.request.contextPath}/index">
      <input type="submit" value="Counter +1">
      <p><a href="${pageContext.request.contextPath}/logout">Logout</a></p>
    </form>
   </body>
</html>
