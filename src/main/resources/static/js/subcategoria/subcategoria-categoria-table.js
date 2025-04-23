$(document).ready(function () {
    let categoriaId = window.location.pathname.split("/").pop();
    $('#tblSubCategoria').DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "/api/subcategoria/listar-categoria/" + categoriaId,
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
                    return '<a class="btn btn-outline-success" href="/producto/' + data + '">Ver Productos</a>';
                }
            }
        ],
        "lengthMenu": [3, 6, 9, 12],
        "pageLength": 3
    });
    
    $(".btn-return").click(function () {
        window.history.back();
    });
});