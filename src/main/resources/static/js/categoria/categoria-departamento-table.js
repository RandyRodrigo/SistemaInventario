$(document).ready(function () {
    let departamentoId = window.location.pathname.split("/").pop();
    $('#tblCategoria').DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "/api/categoria/listar-departamento/" + departamentoId,
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
                "data": "nombre_departamento"
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
                    return '<a class="btn btn-outline-success" href="/subcategoria/' + data + '">Ver SubCategorias</a>';
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