package com.lod.brightstream.controller.taskControllers;

import com.lod.brightstream.BE.Task;
import com.lod.brightstream.BE.User;
import com.lod.brightstream.DAL.TaskDA;
import com.lod.brightstream.DAL.impl.TaskDAMySQL;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;


@WebServlet("/userPage/downloadTasksXLSX")
public class DownloadTasksXLSXServlet extends HttpServlet {
    public static final int NUMBER_OF_COLUMS = 5;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("My Tasks");
        Row row = sheet.createRow(0);
        
        User user = (User) getObjectFromSession(request, "user");
        
        setUpHeaders(row);
        setUpContent(sheet, user.getId());
        
        response.setHeader("content-disposition", "attachment; filename=MyTasks.xls");
        response.setHeader("cache-control", "no-chache");
        
        try (OutputStream out = response.getOutputStream()) {
            workbook.write(out);
        } catch (IOException ioException) {
            log("Workbook could not be loaded" + ioException);
        }
        
    }
    
    private void setUpHeaders(Row row) {
        
        List<String> headers = List.of("Name",
                                       "Content",
                                       "Completed",
                                       "Creation Date",
                                       "Completion Date");
        
        for ( int i = 0; i < headers.size(); i++ ) {
            row.createCell(i).setCellValue(headers.get(i));
        }  
    }
    
    private void setUpContent(Sheet sheet, long userId) {
        
        TaskDA taskDA = new TaskDAMySQL();
        List<Task> listOfTasks = taskDA.getTasksByUserId(userId);
        
        Row row;
        for (int i = 0; i < listOfTasks.size(); i++) {
            row = sheet.createRow(i + 1);
            
            setUpRow(row, listOfTasks.get(i));
        }
        
    }
    
    private void setUpRow(Row row, Task task) {
        
        List<String> taskFields = List.of(
                                          task.getName(),
                                          task.getContent().replaceAll("<br />", " "),
                                          task.isCompleted() ? "Yes" : "No",
                                          task.getCreation_time().toString(),
                                          task.getCompletion_time() == null ? "-" : task.getCompletion_time().toString()
                                         );
        
        for (int i = 0; i < NUMBER_OF_COLUMS; i++) {
            row.createCell(i).setCellValue(taskFields.get(i));
        }
        
    }
    
    private Object getObjectFromSession(HttpServletRequest request, String name) {
        return request.getSession().getAttribute(name);
    }
    
}
