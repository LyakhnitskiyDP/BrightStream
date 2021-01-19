$(document).ready( function() {
   //Select all content that is big enough and shrink it to an expandable link
   $('.task-log-content').each( function(i, obj) {
       let content = obj.innerHTML;
       
       if (content.length > 25) {
           let prevContent = content;
           obj.innerHTML = "<span class='expandable'>" + content.substring(0, 25) + "...</span>";
           obj.onclick = function() {
               obj.innerHTML = prevContent;
           };
       }
   });
});
