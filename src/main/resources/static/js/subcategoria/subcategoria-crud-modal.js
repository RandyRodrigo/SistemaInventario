$(document).ready(function() {
    // Variable para almacenar los datos de la subcategoria seleccionada
    let subcategoriaSeleccionada = null;
    
    // Modificar la columna de acciones en la tabla
    $('#tblSubCategoria').on('draw.dt', function() {
        $('#tblSubCategoria tbody tr').each(function() {
            const row = $(this);
            const id = row.find('td:first').text();
            const actionCell = row.find('td:last');
            
            // Agregar botones de acción
            if (actionCell.find('.btn-editar').length === 0) {
                actionCell.html(`
                    <div class="btn-group" role="group">
                        <button class="btn btn-primary btn-sm btn-editar" data-id="${id}" title="Editar">
                            <i class="bi bi-pencil"></i>
                        </button>
                        <button class="btn btn-danger btn-sm btn-eliminar" data-id="${id}" title="Eliminar">
                            <i class="bi bi-trash"></i>
                        </button>
                    </div>
                `);
            }
        });
    });
    
    // Manejador para botón de editar en la tabla (desvinculamos primero)
    $('#tblSubCategoria').off('click', '.btn-editar').on('click', '.btn-editar', function() {
        const id = $(this).data('id');
        window.location.href = '/subcategoria/editar/' + id;
    });
    
    // Manejador para botón de eliminar en la tabla (desvinculamos primero)
    $('#tblSubCategoria').off('click', '.btn-eliminar').on('click', '.btn-eliminar', function() {
        const id = $(this).data('id');
        
        if (confirm('¿Está seguro de eliminar esta subcategoría? Esta acción desactivará la subcategoría en el sistema.')) {
            $.ajax({
                url: '/api/subcategoria/' + id,
                type: 'DELETE',
                success: function(response) {
                    // Actualizar la tabla sin recargar la página
                    $('#tblSubCategoria').DataTable().ajax.reload();
                    mostrarNotificacion('Subcategoría eliminada correctamente', 'success');
                },
                error: function(xhr) {
                    if (xhr.status === 409) {
                        // Error específico: productos asociados
                        mostrarNotificacion(xhr.responseText, 'warning');
                    } else {
                        // Otros errores
                        mostrarNotificacion('Error al eliminar la subcategoría: ' + (xhr.responseText || 'Error desconocido'), 'danger');
                    }
                }
            });
        }
    });
    
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
            'border-left': tipo === 'success' ? '4px solid #28a745' : 
                          (tipo === 'warning' ? '4px solid #ffc107' : '4px solid #dc3545'),
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
    
    // Manejador para botón de nueva subcategoria
    $('#btnNuevaSubCategoria').on('click', function() {
        window.location.href = '/subcategoria/nuevo';
    });
});