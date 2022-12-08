<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Favourite</title>
</head>
<body>
<div style="position: fixed; left:5%">
    <a href="${pageContext.request.contextPath}/mainPage"><button>Main</button></a>
</div>
<div style="position: fixed; left:35%; top:20%">
    <div style="position: fixed; left: 45%; top:10%"><h1>Favourites</h1></div>
    <table>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Category</th>
            <th>Price</th>
            <th>Status</th>
            <th>Go to ad</th>
        </tr>
        <c:forEach items="${requestScope.adverts}" var="advert">
            <tr>
                <td>${advert.name}</td>
                <td>${advert.description}</td>
                <td>${advert.category}</td>
                <td>${advert.price}</td>
                <td>${advert.status}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/advert">
                        <input type="hidden" name="id" value="${advert.id}">
                        <input type="submit" value="go">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
