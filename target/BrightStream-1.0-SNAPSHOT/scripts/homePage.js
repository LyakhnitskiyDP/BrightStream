$(document).ready(addNewTaskForm);

function addNewTaskForm() {
    const formSpace = document.getElementById("task-details");

    formSpace.innerHTML = "<form action='addTask' method='POST' id='add-task-form' onsubmit='return validate()'>" +
                          "<input id='newTask-name' type='text' name='task-name' placeholder='Form name'>" +
                          "<textarea id='newTask-content' name='content' rows='4' cols='50'></textarea>" +
                          "<input id='newTask-submit' type='submit' value='Submit new Task' />" +
                          "</form>";
}

function validate() {
    var name = document.getElementById("newTask-name");
    var content = document.getElementById("newTask-content");

    if (name.value == null || name.value == "") {


        name.style.border = '3px solid crimson';
        name.focus();
        return false;
    } else {
        name.style.border = '1px solid black';
    }

    if (content.value == null || content.value == "") {

        content.style.border = '3px solid crimson';
        content.focus();
        return false;
    } else {
        content.style.border = '1px solid black';
    }
}

$(document).ready(function () {
   $(".task-button").click(function() {
       
       $.ajax({
           url: 'getJSONTaskServlet',
           data: {
             taskId: $(this).attr("id")  
           },
           success: function(taskJSON) {
               showTask(taskJSON.taskName, 
                        taskJSON.taskContent, 
                        taskJSON.taskDate, 
                        taskJSON.taskId);
           }
       });
       
   });
});

function showTask(name, content, date, id) {

    const taskSpace = document.getElementById("task-details");

    taskSpace.innerHTML = "<form action='alterTask' method='post'>"
            + "<h2 class='details-task-name'>" + name + "</h2>"
            + "<p class='details-task-content'>" + content + "</p>"
            + "<div class='details-task-date'>" + date + "</div>"
            + "<input type='hidden' name='task-id' value='" + id + "' />"
            + "<input class='achieve-button' name='submit-button' type='submit' value='Achieve' />"
            + "<input class='delete-button' name='submit-button' type='submit' value='Delete' />"
            + "</form>";
                
}

