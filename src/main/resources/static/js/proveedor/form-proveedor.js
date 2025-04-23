$(document).ready(function() {
    const accion = $('#accion').val();
    const proveedorId = $('#proveedorId').val();
    
    // Cargar datos para selects
    cargarTipoDocumentos();

    // Si es edición, cargar datos del proveedor
    if (accion === 'editar' && proveedorId) {
        cargarDatosProveedor(proveedorId);
    }
    
    // Manejar envío del formulario
    $('#proveedorForm').on('submit', function(e) {
        e.preventDefault();
        
        // Validar formulario
        if (!this.checkValidity()) {
            e.stopPropagation();
            $(this).addClass('was-validated');
            return;
        }
        
        // Recopilar datos del formulario
        const proveedor = {
            nombre_comercial: $('#nombre_comercial').val(),
            idtipodocumento: $('#nombre_documento').val(),
            numero_documento: $('#numero_documento').val(),
            telefono: $('#telefono').val(),
            correo: $('#correo').val()
        };
        
        // Guardar o actualizar dependiendo de la acción
        if (accion === 'nuevo') {
            guardarProveedor(proveedor);
        } else {
            actualizarProveedor(proveedorId, proveedor);
        }
    });
    
    // Botón cancelar
    $('#btnCancelar').on('click', function() {
        window.location.href = '/proveedor';
    });
    
    // Función para cargar tipo documento
    function cargarTipoDocumentos() {
        $.ajax({
            url: '/api/tipodocumento/listartodo',
            type: 'GET',
            success: function(data) {
                $('#nombre_documento').empty().append('<option value="">Seleccione un Tipo de Documento</option>');
                
                data.content.forEach(function(tipodocumento) {
                    $('#nombre_documento').append(`<option value="${tipodocumento.id}">${tipodocumento.nombre}</option>`);
                });
            },
            error: function() {
                mostrarNotificacion('Error al cargar los tipos de documentos', 'danger');
            }
        });
    }
    
    // Función para cargar datos de un proveedor existente
    function cargarDatosProveedor(id) {
        $.ajax({
            url: '/api/proveedor/' + id,
            type: 'GET',
            success: function(proveedor) {
                // Buscar los IDs correspondientes a los nombres
                buscarIdTipoDocumento(proveedor.nombre_documento).then(function(tipodocumentoId) {
                    $('#nombre_documento').val(tipodocumentoId);
                });
                
                // Llenar los demás campos
                $('#nombre_comercial').val(proveedor.nombre_comercial);
                $('#numero_documento').val(proveedor.numero_documento);
                $('#telefono').val(proveedor.telefono);
                $('#correo').val(proveedor.correo);
            },
            error: function() {
                mostrarNotificacion('Error al cargar los datos del proveedor', 'danger');
            }
        });
    }
    
    // Funciones para buscar IDs por nombre
    function buscarIdTipoDocumento(nombre) {
        return new Promise(function(resolve, reject) {
            $.ajax({
                url: '/api/tipodocumento/buscar-por-nombre?nombre=' + encodeURIComponent(nombre),
                type: 'GET',
                success: function(data) {
                    resolve(data.id);
                },
                error: function() {
                    reject();
                }
            });
        });
    }
    
    
    // Función para guardar un nuevo producto
    function guardarProveedor(proveedor) {
        $.ajax({
            url: '/api/proveedor',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(proveedor),
            success: function() {
                mostrarNotificacion('Proveedor guardado correctamente', 'success');
                setTimeout(function() {
                    window.location.href = '/proveedor';
                }, 1500);
            },
            error: function() {
                mostrarNotificacion('Error al guardar el proveedor', 'danger');
            }
        });
    }
    
    // Función para actualizar un proveedor existente
    function actualizarProveedor(id, proveedor) {
        $.ajax({
            url: '/api/proveedor/' + id,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(proveedor),
            success: function() {
                mostrarNotificacion('Proveedor actualizado correctamente', 'success');
                setTimeout(function() {
                    window.location.href = '/proveedor';
                }, 1500);
            },
            error: function() {
                mostrarNotificacion('Error al actualizar el proveedor', 'danger');
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