package com.lod.brightstream.controller.taskControllers;

import com.lod.brightstream.BE.Task;
import com.lod.brightstream.DAL.TaskDA;
import com.lod.brightstream.DAL.impl.TaskDAMySQL;
import java.sql.Timestamp;
import java.util.Optional;

public final class TaskBarController {
    private long userId;
    private long currentTaskId;
    
    private TaskDA taskDA = new TaskDAMySQL();
    
    public TaskBarController() {
        
    }
    
    public TaskBarController(long userId) {
        this.userId = userId;
        initOffsetId();
    }
    
    public void initOffsetId() {
        
        //Set the first task id as a new pointer if there is a task, set empty otherwise
        Optional<Task> optTask = taskDA.getNextAchievedTaskWithUserId(userId, 0L);
        if (optTask.isPresent())
            currentTaskId = optTask.get().getId();
        else 
            currentTaskId = 0;
        
    }
    
    public Optional<Task> getCurrentTask() {
        return taskDA.getTaskById(currentTaskId);
    }
    
    
    public Optional<Task> getNextTask() {
        Optional<Task> optTask = taskDA.getNextAchievedTaskWithUserId(userId, currentTaskId);
        
        if (optTask.isPresent()) 
            currentTaskId = optTask.get().getId();
           
        return optTask;
    }
    
    public Optional<Task> getPreviousTask() { 
        Optional<Task> optTask = taskDA.getPreviousAchievedTaskWithUserId(userId, currentTaskId);
        
        if (optTask.isPresent()) 
            currentTaskId = optTask.get().getId();

        return optTask;
    }
    
    
    public void setcurrentTaskId(Long offSet) {
        currentTaskId = offSet;
    }
    
    public long getcurrentTaskId() {
        return this.currentTaskId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public void setTaskDA(TaskDA taskDA) {
        this.taskDA = taskDA;
    }
    
    
}
