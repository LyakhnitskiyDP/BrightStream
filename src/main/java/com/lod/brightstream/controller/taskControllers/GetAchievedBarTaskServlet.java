package com.lod.brightstream.controller.taskControllers;

import com.lod.brightstream.controller.taskControllers.TaskBarController;
import com.lod.brightstream.BE.Task;
import com.lod.brightstream.BE.User;
import com.lod.brightstream.utils.TimeCalc;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/getAchievedTaskServlet")
public class GetAchievedBarTaskServlet extends HttpServlet {
    private static final String JSONtoFormat = "{\"achievedTaskName\":\"%s\", \"timeOfProcess\":\"%s\"}";
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        setUpJSONResponse(response);
        
        //input
        String direction = request.getParameter("direction");
        Object optBarController = getSessionAttribute(request, "barController");
       
        
        //Objects for getting next/previous task
        User user = (User) getSessionAttribute(request, "user");
        TaskBarController barController = optBarController == null ?
                                                              new TaskBarController(user.getId()) :
                                                              (TaskBarController) optBarController;
        
        switch (direction) {
            case "slide-right":
                setNextTaskWithBarController(response, barController);
                break;
            case "slide-left":
                setPreviousTaskWithBarController(response, barController);
                break;
            case "current":
                setCurrentTaskWithBarController(response, barController);
                break;
            default:
                setEmptyResponse(response);
                break;
        }
        
        request.getSession().setAttribute("barController", barController);
    }
    
    
    
    private void setUpJSONResponse(HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }
    
    private void setNextTaskWithBarController(HttpServletResponse response, TaskBarController barController) {
        Optional<Task> optTask = barController.getNextTask();
        
        if (optTask.isPresent()) 
            setTaskResponse(response, optTask.get());

    }
    
    private void setPreviousTaskWithBarController(HttpServletResponse response, TaskBarController barController) {
        Optional<Task> optTask = barController.getPreviousTask();
        
        if (optTask.isPresent())
            setTaskResponse(response, optTask.get());

    }
    
    private void setCurrentTaskWithBarController(HttpServletResponse response, TaskBarController barController) {
        Optional<Task> optTask = barController.getCurrentTask();
        
        if (optTask.isPresent())
            setTaskResponse(response, optTask.get());
        else
            setEmptyResponse(response);

    }
    
    
    

    
    private void setEmptyResponse(HttpServletResponse response) {
        String defaultJSON = String.format(JSONtoFormat, "Your future task", "Time it will take");
        setResponse(response, defaultJSON);
    }
    
    private void setTaskResponse(HttpServletResponse response, Task task) {
        Date taskCreationTime = task.getCreation_time();
        Date taskCompletionTime = task.getCompletion_time();
        String taskName = task.getName();
        String meaningfulTimePeriod = TimeCalc.getMeaningfulTimePeriod(taskCreationTime, taskCompletionTime);
        
        String taskJSON = String.format(JSONtoFormat, taskName, meaningfulTimePeriod);
        
        setResponse(response, taskJSON);
    } 
    
    private void setResponse(HttpServletResponse response, String JSONString) {
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.write(JSONString);
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(GetAchievedBarTaskServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
        
    private Object getSessionAttribute(HttpServletRequest request, String name) {
        return request.getSession().getAttribute(name);
    }
    
    
}
