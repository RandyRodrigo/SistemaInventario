$(document).ready(function () {
    const tablaProductos = $('#tblProducto').DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "/api/producto/listar",
            "type": "GET",
            "data": function (d) {
                d.page = d.start / d.length;
                d.size = d.length;
                d.search = d.search.value;
            },
            "dataSrc": function (json) {
                json.recordsTotal = json.totalElements;
                json.recordsFiltered = json.totalElements;
                verificarStockBajo(json.content); // Verificar stock bajo
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

    // Cola de notificaciones
    const notificaciones = [];

    // Función para verificar productos con stock bajo
    function
        verificarStockBajo(productos) {
        // Limpiar la cola de notificaciones
        notificaciones.length = 0;

        productos.forEach(producto => {
            // Verificar si el stock es menor o igual a 3
            if (producto.stock <= 3) {
                // Agregar mensaje a la cola de notificaciones
                notificaciones.push(`El producto "${producto.nombre}" tiene un stock bajo (${producto.stock}).`);
            }
        });

        // Mostrar las notificaciones si hay productos con stock bajo
        if (notificaciones.length > 0) {
            mostrarNotificaciones();
        }
    }

    // Función para mostrar notificaciones en el contenedor del dashboard
    function mostrarNotificaciones() {
        const contenedor = $('#notificaciones'); // Contenedor para notificaciones en el dashboard
        if (notificaciones.length > 0) {
            const mensaje = notificaciones.shift();
            contenedor.html(`
                    <div class="alert alert-warning alert-dismissible fade show" role="alert">
                        ${mensaje}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                `);

            // Mostrar la notificación con animación
            setTimeout(() => contenedor.find('.alert').addClass('show'), 100);

            // Ocultar la notificación después de 5 segundos
            setTimeout(() => {
                contenedor.find('.alert').removeClass('show').addClass('hide');
                setTimeout(() => {
                    contenedor.empty();
                    mostrarNotificaciones(); // Mostrar la siguiente notificación
                }, 500); // Esperar a que termine la animación de salida
            }, 5000);
        }
    }

    // Simular la carga de productos en el dashboard
    $.ajax({
        url: "/api/producto/listar",
        type: "GET",
        success: function (response) {
            verificarStockBajo(response.content); // Verificar stock bajo en el dashboard
        }
    });
});