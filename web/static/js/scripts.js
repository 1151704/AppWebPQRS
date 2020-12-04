function cargarImplements(url, data, container) {
    $.post(url, data).done(function (data) {
        $(container).html(data);
    });
}

function multipleFiles(container, max) {
    $(container).on("change", function (e) {
        if (this.files.length > max) {
            alert(`Superó el máximo de archivos. Solo se permite cargar ${max} archivo(s).`);
            $(".custom-file-label[for='" + this.id + "']").html("Cargar");
            this.value = null;
        } else {
            var fileName = e.target.files[0].name;
            $(".custom-file-label[for='" + this.id + "']").html(fileName);
        }
    });
}