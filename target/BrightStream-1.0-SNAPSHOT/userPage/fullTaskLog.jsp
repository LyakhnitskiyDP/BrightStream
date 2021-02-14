
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${user.name} | Tasks</title>
        
        <link rel="stylesheet" href=<c:url value="/styles/main.css" /> />
        <link rel="stylesheet" href=<c:url value="/styles/taskLog.css" /> />
        
        <script type="text/javascript" src="https://code.jquery.com/jquery-1.10.2.js"></script>
        <script type="text/javascript" src=<c:url value="/scripts/jQuerySortPlugin.js" />></script>
        <script type="text/javascript" src=<c:url value="/scripts/taskLogScript.js" />></script>
        <script type="text/javascript" src=<c:url value="/scripts/taskLogSortScript.js" />></script>

    </head>
    <body>
        
        <header>
            <!-- logo -->
          <div id="logo" style="display: inline">
            <a href="/BrightStream/index.jsp">
                <img src=<c:url value="/images/logo.svg" /> alt="LOGO" />
            </a>
          </div>

          <!-- user navigation: <userName> -->
          <form action="" method="get" id="userNav" style="display: inline">
              <a id="home_link" href=<c:url value="/userPage"/> />${user.name}</a>
          </form>
        </header>
          
          <main class="task-log">
              
              <table id="task-table">
                  
                  <tr>
                      <th class="sortable"><nobr>Name<img class="img-triangle" src="../images/triangle.svg" /></nobr></th>
                      <th>Content</th>
                      <th class="sortable"><nobr>Completed<img class="img-triangle" src="../images/triangle.svg" /></nobr></th>
                      <th class="sortable"><nobr>Creation Date<img class="img-triangle" src="../images/triangle.svg" /></nobr></th>
                      <th class="sortable"><nobr>Completion Date<img class="img-triangle" src="../images/triangle.svg" /></nobr></th>
                  </tr>
                  
                  <c:forEach var="taskItem" items="${listOfTasks}">
                    <tr>  
                      <td>${taskItem.name}</td>
                      <td class="task-log-content">${taskItem.content}</td>

                      <c:choose>
                          <c:when test="${taskItem.completed}">
                              <td>Yes</td>
                          </c:when>
                              
                          <c:otherwise>
                            <td>No</td>
                          </c:otherwise>
                      </c:choose>

                      <td>${taskItem.creation_time}</td>
                      
                      <c:choose>
                          <c:when test="${taskItem.completion_time != null}">
                              <td>${taskItem.completion_time}</td>
                          </c:when>
                              
                          <c:otherwise>
                              <td>&mdash;</td>
                          </c:otherwise>
                      </c:choose>
                      
                    </tr>  
                  </c:forEach>
                  
              </table>
              
              <a href="downloadTasksXLSX" style="text-decoration: none;">
              <div id="download-xlsx">
                  <button>Download</button>
                  <img src=<c:url value="/images/xlsx_icon.svg" />>
              </div>
              </a>
          </main>
        
    </body>
</html>
