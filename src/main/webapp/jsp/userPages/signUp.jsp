<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 79174
  Date: 07.10.2022
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
    <script src="https://kit.fontawesome.com/59c52f7b09.js" crossorigin="anonymous"></script>
    <script defer src="/js/showPass.js"></script>
<%--    <script src="${pageContext.request.contextPath}/js/script.js"></script>--%>
</head>
<body>
<с:if test="${!empty message}">
    <h3>${message}</h3>
</с:if>

<div style="position: fixed; left:5%">
    <a href="${pageContext.request.contextPath}/mainPage"><button>Main</button></a>
</div>
<div style="position: fixed; left: 40%; top: 25%">
    <form action="${pageContext.request.contextPath}/signUp" name="signUp" method="POST">
    <label>First Name
        <input required type="text" id="firstName" name="firstName" placeholder="Enter first name..."></label>
        <p>
            <label>Last Name
                <input required id="lastName" name="lastName" placeholder="Enter last name..."></label>
        </p>
        <p>
            <label>Email
                <input required id="email" name="email" type="email" placeholder="Enter email..."></label>
        </p>
        <p>
            <label>Password
                <input required id="password" name="password" type="password" placeholder="Enter password...">
                <a href="#" class="showPassword">
                    <i id="i" class="fa fa-eye" aria-hidden="true"></i>
                </a>
            </label>
        </p>
        <p>
            <label>Phone Number
                <input required id="phoneNumber" name="phoneNumber" placeholder="Enter phone number..."></label>
        </p>
        <p>
            <input id="policy-label" class="form-check-input" type="checkbox" name="policy"
                   value="agreed" required>
            <label class="form-check-label" for="policy-label">
                I do accept the Policy Agreement
            </label>
        </p>
        <button type="submit">Sign Up</button>
    </form>
    <p>
        <a href="${pageContext.request.contextPath}/signIn"><button>Sign In</button></a>
    </p>
</div>
</body>
</html>

