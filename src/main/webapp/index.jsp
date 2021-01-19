<%--
    Document   : index
    Created on : Sep 5, 2020, 12:02:46 PM
    Author     : Dimitry
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bright Stream</title>

        <link rel="stylesheet" href=<c:url value="styles/main.css" />>
        <link rel="stylesheet" href=<c:url value="styles/index.css" />>
    </head>
    <body>

        <!-- header -->
        <header>

          <!-- logo -->
          <div id="logo" style="display: inline">
            <img src="images/logo.svg" alt="LOGO" />
          </div>

          <!-- user navigation: Log in OR Hello, <userName> -->
          <form action="/BrightStream/login.jsp" method="get" id="userNav" style="display: inline">
            
              <c:choose>
              <c:when test="${user == null}">
                <input id="login_label" type="submit" value="Log In" />
              </c:when>
              
              <c:otherwise>
                  <a id="home_link" href="home" />Home</a>
              </c:otherwise>
              </c:choose>
              
          </form>
                
          

        </header>

        <!-- main section -->
        <main>
          <div class="container">

            <div class="box-left">
              <img src="images/logo_text.svg" alt="LOGO" />

              <p>One of the best task-management web systems currently on the market.<br /> Regain clarity and calmness by getting all those tasks out of your head and onto your to-do list <br /> (no matter where you are or what device you use).</p>
            </div>

            <div class="box-center">
              <p>Add a new task.</p>
              <img src="images/showcase/showcase_1.png">
              
              <p>Manage unfinished tasks.</p>
              <img src="images/showcase/showcase_2.png">
              
              <p>Ger reminded how many tasks you have accomplished and how much are there to be finished.</p>
              <img src="images/showcase/showcase_3.png">
              
              <p>Get full log of tasks with ability to download it in XLSX format.</p>
              <img src="images/showcase/showcase_4.png">
            </div>

            <div class="box-right">
              
                <div class="position">
                    <img src="images/feather.svg">
                    <br />
                    <span>Lightweight</span>
                </div>
                
                <div class="position">
                    <img src="images/locked-shield.svg">
                    <br />
                    <span>Secure</span>
                </div>
                
                <div class="position">
                    <img src="images/cogs.svg">
                    <br />
                    <span>Working</span>
                </div>
                
            </div>

          </div>
        </main>

    </body>
</html>
