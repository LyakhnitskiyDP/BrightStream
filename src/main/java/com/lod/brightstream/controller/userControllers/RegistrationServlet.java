package com.lod.brightstream.controller.userControllers;

import com.lod.brightstream.BE.User;
import com.lod.brightstream.utils.HashUtilSHA512;
import com.lod.brightstream.DAL.UserDA;
import com.lod.brightstream.DAL.impl.UserDAMySQL;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String name = request.getParameter("txt_name");
        String email = request.getParameter("txt_email");
        String password = request.getParameter("txt_password");
        
        UserDA userDA = new UserDAMySQL();
        
        if (userDA.isEmailPresent(email)) {
            
            request.setAttribute("errorMessage", "The email You entered is already in use");
            request.setAttribute("preName", name);
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
            return;
        } 
        
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        
        
        userDA.addUser(user, HashUtilSHA512.hashPassword(password));
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
    
}
