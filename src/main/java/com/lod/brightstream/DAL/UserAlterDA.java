package com.lod.brightstream.DAL;

import com.lod.brightstream.BE.User;

public interface UserAlterDA {
    
    public boolean addUser(User user, String password);
    
    public boolean updateUser(User user);
}
