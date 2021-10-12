<%--
  Created by IntelliJ IDEA.
  User: ndwannafly
  Date: 11/10/2021
  Time: 10:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" type="text/css" href="./css/error.css">
</head>
<body>
<div class="error-container">
    <h1>Oops!</h1>
    <h2>An error has occurred</h2>
    <div>Error message: <span><%= exception.getMessage() %></span></div>
    <input type="button" value="Go home" class="back-btn" onclick="history.back(-1)">
</div>
</body>
</html>
