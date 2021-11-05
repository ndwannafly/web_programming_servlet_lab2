<%--
  Created by IntelliJ IDEA.
  User: ndwannafly
  Date: 05.11.2021
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Web_lab2</title>
  <script src="js/jquery.js" type="text/javascript"></script>
  <script type="text/javascript" src="js/signup.js"></script>
</head>
<body>
<h3>Sign Up</h3>
<div >
  <h3>username</h3>
  <label>
    <textarea  id="username_signup" rows="1" cols="5" maxlength="5" style="margin-left: 0px; margin-top: 5px;" ></textarea>
  </label>
</div>
<div>
  <h3>password</h3>
  <label>
    <textarea id="password_signup" rows="1" cols="5" maxlength="5" style="margin-left: 0px; margin-top: 5px;" ></textarea>
  </label>
</div>
<div id="button_signup">
  <input class="button" type="submit" value="Submit" id="signup-button">
</div>
<a href="login.jsp">Login</a>
</body>
</html>