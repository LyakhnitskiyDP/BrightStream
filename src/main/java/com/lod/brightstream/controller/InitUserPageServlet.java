package com.lod.brightstream.controller;

import com.lod.brightstream.BE.User;
import com.lod.brightstream.DAL.TaskDA;
import com.lod.brightstream.DAL.impl.TaskDAMySQL;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/userPage")
public class InitUserPageServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
        Date currentDate = new Date();
        Timestamp current = new Timestamp(currentDate.getTime());
        request.setAttribute("current", current);
        
        TaskDA taskDA = new TaskDAMySQL();
        
        User user = (User) request.getSession().getAttribute("user");
        
        long currentTasksCount = taskDA.getCurrentTaskCount(user.getId());
        long achievedTasksCount = taskDA.getFinishedTaskCount(user.getId());
        
        request.setAttribute("currentTasksCount", currentTasksCount);
        request.setAttribute("achievedTasksCount", achievedTasksCount);
        
        request.getRequestDispatcher("userPage.jsp").forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    
}
