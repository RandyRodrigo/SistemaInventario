$(document).ready(function() {
    const accion = $('#accion').val();
    const subcategoriaId = $('#subcategoriaId').val();
    
    // Cargar datos para selects
    cargarCategorias();
    
    // Si es edición, cargar datos de la subcategoría
    if (accion === 'editar' && subcategoriaId) {
        cargarDatosSubcategoria(subcategoriaId);
    }
    
    // Manejar envío del formulario
    $('#subcategoriaForm').on('submit', function(e) {
        e.preventDefault();
        
        // Validar formulario
        if (!this.checkValidity()) {
            e.stopPropagation();
            $(this).addClass('was-validated');
            return;
        }
        
        // Recopilar datos del formulario
        const subcategoria = {
            idcategoria: $('#categoria').val(),
            nombre: $('#nombre').val(),
            imagen_url: $('#imagen_url').val(),
        };
        
        // Guardar o actualizar dependiendo de la acción
        if (accion === 'nuevo') {
            guardarSubcategoria(subcategoria);
        } else {
            actualizarSubcategoria(subcategoriaId, subcategoria);
        }
    });
    
    // Botón cancelar
    $('#btnCancelar').on('click', function() {
        window.location.href = '/subcategoria';
    });
    
    // Función para cargar categorías
    function cargarCategorias() {
        $.ajax({
            url: '/api/categoria/listartodo',
            type: 'GET',
            success: function(data) {
                $('#categoria').empty().append('<option value="">Seleccione una categoría</option>');
                
                data.content.forEach(function(categoria) {
                    $('#categoria').append(`<option value="${categoria.id}">${categoria.nombre}</option>`);
                });
            },
            error: function() {
                mostrarNotificacion('Error al cargar las categorías', 'danger');
            }
        });
    }
    
    // Función para cargar datos de una subcategoria existente
    function cargarDatosSubcategoria(id) {
        $.ajax({
            url: '/api/subcategoria/' + id,
            type: 'GET',
            success: function(subcategoria) {
                // Buscar los IDs correspondientes a los nombres
                buscarIdCategoria(subcategoria.nombre_categoria).then(function(categoriaId) {
                    $('#categoria').val(categoriaId);
                });
                // Llenar los demás campos
                $('#nombre').val(subcategoria.nombre);
                $('#imagen_url').val(subcategoria.imagen_url);
            },
            error: function() {
                mostrarNotificacion('Error al cargar los datos de la subcategoria', 'danger');
            }
        });
    }
    
    // Funciones para buscar IDs por nombre
    function buscarIdCategoria(nombre) {
        return new Promise(function(resolve, reject) {
            $.ajax({
                url: '/api/categoria/buscar-por-nombre?nombre=' + encodeURIComponent(nombre),
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
    
    // Función para guardar una nueva subcategoria
    function guardarSubcategoria(subcategoria) {
        $.ajax({
            url: '/api/subcategoria',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(subcategoria),
            success: function() {
                mostrarNotificacion('Subcategoria guardada correctamente', 'success');
                setTimeout(function() {
                    window.location.href = '/subcategoria';
                }, 1500);
            },
            error: function() {
                mostrarNotificacion('Error al guardar la subcategoria', 'danger');
            }
        });
    }
    
    // Función para actualizar una subcategoria existente
    function actualizarSubcategoria(id, subcategoria) {
        $.ajax({
            url: '/api/subcategoria/' + id,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(subcategoria),
            success: function() {
                mostrarNotificacion('SubCategoria actualizada correctamente', 'success');
                setTimeout(function() {
                    window.location.href = '/subcategoria';
                }, 1500);
            },
            error: function() {
                mostrarNotificacion('Error al actualizar la subcategoria', 'danger');
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