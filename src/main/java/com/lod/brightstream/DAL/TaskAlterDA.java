package com.lod.brightstream.DAL;

import com.lod.brightstream.BE.Task;

public interface TaskAlterDA {
    public boolean addTask(Task task);
    
    public boolean achieveTask(long id);
    
    public boolean deleteTask(long id);
}
