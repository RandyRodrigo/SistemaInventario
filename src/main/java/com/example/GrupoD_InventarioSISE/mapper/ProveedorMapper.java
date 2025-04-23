/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.mapper;

import com.example.GrupoD_InventarioSISE.dto.ProveedorDto;
import com.example.GrupoD_InventarioSISE.model.Proveedor;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author RANDY
 */
public class ProveedorMapper {
    
    public static ProveedorDto toDto(Proveedor proveedor){
        if (proveedor == null) {
            return null;
        }
        return new ProveedorDto(
                proveedor.getId(),
                proveedor.getNombre_comercial(),
                proveedor.getTipodocumento().getNombre(),
                proveedor.getNumero_documento(),
                proveedor.getTelefono(),
                proveedor.getCorreo()
        );
    }
    public static Page<ProveedorDto> toDtoList(Page<Proveedor> proveedores, Pageable pageable){
        List<ProveedorDto> proveedoresDto = proveedores.stream()
                .map(ProveedorMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(proveedoresDto, pageable, proveedores.getTotalElements());
    }
}
