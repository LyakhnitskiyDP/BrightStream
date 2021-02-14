<%-- 
    Document   : userPage
    Created on : Sep 25, 2020, 6:01:40 PM
    Author     : Dimitry
--%>

<%@page import="java.sql.Timestamp"%>
<%@page import="com.lod.brightstream.BE.User"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="util" uri="/WEB-INF/tlds/util" %>
<%
    Date currentDate = new Date();
    Timestamp current = new Timestamp(currentDate.getTime());
    pageContext.setAttribute("current", current);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${user.name}</title>

        <link rel="stylesheet" href="styles/main.css" />
        <link rel="stylesheet" href="styles/userPage.css" />

        <script type="text/javascript" src="scripts/initUserPage.js"></script>
        
        <script type="text/javascript" src="https://code.jquery.com/jquery-1.10.2.js"></script>
        <script type="text/javascript" src="scripts/achievedTasksBar.js"></script>
        <script type="text/javascript" src="scripts/rememberUserScript.js"></script>

    </head>
    <body onload="initTimeField()">

        <header>
            <!-- logo -->
            <a href="/BrightStream/index.jsp">
            <div id="logo" style="display: inline">
                <img src="images/logo.svg" alt="LOGO" />
            </div>
            </a>



            <!-- user navigation: <Home> -->
            <form action="" method="get" id="userNav" style="display: inline">
                <a id="home_link" href="home" />Home</a>
            </form>
        </header>

        <div class="container">

            <div id="avatar-container">

                <form id="form-new-avatar" method="post" action="addNewAvatar" enctype='multipart/form-data' onchange="submitAvatarForm();" >
                    <label for="input-file">
                        <img class="avatar-top" src="images/avatars/${user.avatarPath}" alt="avatar" />
                        <img class="avatar-bottom" src="images/add-svg.svg" />
                    </label>

                    <input id="input-file" type="file" name="new-avatar-file" accept="image/png, image/jpeg, image/gif" style="display: none;" >
                </form>

            </div>

            <p>Good <span id="timeOfTheDay"></span> ${user.name}!</p>
            <p>We've been together for <util:TimePeriod initialDate="${user.registrationDate}" finalDate="${current}" />!</p>

            <div id="options-bar">

                <a id="configure-profile-link" class="option" href="#">Configure Profile</a>

                <a id="getTaskLog-link" class="option" href="userPage/fullTaskLog">Get full task Log</a>

                <a id="rememberUser-link" class="option">Remember User</a>

                <a id="logout-link" class="option" href="logout">Logout</a>

            </div>

            <div id="task-stats">

                <div class="count">
                    <label>Current Tasks</label>
                    <span id="current-tasks-stat">${currentTasksCount}</span>
                </div>

                <div class="count">
                    <label>Achieved Tasks</label>
                    <span id="achieved-tasks-stat">${achievedTasksCount}</span>
                </div>

            </div>

            <div id="medal">
                <img class="star-img" src="images/black-star.svg" />
                <p>Your accomplished tasks</p>
                <img class="star-img" src="images/black-star.svg" />
            </div>
            <div id="achieved-tasks-bar">

                <button class="slide-button" id="slide-left" value="left">
                    <img src="https://www.flaticon.com/svg/static/icons/svg/126/126492.svg">
                </button>

                <div id="achieved-task-content">
                    <p id="achieved-task-name"></p>
                    <p id="achieved-task-time"></p>
                </div>

                <button class="slide-button" id="slide-right" value="right">
                    <img src="https://www.flaticon.com/svg/static/icons/svg/126/126490.svg">
                </button>

            </div>

        </div>

    </body>
</html>
