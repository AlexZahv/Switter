function add_new_swit(event) {

    var element = document.getElementById('new_swit');
    var lol=document.getElementById('new_swit_button').value;
    switch (lol) {
        case 'New swit':
        {
            element.style.display = "inline-block";
            element = document.getElementById('new_swit_button');
            element.value = "Hide"
        }
            ;
            break;
        case 'Hide':{
            element.style.display = "none";
            element = document.getElementById('new_swit_button');
            element.value = "New swit"
        }
    }
}

function check_new_swit(event){
    var element = document.getElementById('message_body');
    return (element.value!="")

}

function nextStep(elem) {
    if (elem == document.getElementById("next_step_button")) {
        var element = document.getElementById("second_step");
        element.style.display = 'block';
        element = document.getElementById("first_step");
        element.style.display = 'none';
    }
    if (elem == document.getElementById("previous_step_button")) {
        var element = document.getElementById("first_step");
        element.style.display = 'block';
        element = document.getElementById("second_step");
        element.style.display = 'none';
    }

}


