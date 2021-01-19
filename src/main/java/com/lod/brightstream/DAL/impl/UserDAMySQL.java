package com.lod.brightstream.DAL.impl;

import com.lod.brightstream.utils.HashUtilSHA512;
import com.lod.brightstream.BE.User;
import com.lod.brightstream.DAL.ConnectionPool;
import com.lod.brightstream.DAL.UserDA;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class UserDAMySQL implements UserDA {
    

    /*
     * Adds a user to a database. If the operation was successful, returns true, false otherwise.
     * 
     */
    @Override
    public boolean addUser(User user, String password) {
        
        String sqlUpdate = "INSERT INTO user "
                         + "(user_name, email, password) "
                         + "VALUES (?, ?, ?);";
        
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareCall(sqlUpdate)) {
            
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, HashUtilSHA512.hashPassword(password));
            
            int result = statement.executeUpdate();
            
            return result == 1;
            
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }
    
    /**
     * Returns Optional with an user instance if there is one, or empty Optional otherwise.
     * 
     * @param id
     * @return Optional\<User\>
     */
    @Override
    public Optional<User> getUserById(long id) {
        
        String sqlUpdate = "SELECT * FROM user WHERE user_id=?;";
        
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareCall(sqlUpdate)) {
            
            statement.setLong(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            User user = null;
            
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                user.setRegistrationDate(resultSet.getTimestamp("registration_time"));
                
                String avatarPath = resultSet.getString("avatar_path");
                user.setAvatarPath(avatarPath == null ? "default.png" : avatarPath);
                
            } else {
                return Optional.empty();
            }
            
            if (resultSet.next()) throw new RuntimeException("Two users with identical id found");
            
            return Optional.ofNullable(user);
            
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return Optional.empty();
        } 
    }
    
    /**
     * Return Optional\<User\> if there is such, or empty Optional otherwise.
     * 
     * @param email
     * @param password
     * @return Optional\<User\>
     */

    @Override
    public Optional<User> getUser(String email, String password) {
        
        String sqlUpdate = "SELECT * FROM user WHERE email=? AND password=?;";
        
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareCall(sqlUpdate)) {
            
            statement.setString(1, email);
            statement.setString(2, HashUtilSHA512.hashPassword(password));
            
            ResultSet resultSet = statement.executeQuery();
            
            User user = null;
            
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                user.setRegistrationDate(resultSet.getTimestamp("registration_time"));
                
                String avatarPath = resultSet.getString("avatar_path");
                user.setAvatarPath(avatarPath == null ? "default.png" : avatarPath);
                
            } else {
                return Optional.empty();
            }
            
            if (resultSet.next()) throw new RuntimeException("Two users with identical email found");
            
            return Optional.ofNullable(user);
            
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Returns true if the given email is present, false otherwise.
     * 
     * @param email
     * @return boolean
     */
    
    @Override
    public boolean isEmailPresent(String email) {
        
        String sqlUpdate = "SELECT * FROM user WHERE email=?;";
        
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareCall(sqlUpdate)) {
            
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("SQL exception");
        }
    }
    
    @Override
    public boolean updateUser(User user) {
        
        String sqlUpdate = "UPDATE user SET "
                         + "user_name=?, "
                         + "email=?, "
                         + "avatar_path=?"
                         + "WHERE user_id=?";
        
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareCall(sqlUpdate)) {
            
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getAvatarPath());
            
            statement.setLong(4, user.getId());
            
            int result = statement.executeUpdate();
            
            return result == 1;
            
            
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("SQL exception");
        }
        
    }
    
    
}
