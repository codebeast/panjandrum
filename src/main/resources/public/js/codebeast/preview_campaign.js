
function updatePreview() {
    var formData = $("#campaignForm").serializeArray().reduce(function(a, x) { a[ x.name] = x.value; return a; }, {});


    var color = formData.backgroundColor;

    $("#previewCard").removeClass();
    $('#previewCard').addClass(color);
    $('#previewCard').addClass('card');
    $('#previewCard').addClass('center');
    $('#previewCard').addClass('z-depth-3');

    var textColor = formData.textColor;
    var primaryTextCss = textColor + '-text';

    $('#headingPreview').text(formData.headline);
    $("#headingPreview").removeClass();
    $('#headingPreview').addClass(primaryTextCss);
    $('#headingPreview').addClass('flow-text');

    $('#bodyPreview').text(formData.body);
    $("#bodyPreview").removeClass();
    $('#bodyPreview').addClass(primaryTextCss);


    $("#primaryImagePreview").attr('src', formData.primaryImage);
    $("#secondaryImagePreview").attr('src', formData.secondaryImage);
}

$("#updatePreview").click(updatePreview);


function postForm(){
    showSave();

    var formData = $("#campaignForm").serializeArray().reduce(function(a, x) { a[ x.name] = x.value; return a; }, {});
    $.ajax({
        type: "POST",
        url: window.location.href + "/savecampaign",
        data: JSON.stringify(formData),
        contentType: "application/json",
        success: function(data){
            Materialize.toast('Saved campaign ' + data.name, 4000)
            hideSave();
        },
        error: function(){
            Materialize.toast('Failed to save campaign', 4000)
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

$(document).ready(function() {
    updatePreview();
});