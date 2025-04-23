$(document).ready(function () {

    $('#tblSubCategoria').DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "/api/subcategoria/listar",
            "type": "GET",
            "data": function (d) {
                d.page = d.start / d.length;
                d.size = d.length;
                d.search = d.search.value;
            },
            "dataSrc": function (json) {
                json.recordsTotal = json.totalElements;
                json.recordsFiltered = json.totalElements;
                return json.content;
            }
        },
        "columns": [
            {
                "data": "id"
            },
            {
                "data": "nombre_categoria"
            },
            {
                "data": "nombre"
            },
            {
                "data": "imagen_url"
            },
            {
                "data": "id",
                "render": function (data, _, _) {
                    return '';
                }
            }
        ],
        "lengthMenu": [10, 20, 50, 100],
        "pageLength": 10
    });
    
    $(".btn-return").click(function () {
        window.history.back();
    });
});