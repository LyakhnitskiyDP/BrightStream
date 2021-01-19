package com.lod.brightstream.controller.taskControllers;

import com.lod.brightstream.DAL.TaskDA;
import com.lod.brightstream.DAL.impl.TaskDAMySQL;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/alterTask")
public class alterTaskServlet extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String submitButton = request.getParameter("submit-button");
        Long taskId = Long.parseLong(request.getParameter("task-id"));
        
        String furtherURL = "";
        TaskDA taskDA = new TaskDAMySQL();
        
        if (submitButton.equals("Achieve")) {
            
            taskDA.achieveTask(taskId);
            furtherURL = "home"; 
        }
        
        if (submitButton.equals("Delete")) {
            
            taskDA.deleteTask(taskId);
            furtherURL = "home";
        }
        
        request.getRequestDispatcher(furtherURL)
               .forward(request, response);
        
    }
    
}
