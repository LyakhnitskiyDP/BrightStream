

function submitAvatarForm() {
    const form = document.getElementById("form-new-avatar");
    form.submit();
}

function initTimeField() {
    var timeField = document.getElementById("timeOfTheDay");
    var hours = (new Date()).getHours();
    var timeString;

    switch (true) {
        case (hours < 6):
            timeString = "night";
            break;
        case (hours < 12):
            timeString = "morning";
            break;
        case (hours < 18):
            timeString = "day";
            break;
        case (hours < 24):
            timeString = "evening";
            break;
    }

    timeField.innerText = timeString;
}






