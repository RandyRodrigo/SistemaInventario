$(document).ready(function() {
    const accion = $('#accion').val();
    const categoriaId = $('#categoriaId').val();
    
    // Cargar datos para selects
    cargarDepartamentos();
    
    // Si es edición, cargar datos de la categoría
    if (accion === 'editar' && categoriaId) {
        cargarDatosCategoria(categoriaId);
    }
    
    // Manejar envío del formulario
    $('#categoriaForm').on('submit', function(e) {
        e.preventDefault();
        
        // Validar formulario
        if (!this.checkValidity()) {
            e.stopPropagation();
            $(this).addClass('was-validated');
            return;
        }
        
        // Recopilar datos del formulario
        const categoria = {
            iddepartamento: $('#departamento').val(),
            nombre: $('#nombre').val(),
            imagen_url: $('#imagen_url').val(),
        };
        
        // Guardar o actualizar dependiendo de la acción
        if (accion === 'nuevo') {
            guardarCategoria(categoria);
        } else {
            actualizarCategoria(categoriaId, categoria);
        }
    });
    
    // Botón cancelar
    $('#btnCancelar').on('click', function() {
        window.location.href = '/categoria';
    });
    
    // Función para cargar departamentos
    function cargarDepartamentos() {
        $.ajax({
            url: '/api/departamento/listartodo',
            type: 'GET',
            success: function(data) {
                $('#departamento').empty().append('<option value="">Seleccione un departamento</option>');
                
                data.content.forEach(function(departamento) {
                    $('#departamento').append(`<option value="${departamento.id}">${departamento.nombre}</option>`);
                });
            },
            error: function() {
                mostrarNotificacion('Error al cargar los Departamentos', 'danger');
            }
        });
    }
    
    // Función para cargar datos de una categoria existente
    function cargarDatosCategoria(id) {
        $.ajax({
            url: '/api/categoria/' + id,
            type: 'GET',
            success: function(categoria) {
                // Buscar los IDs correspondientes a los nombres
                buscarIdDepartamento(categoria.nombre_departamento).then(function(departamentoId) {
                    $('#departamento').val(departamentoId);
                });
                // Llenar los demás campos
                $('#nombre').val(categoria.nombre);
                $('#imagen_url').val(categoria.imagen_url);
            },
            error: function() {
                mostrarNotificacion('Error al cargar los datos de la categoria', 'danger');
            }
        });
    }
    
    // Funciones para buscar IDs por nombre
    function buscarIdDepartamento(nombre) {
        return new Promise(function(resolve, reject) {
            $.ajax({
                url: '/api/departamento/buscar-por-nombre?nombre=' + encodeURIComponent(nombre),
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
    
    // Función para guardar una nueva categoria
    function guardarCategoria(categoria) {
        $.ajax({
            url: '/api/categoria',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(categoria),
            success: function() {
                mostrarNotificacion('Categoría guardada correctamente', 'success');
                setTimeout(function() {
                    window.location.href = '/categoria';
                }, 1500);
            },
            error: function() {
                mostrarNotificacion('Error al guardar la categoria', 'danger');
            }
        });
    }
    
    // Función para actualizar una categoria existente
    function actualizarCategoria(id, categoria) {
        $.ajax({
            url: '/api/categoria/' + id,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(categoria),
            success: function() {
                mostrarNotificacion('Categoria actualizada correctamente', 'success');
                setTimeout(function() {
                    window.location.href = '/categoria';
                }, 1500);
            },
            error: function() {
                mostrarNotificacion('Error al actualizar la categoria', 'danger');
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