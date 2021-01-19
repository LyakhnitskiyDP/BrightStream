
import com.lod.brightstream.BE.Task;
import com.lod.brightstream.BE.User;
import com.lod.brightstream.DAL.TaskRetrieveDA;
import com.lod.brightstream.DAL.impl.TaskDAMySQL;
import com.lod.brightstream.controller.taskControllers.TaskBarController;
import com.lod.brightstream.utils.TimeCalc;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getJSONTaskServlet")
public class GetJSONTaskServlet extends HttpServlet {
    private static final String JSONtoFormat = 
            "{\"taskName\":\"%s\", \"taskDate\":\"%s\", \"taskContent\":\"%s\", \"taskId\":\"%s\"}";
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setUpJSONResponse(response);
        
        TaskRetrieveDA taskDA = new TaskDAMySQL();
        Optional<Task> task = taskDA.getTaskById(
                Long.parseLong(request.getParameter("taskId"))
        );
        
        if (task.isPresent())
            setTaskResponse(response, task.get());
        else
            Logger.getLogger(GetJSONTaskServlet.class.getName())
                  .log(Level.SEVERE, null, "Tried to retreive non-existing task");
        
        
    }
    
    
    private void setUpJSONResponse(HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }
    
    
    private void setTaskResponse(HttpServletResponse response, Task task) {
        
        String taskJSON = String.format(JSONtoFormat, task.getName(), 
                                                      task.getCreation_time(), 
                                                      task.getContent(), 
                                                      task.getId());
        
        setResponse(response, taskJSON);
    }
    
    private void setResponse(HttpServletResponse response, String JSONString) {
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.write(JSONString);
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(GetJSONTaskServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
        
    private Object getSessionAttribute(HttpServletRequest request, String name) {
        return request.getSession().getAttribute(name);
    }
    

    
}