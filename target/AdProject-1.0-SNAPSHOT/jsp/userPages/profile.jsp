<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>

    <title>Profile</title>
</head>
<body>
    <div style="margin-left: 10%">
        <a href="${pageContext.request.contextPath}/mainPage"><button>Main</button></a>
        <br>
        <br>
        <c:if test="${requestScope.profileUser.id == requestScope.thisUser.id}">
            <div><a href="${pageContext.request.contextPath}/profile/settings?id=${requestScope.thisUser.id}"><button>Settings</button></a></div>
        </c:if>
    </div>
    <br>
    <br>
    <div style="margin-left: 50%">
        <div style=""><img src="<c:url value='${requestScope.storagePath}${requestScope.avatar}'/>" height="125px"/> </div>
        <p>Name: ${requestScope.profileUser.firstName}</p>
        <p>Last name: ${requestScope.profileUser.lastName}</p>
        <p>Phone Number: ${requestScope.profileUser.phoneNumber}</p>
        <p>City, Address: ${requestScope.profileUser.city}, ${requestScope.profileUser.address}</p>
    </div>
    <div style="margin-left: 30%">
        <p>-----------------------------------------------------------------------------------------------------------</p>
    </div>
        <div style="margin-left: 50%">
            <h1>Active items</h1>
        </div>
    <div style="margin-left: 40%">
        <div style="">
            <table>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Category</th>
                    <th>Price</th>
                    <th>Go to ad</th>
                </tr>
                <c:forEach items="${requestScope.activeAdverts}" var="activeAdvert">
                    <tr>
                        <td>${activeAdvert.name}</td>
                        <td>${activeAdvert.description}</td>
                        <td>${activeAdvert.category}</td>
                        <td>${activeAdvert.price}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/advert">
                                <input type="hidden" name="id" value="${activeAdvert.id}">
                                <input type="submit" value="go">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
        <div style="margin-left: 50%">
            <h1>Sold items</h1>
        </div>
    <div style="margin-left: 40%">
        <div style="">
            <table>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Category</th>
                    <th>Price</th>
                    <th>Go to ad</th>
                </tr>
                <c:forEach items="${requestScope.soldAdverts}" var="soldAdvert">
                    <tr>
                        <td>${soldAdvert.name}</td>
                        <td>${soldAdvert.description}</td>
                        <td>${soldAdvert.category}</td>
                        <td>${soldAdvert.price}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/advert">
                                <input type="hidden" name="id" value="${soldAdvert.id}">
                                <input type="submit" value="go">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>

