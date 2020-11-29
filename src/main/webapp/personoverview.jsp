<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <div id="content">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="Overview"></jsp:param>
        </jsp:include>

        <main style="overflow-x: scroll">
            <c:choose>
                <c:when test="${people.size() <= 0}">
                    <p id="error">At this moment, nobody is registered in the system.</p>
                </c:when>
                <c:otherwise>
                    <table>
                        <h2>All</h2>
                        <tr>
                            <th>E-mail</th>
                            <th>User ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                        </tr>
                        <c:forEach var="person" items="${people}">
                            <tr>
                                <td>${person.getEmail()}</td>
                                <td>${person.getUserid()}</td>
                                <td>${person.getFirstName()}</td>
                                <td>${person.getLastName()}</td>
                            </tr>
                        </c:forEach>
                    </table>
                    <br>
                    <table>
                        <h2>Positives</h2>
                        <tr>
                            <th>E-mail</th>
                            <th>User ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Date</th>
                        </tr>
                        <c:forEach var="person" items="${positives}">
                            <tr>
                                <td>${person.getEmail()}</td>
                                <td>${person.getUserid()}</td>
                                <td>${person.getFirstName()}</td>
                                <td>${person.getLastName()}</td>
                                <td>${person.getTestTime()}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
        </main>
    </div>
    <footer> &copy; R0716887, Mats Coeckelbergh</footer>
</div>
</body>
</html>