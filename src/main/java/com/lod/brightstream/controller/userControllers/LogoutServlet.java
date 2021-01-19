package com.lod.brightstream.controller.userControllers;

import com.lod.brightstream.utils.CookieUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.addCookie(CookieUtil.getEmptyCookie("userId"));
        
        request.getSession().invalidate();
        
        request.getRequestDispatcher("/index.jsp").forward(request, response);
        
    }
    
}
