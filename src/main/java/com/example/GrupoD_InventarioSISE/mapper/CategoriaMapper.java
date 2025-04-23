/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.mapper;

import com.example.GrupoD_InventarioSISE.dto.CategoriaDto;
import com.example.GrupoD_InventarioSISE.model.Categoria;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author RANDY
 */
public class CategoriaMapper {
    
    public static CategoriaDto toDto(Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        return new CategoriaDto(
                categoria.getId(),
                categoria.getDepartamento().getNombre(),
                categoria.getNombre(),
                categoria.getImagen_url()
        );
    }
    public static Page<CategoriaDto> toDtoList(Page<Categoria> categorias, Pageable pageable){
        List<CategoriaDto> categoriasDto = categorias.stream()
                .map(CategoriaMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(categoriasDto, pageable, categorias.getTotalElements());
    }
}
