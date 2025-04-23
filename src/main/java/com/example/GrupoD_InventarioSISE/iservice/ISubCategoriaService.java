/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.iservice;

import com.example.GrupoD_InventarioSISE.dto.SubCategoriaDto;
import com.example.GrupoD_InventarioSISE.model.SubCategoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author RANDY
 */
public interface ISubCategoriaService {
    
    Page<SubCategoriaDto> Paginado(String search, Pageable pageable);
    Page<SubCategoriaDto> listarDtoPorCategoria(Long idcategoria, String search, Pageable pageable);
    SubCategoriaDto obtenerDtoPorId(Long id);
    SubCategoria obtenerPorId(Long id);
    void eliminar(Long id);
    void guardar(SubCategoria subcategoria);
    void actualizar(SubCategoria subcategoria);
}
