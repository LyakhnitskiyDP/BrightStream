package com.lod.brightstream.controller.userControllers;

import com.lod.brightstream.BE.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@WebServlet("/rememberUser")
public class RememberUserServlet extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        User user = (User) request.getSession().getAttribute("user");
        
        Cookie userIdCookie = new Cookie( "userId", Long.toString(user.getId()) );
        userIdCookie.setMaxAge(24 * 60 * 60);
        response.addCookie(userIdCookie);
        
        //request.getRequestDispatcher("/userPage").forward(request, response);
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    
}
