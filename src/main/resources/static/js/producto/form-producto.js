$(document).ready(function() {
    const accion = $('#accion').val();
    const productoId = $('#productoId').val();
    
    // Cargar datos para selects
    cargarSubcategorias();
    cargarMarcas();
    cargarProveedores();
    
    // Si es edición, cargar datos del producto
    if (accion === 'editar' && productoId) {
        cargarDatosProducto(productoId);
    }
    
    // Manejar envío del formulario
    $('#productoForm').on('submit', function(e) {
        e.preventDefault();
        
        // Validar formulario
        if (!this.checkValidity()) {
            e.stopPropagation();
            $(this).addClass('was-validated');
            return;
        }
        
        // Recopilar datos del formulario
        const producto = {
            idsubcategoria: $('#subcategoria').val(),
            idmarca: $('#marca').val(),
            idproveedor: $('#proveedor').val(),
            codigo: $('#codigo').val(),
            nombre: $('#nombre').val(),
            descripcion: $('#descripcion').val(),
            especificaciones: $('#especificaciones').val(),
            precio: parseFloat($('#precio').val()),
            stock: parseInt($('#stock').val()),
            imagen_url: $('#imagen_url').val(),
            informacion_fabricante_url: $('#informacion_fabricante_url').val()
        };
        
        // Guardar o actualizar dependiendo de la acción
        if (accion === 'nuevo') {
            guardarProducto(producto);
        } else {
            actualizarProducto(productoId, producto);
        }
    });
    
    // Botón cancelar
    $('#btnCancelar').on('click', function() {
        window.location.href = '/producto';
    });
    
    // Función para cargar subcategorías
    function cargarSubcategorias() {
        $.ajax({
            url: '/api/subcategoria/listartodo',
            type: 'GET',
            success: function(data) {
                $('#subcategoria').empty().append('<option value="">Seleccione una subcategoría</option>');
                
                data.content.forEach(function(subcategoria) {
                    $('#subcategoria').append(`<option value="${subcategoria.id}">${subcategoria.nombre}</option>`);
                });
            },
            error: function() {
                mostrarNotificacion('Error al cargar las categorías', 'danger');
            }
        });
    }
    
    // Función para cargar marcas
    function cargarMarcas() {
        $.ajax({
            url: '/api/marca/listartodo',
            type: 'GET',
            success: function(data) {
                $('#marca').empty().append('<option value="">Seleccione una marca</option>');
                
                data.content.forEach(function(marca) {
                    $('#marca').append(`<option value="${marca.id}">${marca.nombre}</option>`);
                });
            },
            error: function() {
                mostrarNotificacion('Error al cargar las marcas', 'danger');
            }
        });
    }
    
    // Función para cargar proveedores
    function cargarProveedores() {
        $.ajax({
            url: '/api/proveedor/listartodo',
            type: 'GET',
            success: function(data) {
                $('#proveedor').empty().append('<option value="">Seleccione un proveedor</option>');
                
                data.content.forEach(function(proveedor) {
                    $('#proveedor').append(`<option value="${proveedor.id}">${proveedor.nombre_comercial}</option>`);
                });
            },
            error: function() {
                mostrarNotificacion('Error al cargar los proveedores', 'danger');
            }
        });
    }
    
    // Función para cargar datos de un producto existente
    function cargarDatosProducto(id) {
        $.ajax({
            url: '/api/producto/' + id,
            type: 'GET',
            success: function(producto) {
                // Buscar los IDs correspondientes a los nombres
                buscarIdSubcategoria(producto.nombre_categoria).then(function(subcategoriaId) {
                    $('#subcategoria').val(subcategoriaId);
                });
                
                buscarIdMarca(producto.nombre_marca).then(function(marcaId) {
                    $('#marca').val(marcaId);
                });
                
                buscarIdProveedor(producto.nombre_proveedor).then(function(proveedorId) {
                    $('#proveedor').val(proveedorId);
                });
                
                // Llenar los demás campos
                $('#codigo').val(producto.codigo);
                $('#nombre').val(producto.nombre);
                $('#descripcion').val(producto.descripcion);
                $('#especificaciones').val(producto.especificaciones);
                $('#precio').val(producto.precio);
                $('#stock').val(producto.stock);
                $('#imagen_url').val(producto.imagen_url);
                $('#informacion_fabricante_url').val(producto.informacion_fabricante_url);
            },
            error: function() {
                mostrarNotificacion('Error al cargar los datos del producto', 'danger');
            }
        });
    }
    
    // Funciones para buscar IDs por nombre
    function buscarIdSubcategoria(nombre) {
        return new Promise(function(resolve, reject) {
            $.ajax({
                url: '/api/subcategoria/buscar-por-nombre?nombre=' + encodeURIComponent(nombre),
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
    
    function buscarIdMarca(nombre) {
        return new Promise(function(resolve, reject) {
            $.ajax({
                url: '/api/marca/buscar-por-nombre?nombre=' + encodeURIComponent(nombre),
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
    
    function buscarIdProveedor(nombre) {
        return new Promise(function(resolve, reject) {
            $.ajax({
                url: '/api/proveedor/buscar-por-nombre?nombre=' + encodeURIComponent(nombre),
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
    function guardarProducto(producto) {
        $.ajax({
            url: '/api/producto',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(producto),
            success: function() {
                mostrarNotificacion('Producto guardado correctamente', 'success');
                setTimeout(function() {
                    window.location.href = '/producto';
                }, 1500);
            },
            error: function() {
                mostrarNotificacion('Error al guardar el producto', 'danger');
            }
        });
    }
    
    // Función para actualizar un producto existente
    function actualizarProducto(id, producto) {
        $.ajax({
            url: '/api/producto/' + id,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(producto),
            success: function() {
                mostrarNotificacion('Producto actualizado correctamente', 'success');
                setTimeout(function() {
                    window.location.href = '/producto';
                }, 1500);
            },
            error: function() {
                mostrarNotificacion('Error al actualizar el producto', 'danger');
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