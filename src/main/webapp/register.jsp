<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script>
        function clientValidateByID(id){
            if (document.getElementById(id).value === ""){
                document.getElementById(id).className += "red-border ";
            }
            else{
                document.getElementById(id).className = "";
            }
        }

        function clientValidateUserID(){
            clientValidateByID("userid");
        }

        function clientValidateFirstName(){
            clientValidateByID("firstName");
        }

        function clientValidateLastName(){
            clientValidateByID("lastName");
        }

        function clientValidateEmail(){
            var emailstr = document.getElementById("email").value;
            if (/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(emailstr))
            {
                document.getElementById("email").className = "";
            }
            else
            {
                document.getElementById("email").className += "red-border "
            }
        }

        function clientValidatePassword(){
            var pwdstr = document.getElementById("password").value;
            var errors = [];
            if (pwdstr.length < 4)
            {
                errors.push("Your password is too short.");
            }
            if (pwdstr.toLowerCase() === pwdstr)
            {
                errors.push("Your password needs to contain atleast one capital letter.");
            }
            if (pwdstr.toLowerCase().includes(document.getElementById("firstName").value.toLowerCase())
                || pwdstr.toLowerCase().includes(document.getElementById("lastName").value.toLowerCase()))
            {
                errors.push("Your password can't contain your name");
            }
            if (errors.length === 0){
                document.getElementById("password").className = "";
            }
            else{
                document.getElementById("password").className += "red-border ";
            }
            document.getElementById("pwdvalidation").innerHTML = '';
            errors.forEach(function (error) {
                let li = document.createElement('li');
                document.getElementById("pwdvalidation").appendChild(li);

                li.innerHTML += error;
            });
        }

    </script>
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
                    <input type="text" id="userid" name="userid" value="${userIdPrevious}" onblur="clientValidateUserID()" required>
                </p>
                <p>
                    <label for="firstName">First Name:</label>
                    <input type="text" id="firstName" name="firstName" value="${firstNamePrevious}" onblur="clientValidateFirstName()" required>
                </p>
                <p>
                    <label for="lastName">Last Name:</label>
                    <input type="text" id="lastName" name="lastName" value="${lastNamePrevious}" onblur="clientValidateLastName()" required>
                </p>
                <p>
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="${emailPrevious}" onblur="clientValidateEmail()" required>
                </p>
                <p>
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" value="${passwordPrevious}" onkeyup="clientValidatePassword()" required>
                </p>
                <ul id="pwdvalidation"></ul>
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
