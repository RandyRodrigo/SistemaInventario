$(document).ready(function() {
    const accion = $('#accion').val();
    const departamentoId = $('#departamentoId').val();
    
    // Si es edición, cargar datos del departamento
    if (accion === 'editar' && departamentoId) {
        cargarDatosDepartamento(departamentoId);
    }
    
    // Manejar envío del formulario
    $('#departamentoForm').on('submit', function(e) {
        e.preventDefault();
        
        // Validar formulario
        if (!this.checkValidity()) {
            e.stopPropagation();
            $(this).addClass('was-validated');
            return;
        }
        
        // Recopilar datos del formulario
        const departamento = {
            nombre: $('#nombre').val(),
            descripcion: $('#descripcion').val(),
            imagen_url: $('#imagen_url').val(),
        };
        
        // Guardar o actualizar dependiendo de la acción
        if (accion === 'nuevo') {
            guardarDepartamento(departamento);
        } else {
            actualizarDepartamento(departamentoId, departamento);
        }
    });
    
    // Botón cancelar
    $('#btnCancelar').on('click', function() {
        window.location.href = '/departamento';
    });
 
    // Función para cargar datos de un departamento existente
    function cargarDatosDepartamento(id) {
        $.ajax({
            url: '/api/departamento/' + id,
            type: 'GET',
            success: function(departamento) {
                // Llenar los demás campos
                $('#nombre').val(departamento.nombre);
                $('#descripcion').val(departamento.descripcion);
                $('#imagen_url').val(departamento.imagen_url);
            },
            error: function() {
                mostrarNotificacion('Error al cargar los datos del departamento', 'danger');
            }
        });
    }
    
    
    // Función para guardar un nuevo departamento
    function guardarDepartamento(departamento) {
        $.ajax({
            url: '/api/departamento',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(departamento),
            success: function() {
                mostrarNotificacion('Departamento guardada correctamente', 'success');
                setTimeout(function() {
                    window.location.href = '/departamento';
                }, 1500);
            },
            error: function() {
                mostrarNotificacion('Error al guardar el departamento', 'danger');
            }
        });
    }
    
    // Función para actualizar un departamento existente
    function actualizarDepartamento(id, departamento) {
        $.ajax({
            url: '/api/departamento/' + id,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(departamento),
            success: function() {
                mostrarNotificacion('Departamento actualizada correctamente', 'success');
                setTimeout(function() {
                    window.location.href = '/departamento';
                }, 1500);
            },
            error: function() {
                mostrarNotificacion('Error al actualizar el departamento', 'danger');
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