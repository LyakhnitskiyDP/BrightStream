jQuery.fn.rotate = function(degrees) {
    $(this).css({'transform' : 'rotate('+ degrees +'deg)'});
    return $(this);
};

$(document).ready( function() {
    

var table = $('table');

$('.sortable')
    .each(function(){

        var th = $(this),
            triangle = th.find('.img-triangle'),
            thIndex = th.index(),
            inverse = false;

        th.click(function(){
            
            if (inverse == 1) {
            triangle.css({
                "-webkit-transform": "rotate(180deg)",
                "-moz-transform": "rotate(180deg)",
                "transform": "rotate(0deg)"
            });
            } else {
                triangle.css({
                "-webkit-transform": "rotate(180deg)",
                "-moz-transform": "rotate(180deg)",
                "transform": "rotate(180deg)"
            });
            }
            
            table.find('td').filter(function(){

                return $(this).index() === thIndex;
            }).sortElements(function(a, b){

                if( $.text([a]) === $.text([b]) )
                    return 0;

                return $.text([a]) > $.text([b]) ?
                    inverse ? -1 : 1
                    : inverse ? 1 : -1;

            }, function(){

                // parentNode is the element we want to move
                return this.parentNode; 

            });

            inverse = !inverse;

        });

    });
    }
);







