package com.lod.brightstream.controller;

import com.lod.brightstream.BE.User;
import com.lod.brightstream.DAL.UserDA;
import com.lod.brightstream.DAL.impl.UserDAMySQL;
import com.lod.brightstream.utils.CookieUtil;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class InitializerServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Optional<String> userId = CookieUtil.getCookieValue(request.getCookies(), "userId");
        
        if (userId.isPresent())
            initializeUser(userId.get(), request);

        
        request.getRequestDispatcher("/index.jsp").forward(request, response);
        
    }
    
    private void initializeUser(String userIdString, HttpServletRequest request) {
        
        long userId = Long.parseLong(userIdString);
        
        UserDA userDA = new UserDAMySQL();
        Optional<User> user = userDA.getUserById(userId);
        
        if (user.isPresent()) 
            request.getSession().setAttribute("user", user.get());
        else 
            System.err.println("Non-existing user with the given cookie value.");

    }
    
}
