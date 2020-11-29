<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Add Contact</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <div id="content">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="Contacts"></jsp:param>
        </jsp:include>



        <main>
            <div style="overflow-x: scroll">
                <c:choose>
                    <c:when test="${people.size() <= 0}">
                        <p id="error">At this moment, nobody is registered in the system.</p>
                    </c:when>
                    <c:otherwise>
                        <table>
                            <tr>
                                <th>ID</th>
                                <th>Date</th>
                                <th>Hour</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                            </tr>
                            <c:forEach var="contact" items="${contacts}">
                                <tr>
                                    <td>${contact.getUserID()}</td>
                                    <td>${contact.getDate()}</td>
                                    <td>${contact.getHour()}</td>
                                    <td>${contact.getfName()}</td>
                                    <td>${contact.getlName()}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:otherwise>
                </c:choose>
            </div>

            <c:if test="${not empty errors}">
                <div class="alert-danger">
                    <ul>
                        <c:forEach var="error" items="${errors}">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <form method="POST" action="Controller?command=AddContact" novalidate="novalidate">
                <p>
                    <label for="fName">First Name:</label>
                    <input type="text" id="fName" name="fName" value="${fNamePrevious}" required>
                </p>
                <p>
                    <label for="lName">Last Name:</label>
                    <input type="text" id="lName" name="lName" value="${lNamePrevious}" required>
                </p>
                <p>
                    <label for="date">Date:</label>
                    <input type="date" id="date" name="date" value="${datePrevious}" required>
                </p>
                <p>
                    <label for="hour">Hour:</label>
                    <input type="time" id="hour" name="hour" value="${hourPrevious}" required>
                </p>
                <p>
                    <label for="phoneNumber">GSM-nummer:</label>
                    <input type="text" id="phoneNumber" name="phoneNumber" value="${phoneNumberPrevious}" required>
                </p>
                <p>
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="${emailPrevious}" required>
                </p>
                <p>
                    <input type="submit" id="addContact" value="Add contact">
                </p>
            </form>
        </main>
    </div>
    <footer> &copy; R0716887, Mats Coeckelbergh</footer>
</div>
</body>
</html>
