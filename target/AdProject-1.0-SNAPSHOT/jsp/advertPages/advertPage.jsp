<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 79174
  Date: 06.12.2022
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Advert ${requestScope.advert.name}</title>
    <script src="${pageContext.request.contextPath}/js/countViews.js"></script>
</head>
<body>
<div style="position: fixed; left:25%">
    <a href="${pageContext.request.contextPath}/mainPage"><button>Main</button></a>
</div>

<div>
    Name advert: ${requestScope.advert.name}
    <p>Description: ${requestScope.advert.description}</p>
    <p>Author: ${requestScope.author.firstName} ${requestScope.author.lastName}</p>
    <p>Phone Number: ${requestScope.author.phoneNumber}</p>
    <p>Price: ${requestScope.advert.price}</p>
    <p>Category: ${requestScope.advert.category}</p>
    <p>City: ${requestScope.advert.city}</p>
    <p>Address: ${requestScope.advert.address}</p>
    <p>Sales Start Date: ${requestScope.advert.salesStartDate}</p>
    <c:if test="${requestScope.advert.status == false}">
        <p>Sold date: ${requestScope.advert.dateOfSale}</p>
    </c:if>
    <p>Status:
        <c:if test="${requestScope.advert.status == true}">In sale</c:if>
        <c:if test="${requestScope.advert.status == false}">Sold</c:if>
    </p>
</div>

<div style="position: fixed; left: 40%; top:5%">
<%--    Views: <div onload="counter_fn()" id="views"><span>0</span></div>--%>
    <div>
        <p>The number of visitors is : <span id="cntr">0</span></p>
    </div>

    <script>
        function counter_fn() {
            var counter = document.getElementById("cntr");
            var count = 0;
            count = parseInt(counter.innerHTML);
            count = count + 1;
            counter.innerHTML = count;
        }
        window.onload = counter_fn;
    </script>
    <p>Added favourite: ${requestScope.countFavourite}</p>
</div>

<div style="position: fixed; top: 65%">
    <c:if test="${!empty requestScope.user}">
        <c:if test="${!requestScope.listFavouriteAds.contains(requestScope.advert)}">
            <a href="${pageContext.request.contextPath}/advert/addFavourite?id=${requestScope.advert.id}"><button>Add to favourite</button></a>
        </c:if>

        <c:if test="${requestScope.listFavouriteAds.contains(requestScope.advert)}">
            <a href="${pageContext.request.contextPath}/advert/removeFavourite?id=${requestScope.advert.id}"><button>Remove favourite</button></a>
        </c:if>
    </c:if>
</div>

<div style="position:fixed; top: 60%">
    <c:if test="${requestScope.user.id != requestScope.author.id}">
        <a href="${pageContext.request.contextPath}/profile?id=${requestScope.advert.authorId}"><button>Profile Seller</button></a>
    </c:if>
</div>

<div style="position:fixed; top: 70%">
    <c:if test="${requestScope.user.id == requestScope.author.id}">
        <c:if test="${requestScope.advert.status == true}">
            <a href="${pageContext.request.contextPath}/advert/sold?id=${requestScope.advert.id}"><button>I sold</button></a>
            <a href="${pageContext.request.contextPath}/advert/editAdvert?id=${requestScope.advert.id}"><button>Edit advert</button></a>
        </c:if>
        <c:if test="${requestScope.advert.status == false}">
            <a href="${pageContext.request.contextPath}/advert/returnAdvert?id=${requestScope.advert.id}"><button>Return in sell</button></a>
        </c:if>
    </c:if>
</div>

</body>
</html>
