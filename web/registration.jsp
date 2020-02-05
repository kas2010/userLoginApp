<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration</title>
    <style>
        <%@include file="resources/static/css/style.css" %>
    </style>
    <script>
        <%@include file="resources/static/js/jquery.min.js" %>
    </script>
    <%--    <link rel="stylesheet" href="css/style.css" type="text/css"--%>
    <%--          media="all"/>--%>
    <%--    <script type="text/javascript" src="js/jquery.min.js"></script>--%>

</head>
<body>
<h1>User registration</h1>
<hr>
<c:if test="${errors != null}">
    <c:forEach items="${errors}" var="error">
        <p class="error-msg">${error}</p>
    </c:forEach>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/registration" id="register_form">
    <div class="main">
        <div class="field">
            <label for="surnameField">Surname</label>
            <input id="surnameField" type="text" name="surname" value="${surname}" required="required">
        </div>
        <div class="field">
            <label for="nameField">Name</label>
            <input id="nameField" type="text" name="name" value="${name}" required="required">
        </div>
        <div class="field">
            <label for="loginField">Login</label>
            <input id="loginField" type="text" name="login" value="${login}" required="required">
        </div>
        <div class="field">
            <label for="passwordField">Password</label>
            <input id="passwordField" type="password" name="password">
        </div>
        <div class="field">
            <label for="repasswordField">Reenter password</label>
            <input id="repasswordField" type="password" name="repassword">
        </div>
        <div class="field">
            <label for="birthdateField">Birth date</label>
            <input id="birthdateField" type="date" name="born" value="${born}" required="required">
        </div>
        <input type="submit" value="Register"/>
        <input type="reset" value="Reset"/>
        <p><a href="${pageContext.request.contextPath}/login">Close page</a></p>
    </div>
</form>
</body>
<script>
    $(window).on('load', function () {
        let passwordElement = $('#passwordField')[0];
        passwordElement.addEventListener('input', function () {
            $('#password-err').remove();
            let password = $('#passwordField').val();
            if (password !== null) {
                if (password.length < 5) {
                    $('#passwordField').after('<span class="error" id="password-err">Password must be at least 5 characters long</span>');
                }
            }
        });

        let repasswordElement = $('#repasswordField')[0];
        repasswordElement.addEventListener('input', function () {
            $('#repassword-err').remove();
            let password = $('#passwordField').val();
            let repassword = $('#repasswordField').val();
            if (repassword !== null) {
                if (password !== repassword) {
                    $('#repasswordField').after('<span class="error" id="repassword-err">Password do not match</span>');
                }
            }
        });

        let bornElement = $('#birthdateField')[0];
        bornElement.addEventListener('input', function () {
            $('#birthdate-err').remove();
            let born = $('#birthdateField').val();
            if (born !== null) {
                let currentDay = new Date();
                let y1 = currentDay.getFullYear();
                let dt = new Date(born);
                let y2 = dt.getFullYear();
                let raz = y1 - y2;
                if (raz < 5) {
                    $('#birthdateField').after('<span class="error" id="birthdate-err">Too young!</span>');
                }
                if (raz > 150) {
                    $('#birthdateField').after('<span class="error" id="birthdate-err">Too old!</span>');
                }
            }
        });

        let  reg_form = $('#register_form')[0];
        reg_form.addEventListener('reset', function () {
            $('.error').remove();
            $('.error-msg').remove();
        })
    });
</script>
</html>
