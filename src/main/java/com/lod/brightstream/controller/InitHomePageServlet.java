package com.lod.brightstream.controller;

import com.lod.brightstream.BE.Task;
import com.lod.brightstream.BE.User;
import com.lod.brightstream.DAL.TaskRetrieveDA;
import com.lod.brightstream.DAL.impl.TaskDAMySQL;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class InitHomePageServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TaskRetrieveDA taskDA = new TaskDAMySQL();
        User user = (User) request.getSession().getAttribute("user");
        List<Task> listOfTasks = taskDA.getTasksByUserId(user.getId()).stream()
                .filter(task -> !task.isCompleted())
                .map(task -> {
                    task.setContent(task.getContent().replaceAll("['\"]", "\\\\&#39;"));
                    task.setName(task.getName().replaceAll("['\"]", "\\\\&#39;"));
                    return task;
                })
                .collect(Collectors.toList());

        request.setAttribute("listOfTasks", listOfTasks);

        request.getRequestDispatcher("home.jsp")
               .forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
}
