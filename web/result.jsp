<%--
  Created by IntelliJ IDEA.
  User: ndwannafly
  Date: 11/10/2021
  Time: 10:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="main.webapp.entity.Query" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="result" scope="session" class="main.webapp.entity.Result"></jsp:useBean>
<% result.setQueries((List<Query>) session.getServletContext().getAttribute("queries"));
    System.out.println(session.getServletContext().getAttribute("queries"));
%>
<!DOCTYPE html>
<html>
<head>
    <title>Result</title>
    <link rel="stylesheet" type="text/css" href="./css/respond.css">
</head>
<body>
<header>
    <h2>Queries Result</h2>
</header>
<div>
    <p class="col-75 error" id="x-err"><?= $error ?></p>
</div>
<div>
    <table>
        <thead><tr>
            <th>Point x</th>
            <th>Point y</th>
            <th>Radius</th>
            <th>Result (Point lies inside the area?)</th>
            <th>Execution time</th>
            <th>Creation time</th>
        </tr></thead>
        <tbody>
        <% for(Query query: result.getQueries()) { %>
            <tr class="row">
                <td><%=query.getX()%></td>
                <td><%=query.getY()%></td>
                <td><%=query.getR()%></td>
                <td><%=query.getResult()%></td>
                <td><%=query.getExecutionTime()%></td>
                <td><%=query.getCreationTime()%></td>
            </tr>
        <%}%>
        </tbody>
    </table>
</div>
<div id="wrapper">
    <button class="back-btn" onclick="goBack()">Back</button>
</div>
<script>
    function goBack() {
        window.location.replace("/lab_2_war_exploded");
    }
</script>
</body>
</html>