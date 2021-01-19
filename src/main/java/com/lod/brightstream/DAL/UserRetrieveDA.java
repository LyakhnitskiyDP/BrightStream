package com.lod.brightstream.DAL;

import com.lod.brightstream.BE.User;
import java.util.Optional;

public interface UserRetrieveDA {
    
    public Optional<User> getUserById(long id);
    
    public Optional<User> getUser(String email, String password);
    
    public boolean isEmailPresent(String email);
    
}
