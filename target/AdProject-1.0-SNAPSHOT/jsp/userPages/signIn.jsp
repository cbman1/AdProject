<%--
  Created by IntelliJ IDEA.
  User: 79174
  Date: 26.11.2022
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
    <script src="https://kit.fontawesome.com/59c52f7b09.js" crossorigin="anonymous"></script>
    <script defer src="/js/showPass.js"></script>
</head>
<body>
<div style="position: fixed; left:25%">
    <a href="${pageContext.request.contextPath}/mainPage"><button>Main</button></a>
</div>
<div style="position: fixed; left: 42%"><h1>Sign in</h1></div>
<div style="position: fixed; left: 42%; top:20%">
    <form action="${pageContext.request.contextPath}/signIn" method="post">
        <label>Email
            <input required name="email" placeholder="Enter email..."></label>
        <p><label>Password
            <input required id="password" name="password" type="password" placeholder="Enter password...">
            <a href="#" class="showPassword">
                <i id="i" class="fa fa-eye" aria-hidden="true"></i>
            </a>
        </label></p>
        <p><input type="submit" value="Sign In"></p>
    </form>
</div>
</body>
</html>
