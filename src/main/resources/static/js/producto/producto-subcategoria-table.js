$(document).ready(function () {
    let subcategoriaId = window.location.pathname.split("/").pop();
    const tablaProductos = $('#tblProducto').DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "/api/producto/listar-subcategoria/" + subcategoriaId,
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
            { "data": "id" },
            { "data": "nombre_categoria" },
            { "data": "nombre_marca" },
            { "data": "codigo" },
            {
                "data": "nombre",
                "render": function (data) {
                    // Limitar a 30 caracteres y añadir puntos suspensivos si es más largo
                    return data.length > 30 ? data.substr(0, 30) + '...' : data;
                }
            },
            {
                "data": "precio",
                "render": function (data) {
                    return 'S/ ' + parseFloat(data).toFixed(2);
                }
            },
            { "data": "stock" },
            {
                "data": "id",
                "render": function (data) {
                    return '';
                }
            }
        ],
        "lengthMenu": [10, 20, 50, 100],
        "pageLength": 10,
    });
    
    $(".btn-return").click(function () {
        window.history.back();
    });
});