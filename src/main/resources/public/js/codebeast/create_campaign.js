function postForm(){
    showSave();

    var formData = $("#create_campaign").serializeArray().reduce(function(a, x) { a[ x.name] = x.value; return a; }, {});
    $.ajax({
        type: "POST",
        url: "/campaigns/create",
        data: JSON.stringify(formData),
        contentType: "application/json",
        success: function(data){
            Materialize.toast('Created campaign ' + data.name, 4000)
            hideSave();
            $('#modal1').modal('close');
        },
        error: function(){
            Materialize.toast('Failed to create campaign', 4000)
            hideSave();
        }
    });
}

function showSave() {
    $("#save_spinner").addClass("active");
    $("#submit_button").addClass('disabled');
}

function hideSave() {
    $("#save_spinner").removeClass("active");
    $("#submit_button").removeClass('disabled');
}

$("#submit_button").click(postForm);

$(document).keypress(function(e) {
  if(e.which == 13) {
    e.preventDefault();
    postForm();
  }
});