<%--
  Created by IntelliJ IDEA.
  User: 79174
  Date: 07.12.2022
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Advert</title>
</head>
<body>

<div style="position: fixed; left:25%">
    <a href="${pageContext.request.contextPath}/advert?id=${requestScope.advert.id}"><button>Back</button></a>
</div>
<div style="position: fixed; left: 42%; top: 20%">
    <form action="${pageContext.request.contextPath}/advert/editAdvert?id=${requestScope.advert.id}" name="advert" method="POST">
        <label>Name
            <input required type="text" id="name" name="name" placeholder="Enter advert name..."></label>
        <p><label>Description
            <input required id="description" type="text" name="description" placeholder="Enter description..."></label></p>
        <p><label>Price
            <input required id="price" name="price" placeholder="Enter price"></label></p>
        <button type="submit">Edit advert</button>
    </form>
</div>
</body>
</html>
