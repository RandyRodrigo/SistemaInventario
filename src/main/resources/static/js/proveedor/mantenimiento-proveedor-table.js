$(document).ready(function () {
   
    $('#tblProveedor').DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "/api/proveedor/listar",
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
                "data": "nombre_comercial"
            },
            {
                "data": "nombre_documento"
            },
            {
                "data": "numero_documento"
            },
            {
                "data": "telefono"
            },
            {
                "data": "correo"
            },
            {
                "data": "id",
                "render": function (data, _, _) {
                    return '';
                }
            }
        ],
        "lengthMenu": [3, 6, 9, 12],
        "pageLength": 3
    });
});