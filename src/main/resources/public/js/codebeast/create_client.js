function postForm(){
    var formData = $("#create_client").serializeArray().reduce(function(a, x) { a[ x.name] = x.value; return a; }, {});
    $.ajax({
        type: "POST",
        url: "/clients/create",
        data: JSON.stringify(formData),
        contentType: "application/json",
        success: function(data){
            alert(data.name);
        },
    });
}

$("#create_client_button").click(postForm);