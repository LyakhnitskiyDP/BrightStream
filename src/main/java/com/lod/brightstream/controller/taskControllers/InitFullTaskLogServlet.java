package com.lod.brightstream.controller.taskControllers;

import com.lod.brightstream.BE.Task;
import com.lod.brightstream.BE.User;
import com.lod.brightstream.DAL.TaskDA;
import com.lod.brightstream.DAL.impl.TaskDAMySQL;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/userPage/fullTaskLog")
public class InitFullTaskLogServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        initTaskList(request);
        request.getRequestDispatcher("fullTaskLog.jsp").forward(request, response);
        
    }
    
    private void initTaskList(HttpServletRequest request) {
        
        TaskDA taskDA = new TaskDAMySQL();
        User user = (User) getObjectFromSession(request, "user");
        
        List<Task> listOfTasks = taskDA.getTasksByUserId(user.getId());    
        request.setAttribute("listOfTasks", listOfTasks);
        
    }
    
    private Object getObjectFromSession(HttpServletRequest request, String name) {
        return request.getSession().getAttribute(name);
    }

    
}
