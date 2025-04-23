$(document).ready(function () {
   
    $('#tblDepartamento').DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "/api/departamento",
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
                "data": "nombre"
            },
            {
                "data": "descripcion"
            },
            {
                "data": "imagen_url"
            },
            {
                "data": "id",
                "render": function (data, _, _) {
                    return '<a class="btn btn-outline-success" href="/categoria/' + data + '">Ver Categorias</a>';
                }
            }
        ],
        "lengthMenu": [3, 6, 9, 12],
        "pageLength": 3
    });
});