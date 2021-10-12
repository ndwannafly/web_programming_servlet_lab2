<%--
  Created by IntelliJ IDEA.
  User: ndwannafly
  Date: 11/10/2021
  Time: 10:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Web_lab2</title>
  <script type="text/javascript" charset="UTF-8"
          src="https://cdn.jsdelivr.net/npm/jsxgraph/distrib/jsxgraphcore.js"></script>
  <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/jsxgraph/distrib/jsxgraph.css"/>

  <script type="text/javascript" charset="UTF-8" src="assets/codemirror-5.62.2/lib/codemirror.js"></script>
  <script type="text/javascript" charset="UTF-8" src="assets/codemirror-5.62.2/addon/selection/active-line.js"></script>
  <script type="text/javascript" charset="UTF-8" src="assets/codemirror-5.62.2/mode/javascript/javascript.js"></script>
  <link rel="stylesheet" href="assets/codemirror-5.62.2/lib/codemirror.css">

  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=JetBrains+Mono:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800&display=swap" rel="stylesheet">

  <script type="text/javascript" src="js/index.js"></script>
  <link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
<div id="header" style="height: 40px; padding-top: 10px">
  <h3 id="intro">
    Hi, I'm Nguyen Ngoc Duc! I'm currently flutter developer and competitive programmer
  </h3>
</div>
<div id="main" style="margin-top: 15px">
  <div id="left">
    <div id="advanced-content">
      <label for="current">Current points</label>
      <textarea id="current"></textarea>

    </div>

    <div id="simple-content" style="display: block">
      <p>Enter X: </p>
      <label>
        <textarea  id="x_value" rows="1" cols="5" maxlength="5" style="margin-left: 0px; margin-top: 5px;" required autofocus ></textarea>
      </label>
        <p>Select Y: </p>
        <div class="y-button-line">
          <input type="radio" id="y_-2" name="y_value" value=-2 checked>
          <label for="y_-2">-2</label>
          <input type="radio" id="y_-1.5" name="y_value" value=-1.5>
          <label for="y_-1.5">-1.5</label>
          <input type="radio" id="y_-1" name="y_value" value=-1>
          <label for="y_-1">-1</label><br>
          <input type="radio" id="y_-0.5" name="y_value" value=-0.5>
          <label for="y_-0.5">-0.5</label>
          <input type="radio" id="y_0" name="y_value" value=0>
          <label for="y_0">0</label>
          <input type="radio" id="y_0.5" name="y_value" value=0.5>
          <label for="y_0.5">0.5</label>
          <input type="radio" id="y_1" name="y_value" value=1>
          <label for="y_1">1</label>
          <input type="radio" id="y_1.5" name="y_value" value=1.5>
          <label for="y_1.5">1.5</label>
          <input type="radio" id="y_2" name="y_value" value=2>
          <label for="y_2">2</label>
        </div>
        <br>
        <p>
          Select R:
        </p>
        <div class="r-button-line">
          <input type="radio" id="r_1" name="r_value" value="1" checked>
          <label for="r_1">1</label><br>
          <input type="radio" id="r_2" name="r_value" value="2">
          <label for="r_2">2</label><br>
          <input type="radio" id="r_3" name="r_value" value="3">
          <label for="r_3">3</label><br>
          <input type="radio" id="r_4" name="r_value" value="4">
          <label for="r_4">4</label><br>
          <input type="radio" id="r_5" name="r_value" value="5">
          <label for="r_5">5</label><br>
        </div>

        <div class="button-group">
          <input class="button" type="submit" value="Submit" id="submit-button">
        </div>
    </div>
  </div>
  <div id="center">
    <div id="box1" class="jxgbox" style="width: 700px; height: 700px;"></div>
  </div>
  <div id="right">
    <div class="button-group">
      <button class="button checked" id="overview">Overview</button>
      <button class="button" id="constraint">Constraint</button>
      <button class="button" id="notation">Notation</button>
    </div>
    <div class="content" id="notationContent" style="display: none">
      <p>Click on the plane to show the point. There are 2 cases:</p>
      <ul>
        <li>Red point - the point is outside the chosen area.</li>
        <li>Green point - the point is in the chosen area.</li>
      </ul>
    </div>

    <div class="content" id="constraintContent" style="display: none">
      <p>Constraints:</p>
      <ul>
        <li>X: (-3 ... 5)</li>
        <li>Y: {-2, -1.5, -1, -0.5, 0, 0.5, 1, 1.5, 2}</li>
        <li>R: {1, 2, 3, 4, 5}</li>
      </ul>
    </div>

    <div class="content" id="overviewContent">
      <p>Author: Nguyen Ngoc Duc</p>
      <p>Group: P3233</p>
      <p>Github: <a href="https://github.com/ndwannafly">github.com/ndwannafly</a></p>
      <p>Contact:</p>
      <ul>
        <li>
          Gmail:
          <a href="mailto:ngocducforwork@gmail.com">ngocducforwork@gmail.com</a>
        </li>
        <li>
          Telegram:
          <a href="https://t.me/nd_wannafly">
            @nd_wannafly
          </a>
        </li>
      </ul>
    </div>
  </div>
</div>
</body>
</html>
