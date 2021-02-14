$.fn.extend({
   qcss: function(css) {
      return $(this).queue(function(next) {
         $(this).css(css);
         next();
      });
   }
});

$(document).ready( function() {
   $("#rememberUser-link").click( function() {
        $.ajax({
            url: 'rememberUser',
            success: function () {
                $("#rememberUser-link").qcss( {backgroundColor: '#d6f0c3', borderRadius: '1em 1em 0 0'} )
                                       .delay(1000)
                                       .qcss( {background: 'none'} );
            }
        });
   });      
});


