<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Search</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <div id="content">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="Search"></jsp:param>
        </jsp:include>

        <main>
            <div style="overflow-x: scroll">
                <form method="POST" action="Controller?command=Search" novalidate="novalidate">
                    <p>
                        <label for="searchforSubmit">Search for:</label>
                        <input type="text" id="searchfor" name="searchfor" value="" required>
                    </p>
                    <p>
                        <input type="submit" id="searchforSubmit" value="Search">
                    </p>
                </form>
                <h2>These are the people you met since your last test:</h2>
                <c:choose>
                    <c:when test="${contacts.size() <= 0}">
                        <p id="error">You don't need to contact anyone. You haven't seen anyone since your last positive test.</p>
                    </c:when>
                    <c:otherwise>
                        <table>
                            <tr>
                                <th>Date</th>
                                <th>Hour</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>GSM</th>
                                <th>Email</th>
                            </tr>
                            <c:forEach var="contact" items="${contacts}">
                                <tr>
                                    <td>
                                        <c:out value="${contact.getDate()}"/>
                                    </td>
                                    <td>
                                        <c:out value="${contact.getHour()}"/>
                                    </td>
                                    <td>
                                        <c:out value="${contact.getfName()}"/>
                                    </td>
                                    <td>
                                        <c:out value="${contact.getlName()}"/>
                                    </td>
                                    <td>
                                        <c:out value="${contact.getPhoneName()}"/>
                                    </td>
                                    <td>
                                        <c:out value="${contact.getEmail()}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:otherwise>
                </c:choose>
            </div>
        </main>
    </div>
    <footer> &copy; R0716887, Mats Coeckelbergh</footer>
</div>
</body>
</html>
