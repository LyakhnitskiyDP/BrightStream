/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lod.brightstream.BE;

import java.io.Serializable;

import java.sql.Timestamp;


public class User implements Serializable {
    private long id;
    private String name;
    private String email;
    private Timestamp registrationDate;
    private String avatarPath;
    
    public User() {
        
    }
    
    public User(long id, String name, Timestamp registration_date) {
        this.name = name;
        this.id = id;
        this.registrationDate = registration_date;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registration_date) {
        this.registrationDate = registration_date;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
        public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
    
    
    
}
