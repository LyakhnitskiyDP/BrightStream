package com.lod.brightstream.controller.taskControllers;

import com.lod.brightstream.BE.Task;
import com.lod.brightstream.BE.User;
import com.lod.brightstream.DAL.TaskDA;
import com.lod.brightstream.DAL.impl.TaskDAMySQL;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/addTask")
public class AddTaskServlet extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String taskName = request.getParameter("task-name");
        String content = request.getParameter("content");
        
        User user = (User) request.getSession().getAttribute("user");
        long user_id = user.getId();
        
        Task task = new Task();
        task.setName(taskName);
        task.setContent(content);
        task.setUser_id(user_id);
        
        TaskDA taskDA = new TaskDAMySQL();
        boolean result = taskDA.addTask(task);
        
        if (result) {
            request.getRequestDispatcher("home")
                   .forward(request, response);
        }
        
    }
    
}
