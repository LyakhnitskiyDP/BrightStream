package com.lod.brightstream.DAL.impl;

import com.lod.brightstream.BE.Task;
import com.lod.brightstream.DAL.ConnectionPool;
import com.lod.brightstream.DAL.TaskDA;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TaskDAMySQL implements TaskDA {

    @Override
    public Optional<Task> getTaskById(long id) {
        return getTaskWithSqlQuery("SELECT * FROM task WHERE task_id=" + id + ";");
    }
    
    @Override
    public Optional<Task> getNextAchievedTaskWithUserId(long userId, long task_id_offset) {
        return getTaskWithSqlQuery(
                String.format(
                "SELECT * FROM task WHERE completed=true AND user_id=%d AND task_id>%d ORDER BY task_id;", userId, task_id_offset
                )
        );
    }

    @Override
    public Optional<Task> getPreviousAchievedTaskWithUserId(long userId, long task_id_offset) {
        return getTaskWithSqlQuery(
                String.format(
                "SELECT * FROM task WHERE completed=true AND user_id=%d AND task_id<%d ORDER BY task_id DESC;", userId, task_id_offset
                )
        );
    }
    
    @Override
    public boolean deleteTask(long id) {
        
        String sqlUpdate = "DELETE FROM task WHERE task_id=?";
        
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlUpdate)) {
            
            statement.setLong(1, id);
            
            return statement.executeUpdate() == 1;
            
        } catch (SQLException sqlException) {
            
            sqlException.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean achieveTask(long id) {
        
        String sqlUpdate = "UPDATE task SET completed=true, completion_time=NOW() WHERE task_id=?";
        
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlUpdate)) {
            
            statement.setLong(1, id);
            
            return statement.executeUpdate() == 1;
            
        } catch (SQLException sqlException) {
            
            sqlException.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Task> getTasksByUserId(long id) {
        
        String sqlQuery = "SELECT * FROM task WHERE user_id=?;";
        
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            
            statement.setLong(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            List<Task> listOfTasks = new ArrayList<>();
            
            while (resultSet.next()) {
                Task task = new Task();
                
                loadTaskFromResultSet(task, resultSet);
                
                listOfTasks.add(task);
            }
            
            return listOfTasks;
            
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean addTask(Task task) {
        
        String sqlUpdate = "INSERT INTO task (user_id, name, content, completed) "
                         + "VALUES (?, ?, ?, ?);";
        
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlUpdate)) {
            
            statement.setLong(1, task.getUser_id());
            statement.setString(2, task.getName());
            statement.setString(3, task.getContent().replaceAll("[\r\n]+", "<br />"));
            statement.setBoolean(4, false);
            
            return statement.executeUpdate() == 1;
            
        } catch (SQLException sqlException) {
            
            sqlException.printStackTrace();
            return false;
        }
        
    }
    
    @Override
    public long getCurrentTaskCount(long userId) {
        return getTaskCount(userId, false);
    }
    
    @Override
    public long getFinishedTaskCount(long userId) {
        return getTaskCount(userId, true);
    }
    
    private long getTaskCount(long userId, boolean finished) {
        
        String sqlQuery = "SELECT count(task_id) AS count FROM task WHERE completed=? AND user_id=? GROUP BY user_id;";
        
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            
            statement.setBoolean(1, finished);
            statement.setLong(2, userId);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (!resultSet.next()) return 0;
            return resultSet.getLong("count");
            
        } catch (SQLException sqlException) {
            
            sqlException.printStackTrace();
            return 0;
        }
    }
    
    private Optional<Task> getTaskWithSqlQuery(String sqlQuery) {
        
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) return Optional.empty();
            
            Task task = new Task();
            
            loadTaskFromResultSet(task, resultSet);
            
            return Optional.of(task);
        } catch (SQLException ex) {
            Logger.getLogger(TaskDAMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return Optional.empty();
        }
        
    }
    
    private void loadTaskFromResultSet(Task task, ResultSet resultSet) throws SQLException {
            
            task.setCompleted(resultSet.getBoolean("completed"));
            task.setCreation_time(new Date(resultSet.getTimestamp("creation_time").getTime()));
            task.setCompletion_time(resultSet.getTimestamp("completion_time") != null ?
                                    new Date(resultSet.getTimestamp("completion_time").getTime()) 
                                    : null);
            task.setContent(resultSet.getString("content"));
            task.setId(resultSet.getLong("task_id"));
            task.setName(resultSet.getString("name"));
            task.setUser_id(resultSet.getLong("user_id"));
    }
    
    
}
