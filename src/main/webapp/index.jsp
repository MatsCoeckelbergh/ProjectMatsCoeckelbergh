<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <div id="content">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="Home"></jsp:param>
        </jsp:include>

        <main>
            <c:choose>
                <c:when test="${not empty user}">
                    <p>Welcome, ${user.firstName}!</p>
                    <form action="Controller?command=Logout" method="POST">
                        <p>
                            <input type="submit" id="logOut" value="Log out">
                        </p>
                    </form>
                </c:when>
                <c:otherwise>
                    <c:if test="${not empty error}">
                        <div class="alert-danger">
                                ${error}
                        </div>
                    </c:if>
                    <form action="Controller?command=Login" method="POST">
                        <p>
                            <label for="userId">Your user id:</label>
                            <input type="text" id="userId" name="userId" value="${userIdPrevious}" required>
                        </p>
                        <p>
                            <label for="password">Your password:    </label>
                            <input type="password" id="password" name="password" required>
                        </p>
                        <p>
                            <input type="submit" id="logIn" value="Log in">
                        </p>
                    </form>
                </c:otherwise>
            </c:choose>
        </main>
    </div>
    <footer> &copy; R0716887, Mats Coeckelbergh</footer>
</div>
</body>
</html>