/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.mapper;

import com.example.GrupoD_InventarioSISE.dto.SubCategoriaDto;
import com.example.GrupoD_InventarioSISE.model.SubCategoria;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author RANDY
 */
public class SubCategoriaMapper {
    
    public static SubCategoriaDto toDto(SubCategoria subcategoria) {
        if (subcategoria == null) {
            return null;
        }
        return new SubCategoriaDto(
                subcategoria.getId(),
                subcategoria.getCategoria().getNombre(),
                subcategoria.getNombre(),
                subcategoria.getImagen_url()
        );
    }
    public static Page<SubCategoriaDto> toDtoList(Page<SubCategoria> subcategorias, Pageable pageable){
        List<SubCategoriaDto> subcategoriasDto = subcategorias.stream()
                .map(SubCategoriaMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(subcategoriasDto, pageable, subcategorias.getTotalElements());
    }
}
