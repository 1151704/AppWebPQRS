$('input[type="file"]').on('change', function (e) {
    var fileName = e.target.files[0].name;
    $(".custom-file-label[for='" + this.id + "']").html(fileName);
});

function cargarImplements(url, data, container) {
    $.post(url, data).done(function (data) {
        $(container).html(data);
    });
}
