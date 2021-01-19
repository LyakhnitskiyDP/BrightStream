
package com.lod.brightstream.controller.userControllers;

import com.lod.brightstream.BE.User;
import com.lod.brightstream.DAL.UserDA;
import com.lod.brightstream.DAL.impl.UserDAMySQL;
import com.lod.brightstream.utils.HashUtilSHA512;
import java.io.IOException;

import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String furtherURL = "/login.jsp";
        String errorMessage = "";
         
        String email = request.getParameter("txt_email");
        String password = request.getParameter("txt_password");
        
        
        
        UserDA userDA = new UserDAMySQL();
        
        //If there is no such email, add an error message to the request object and return to the login page.
        if (!userDA.isEmailPresent(email)) { 
            errorMessage += "There is no such Email!";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher(furtherURL)
                   .forward(request, response);
            return;
        }
        
        Optional<User> optUser = userDA.getUser(email, HashUtilSHA512.hashPassword(password));
        
        if (optUser.isPresent()) {
            
            HttpSession session = request.getSession();
            session.setAttribute("user", optUser.get());
            furtherURL = "/index.jsp";
            request.removeAttribute("errorMessage");
        } else {       
            errorMessage += "The password is incorrect, try again.";
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("preEmail", email);
        }
        
        request.getRequestDispatcher(furtherURL)
               .forward(request, response);
        
    }
    
}
