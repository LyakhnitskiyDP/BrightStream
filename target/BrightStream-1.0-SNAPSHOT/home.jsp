<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.stream.Collectors"%>
<%@page import="com.lod.brightstream.BE.User"%>
<%@page import="com.lod.brightstream.DAL.TaskDAMySQL"%>
<%@page import="com.lod.brightstream.DAL.TaskDA"%>
<%@page import="java.util.List"%>
<%@page import="com.lod.brightstream.BE.Task"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bright Stream | Home</title>
        
        <link rel="stylesheet" href="styles/main.css">
        <link rel="stylesheet" href="styles/home.css">
        
        <script type="text/javascript" src="https://code.jquery.com/jquery-1.10.2.js"></script>
        <script src="scripts/homePage.js"></script>
        
    </head>
    <body>
        
        <header>
            <!-- logo -->
          <div id="logo" style="display: inline">
            <a href="/BrightStream/index.jsp">
                <img src="images/logo.svg" alt="LOGO" />
            </a>
          </div>

          <!-- user navigation: <userName> -->
          <div id="userNav" style="display: inline">
            <a id="home_link" href="userPage" />${user.name}</a>
          </div>
        </header>
          
        
          <main>    
          <div class="container">
                
              <div id="tasks">
                  
                  <div id="add-task">
                      <input id="add-task-button" type="button" onclick="addNewTaskForm()" value="New Task +" />
                  </div>
                  
                  <c:forEach var="task" items="${listOfTasks}">
                             <div class="task">
                                <input type="button" class="task-button" value="${task.name}" id="${task.id}" />
                             </div>
                  </c:forEach>

              </div>
              
              <div id="task-details">
                  
              </div>
              
          </div>   
          </main>
    </body>
</html>
