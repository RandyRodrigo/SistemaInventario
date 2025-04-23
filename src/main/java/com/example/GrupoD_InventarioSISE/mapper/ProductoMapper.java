/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.mapper;

import com.example.GrupoD_InventarioSISE.dto.ProductoDto;
import com.example.GrupoD_InventarioSISE.model.Producto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author RANDY
 */
public class ProductoMapper {
    
    public static ProductoDto toDto(Producto producto) {
        if (producto == null) {
            return null;
        }
        return new ProductoDto(
                producto.getId(),
                producto.getSubcategoria().getNombre(),
                producto.getMarca().getNombre(),
                producto.getProveedor().getNombre_comercial(),
                producto.getCodigo(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getEspecificaciones(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getImagen_url(),
                producto.getInformacion_fabricante_url()
        );
    }
    
    public static Page<ProductoDto> toDtoList(Page<Producto> productos, Pageable pageable){
        List<ProductoDto> productosDto = productos.stream()
                .map(ProductoMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(productosDto, pageable, productos.getTotalElements());
    }
}
