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
            <jsp:param name="page" value="Signout"></jsp:param>
        </jsp:include>

        <main>
            <c:choose>
                <c:when test="${empty user}">
                    <p>You need to log in to be able to sign out of the register.</p>
                </c:when>
                <c:otherwise>
                    <p>
                        Are you sure you want to sign out of our system?
                    </p>
                    <p>
                        Signing out completely erases you from our system, if you just want to log out, press the
                        "Log out" button on our <a href = "Controller?command=Home">home page</a> instead.
                    </p>
                    <form action="Controller?command=Remove" method="POST">
                        <p>
                            <input type="submit" id="signOut" value="Sign out">
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