<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Error</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <div id="content">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="Error"></jsp:param>
        </jsp:include>

        <main>
            <p>This page does not exist (yet).</p>
        </main>
    </div>
    <footer> &copy; R0716887, Mats Coeckelbergh</footer>
</div>
</body>
</html>