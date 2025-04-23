$(document).ready(function() {
    // Variable para almacenar los datos del producto seleccionado
    let productoSeleccionado = null;
    
    // Modificar la columna de acciones en la tabla
    $('#tblProducto').on('draw.dt', function() {
        $('#tblProducto tbody tr').each(function() {
            const row = $(this);
            const id = row.find('td:first').text();
            const actionCell = row.find('td:last');
            
            // Agregar botones de acción
            if (actionCell.find('.btn-ver-detalle').length === 0) {
                actionCell.html(`
                    <div class="btn-group" role="group">
                        <button class="btn btn-info btn-sm btn-ver-detalle" data-id="${id}" title="Ver detalles">
                            <i class="bi bi-eye"></i>
                        </button>
                    </div>
                `);
            }
        });
    });
    
    // Manejador para botón de ver detalles
    $('#tblProducto').on('click', '.btn-ver-detalle', function() {
        const id = $(this).data('id');
        
        // Obtener los datos completos del producto
        $.ajax({
            url: '/api/producto/' + id,
            type: 'GET',
            success: function(producto) {
                productoSeleccionado = producto;
                cargarDatosEnModal(producto);
                $('#detalleProductoModal').modal('show');
            },
            error: function() {
                mostrarNotificacion('Error al cargar los detalles del producto', 'danger');
            }
        });
    });
    
    // Función para cargar los datos en el modal
    function cargarDatosEnModal(producto) {
        $('#detalle-id').text(producto.id);
        $('#detalle-codigo').text(producto.codigo);
        $('#detalle-nombre').text(producto.nombre);
        $('#detalle-precio').text('S/ ' + parseFloat(producto.precio).toFixed(2));
        $('#detalle-stock').text(producto.stock);
        $('#detalle-categoria').text(producto.nombre_categoria);
        $('#detalle-marca').text(producto.nombre_marca);
        $('#detalle-proveedor').text(producto.nombre_proveedor);
        $('#detalle-descripcion').text(producto.descripcion || 'No disponible');
        $('#detalle-especificaciones').text(producto.especificaciones || 'No disponible');
        
        // Manejo de la imagen del producto
        if (producto.imagen_url) {
            $('#detalle-imagen').attr('src', producto.imagen_url);
            $('#detalle-imagen-url').text(producto.imagen_url);
        } else {
            $('#detalle-imagen').attr('src', '/img/no-image.png');
            $('#detalle-imagen-url').text('No disponible');
        }
        
        $('#detalle-fabricante').text(producto.informacion_fabricante_url || 'No disponible');
    }
    
    // Función para mostrar notificaciones elegantes
    function mostrarNotificacion(mensaje, tipo) {
        // Eliminar notificaciones anteriores
        $('.notification-toast').remove();
        
        // Crear elemento de notificación
        const notificacion = $(`
            <div class="alert alert-${tipo} alert-dismissible fade show notification-toast" role="alert">
                ${mensaje}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        `);
        
        // Añadir al cuerpo del documento
        $('body').append(notificacion);
        
        // Posicionar la notificación
        notificacion.css({
            'position': 'fixed',
            'top': '20px',
            'right': '20px',
            'z-index': '9999',
            'min-width': '300px',
            'box-shadow': '0 4px 8px rgba(0, 0, 0, 0.1)',
            'border-left': tipo === 'success' ? '4px solid #28a745' : '4px solid #dc3545',
            'animation': 'slideIn 0.3s ease-out'
        });
        
        // Definir la animación si no existe en CSS
        if (!document.getElementById('notification-animation')) {
            const style = document.createElement('style');
            style.id = 'notification-animation';
            style.innerHTML = `
                @keyframes slideIn {
                    from {
                        transform: translateX(100%);
                        opacity: 0;
                    }
                    to {
                        transform: translateX(0);
                        opacity: 1;
                    }
                }
            `;
            document.head.appendChild(style);
        }
        
        // Eliminar después de 5 segundos
        setTimeout(function() {
            notificacion.alert('close');
        }, 5000);
    }
});