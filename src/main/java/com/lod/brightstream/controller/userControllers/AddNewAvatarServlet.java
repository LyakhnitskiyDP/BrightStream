package com.lod.brightstream.controller.userControllers;

import com.lod.brightstream.BE.User;
import com.lod.brightstream.DAL.UserDA;
import com.lod.brightstream.DAL.impl.UserDAMySQL;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@WebServlet("/addNewAvatar")
public class AddNewAvatarServlet extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        try {
            FileItem newAvatar = fileUpload.parseRequest(request).get(0);
            
            String pathToAvatars = getPathToAvatars(getServletContext());
            String extension = newAvatar.getContentType().split("/")[1]; // getContentType() returns "category/extension"
            
            
            //Uncomment it when deployed to a Linux server 
            //newAvatar.write(new File( pathToAvatars + "/" + user.getEmail() + "." + extension ));
            newAvatar.write(new File( "D:\\SOFT\\testApp\\BrightStream\\src\\main\\webapp\\images\\avatars\\" + user.getEmail() + "." + extension ));
            
            user.setAvatarPath(user.getEmail() + "." + extension);
            session.setAttribute("user", user);
            
            UserDA userDA = new UserDAMySQL();
            userDA.updateUser(user);
            
        } catch (FileUploadException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        request.getRequestDispatcher("/userPage").forward(request, response);
        
    }
    
    private static String getPathToAvatars(ServletContext context) {
        
        return context.getRealPath("images/avatars");
        
    }
    
}
