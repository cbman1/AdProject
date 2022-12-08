<%--
  Created by IntelliJ IDEA.
  User: 79174
  Date: 06.12.2022
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="position: fixed; left:25%">
    <a href="${pageContext.request.contextPath}/mainPage"><button>Main</button></a>
</div>
<div style="position: fixed; left: 42%; top: 20%">
    <form action="${pageContext.request.contextPath}/addAdvert" name="addAdvert" method="POST">
        <label>Name
            <input required type="text" id="name" name="name" placeholder="Enter advert name..."></label>
        <p><label>Description
            <input required id="description" type="text" name="description" placeholder="Enter description..."></label></p>
        <p><label>Price
            <input required id="price" name="price" placeholder="Enter price"></label></p>
        <p><label>Category
            <select required id="category" name="category">
                <option>Personal items</option>
                <option>Auto</option>
                <option>Real estate</option>
                <option>Electronics</option>
                <option>Animals</option>
                <option>Hobby</option>
                <option>Business</option>
            </select></label></p>
        <button type="submit">Add Advert</button>
    </form>
</div>
</body>
</html>
