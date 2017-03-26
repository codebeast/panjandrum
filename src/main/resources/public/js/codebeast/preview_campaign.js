
function updatePreview() {
    var formData = $("#campaignForm").serializeArray().reduce(function(a, x) { a[ x.name] = x.value; return a; }, {});
    console.log("should really update the view");
    console.log(formData);

    var color = formData.backgroundColor;

    $("#previewCard").removeClass();
    $('#previewCard').addClass(color);
    $('#previewCard').addClass('card');
    $('#previewCard').addClass('center');

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
