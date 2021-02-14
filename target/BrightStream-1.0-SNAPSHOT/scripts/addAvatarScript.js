
function hover(element, path) {
    
    initialOpacity = 1;
    percent = 100;
    
    while (percent > 0) {
        element.setAttribute('opacity', percent / 100 + '');
        percent = percent - 1;
    }
    
   
    
}

function unhover(element, path) {
    element.setAttribute('src', path);
}

