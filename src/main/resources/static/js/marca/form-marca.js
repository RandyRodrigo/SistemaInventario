$(document).ready(function() {
    const accion = $('#accion').val();
    const marcaId = $('#marcaId').val();
    
    // Si es edición, cargar datos de la marca
    if (accion === 'editar' && marcaId) {
        cargarDatosMarca(marcaId);
    }
    
    // Manejar envío del formulario
    $('#marcaForm').on('submit', function(e) {
        e.preventDefault();
        
        // Validar formulario
        if (!this.checkValidity()) {
            e.stopPropagation();
            $(this).addClass('was-validated');
            return;
        }
        
        // Recopilar datos del formulario
        const marca = {
            nombre: $('#nombre').val(),
            logo_url: $('#logo_url').val(),
        };
        
        // Guardar o actualizar dependiendo de la acción
        if (accion === 'nuevo') {
            guardarMarca(marca);
        } else {
            actualizarMarca(marcaId, marca);
        }
    });
    
    // Botón cancelar
    $('#btnCancelar').on('click', function() {
        window.location.href = '/marca';
    });
 
    // Función para cargar datos de una marca existente
    function cargarDatosMarca(id) {
        $.ajax({
            url: '/api/marca/' + id,
            type: 'GET',
            success: function(marca) {
                // Llenar los demás campos
                $('#nombre').val(marca.nombre);
                $('#logo_url').val(marca.logo_url);
            },
            error: function() {
                mostrarNotificacion('Error al cargar los datos de la marca', 'danger');
            }
        });
    }
    
    
    // Función para guardar una nueva marca
    function guardarMarca(marca) {
        $.ajax({
            url: '/api/marca',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(marca),
            success: function() {
                mostrarNotificacion('Marca guardada correctamente', 'success');
                setTimeout(function() {
                    window.location.href = '/marca';
                }, 1500);
            },
            error: function() {
                mostrarNotificacion('Error al guardar la marca', 'danger');
            }
        });
    }
    
    // Función para actualizar una marca existente
    function actualizarMarca(id, marca) {
        $.ajax({
            url: '/api/marca/' + id,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(marca),
            success: function() {
                mostrarNotificacion('Marca actualizada correctamente', 'success');
                setTimeout(function() {
                    window.location.href = '/marca';
                }, 1500);
            },
            error: function() {
                mostrarNotificacion('Error al actualizar la marca', 'danger');
            }
        });
    }
    
    // Función para mostrar notificaciones
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