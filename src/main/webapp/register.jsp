<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <div id="content">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="Register"></jsp:param>
        </jsp:include>

        <c:if test="${not empty confirmation}">
            <div class="alert-confirm">
                    ${confirmation}
            </div>
        </c:if>

        <main>
            <c:if test="${not empty errors}">
                <div class="alert-danger">
                    <ul>
                        <c:forEach var="error" items="${errors}">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <form method="POST" action="Controller?command=Add" novalidate="novalidate">
                <p>
                    <label for="userid">User id:</label>
                    <input type="text" id="userid" name="userid" value="${userIdPrevious}" required>
                </p>
                <p>
                    <label for="firstName">First Name:</label>
                    <input type="text" id="firstName" name="firstName" value="${firstNamePrevious}" required>
                </p>
                <p>
                    <label for="lastName">Last Name:</label>
                    <input type="text" id="lastName" name="lastName" value="${lastNamePrevious}" required>
                </p>
                <p>
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="${emailPrevious}" required>
                </p>
                <p>
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" value="${passwordPrevious}" required>
                </p>
                <p>
                    <input type="submit" id="signUp" value="Sign Up">
                </p>
            </form>
        </main>
    </div>
    <footer> &copy; R0716887, Mats Coeckelbergh</footer>
</div>
</body>
</html>
