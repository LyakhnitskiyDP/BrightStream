$(document).ready( function () {
    $.ajax({
        url: 'getAchievedTaskServlet',
        data : {
            direction: 'current'
        },
        success: function(responseJSON) {
            $('#achieved-task-name').text(responseJSON.achievedTaskName);
            $('#achieved-task-time').text(responseJSON.timeOfProcess);
        }
        
    });
});

$(document).ready( function() {
    $('.slide-button').click(function() {
        $.ajax({
            url: 'getAchievedTaskServlet',
            data: { 
                direction: $(this).attr("id")
            },
            success: function(responseJSON) {
                $('#achieved-task-name').text(responseJSON.achievedTaskName);
                $('#achieved-task-time').text(responseJSON.timeOfProcess);
            }
        });
    });
});

