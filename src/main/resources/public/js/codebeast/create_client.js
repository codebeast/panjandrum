function postForm(){
    showSave();

    var formData = $("#create_client").serializeArray().reduce(function(a, x) { a[ x.name] = x.value; return a; }, {});
    $.ajax({
        type: "POST",
        url: "/clients/create",
        data: JSON.stringify(formData),
        contentType: "application/json",
        success: function(data){
            Materialize.toast('Created client ' + data.name, 4000)
            hideSave();
            $('#modal1').modal('close');
        },
        error: function(data){
            Materialize.toast('Failed to create client', 4000)
            hideSave();
        }
    });
}

function showSave() {
    $("#save_spinner").addClass("active");
    $("#create_client_button").addClass('disabled');
}

function hideSave() {
    $("#save_spinner").removeClass("active");
    $("#create_client_button").removeClass('disabled');
}

$("#create_client_button").click(postForm);