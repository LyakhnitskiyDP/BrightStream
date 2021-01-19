package com.lod.brightstream.DAL;

import com.lod.brightstream.BE.Task;
import java.util.List;
import java.util.Optional;

public interface TaskRetrieveDA {
    
    public Optional<Task> getTaskById(long id);
    public List<Task> getTasksByUserId(long userIdd);
    
    public Optional<Task> getNextAchievedTaskWithUserId(long userId, long task_id_offset);  
    public Optional<Task> getPreviousAchievedTaskWithUserId(long userId, long task_id_offset);
    
    public long getFinishedTaskCount(long userId);
    public long getCurrentTaskCount(long userId);  
}
