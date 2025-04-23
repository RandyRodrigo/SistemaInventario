/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.service;

import com.example.GrupoD_InventarioSISE.dto.SubCategoriaDto;
import com.example.GrupoD_InventarioSISE.iservice.ISubCategoriaService;
import com.example.GrupoD_InventarioSISE.mapper.SubCategoriaMapper;
import com.example.GrupoD_InventarioSISE.model.SubCategoria;
import com.example.GrupoD_InventarioSISE.repository.ProductoRepository;
import com.example.GrupoD_InventarioSISE.repository.SubCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author RANDY
 */
@Service
public class SubCategoriaService implements ISubCategoriaService{
    
    @Autowired
    private SubCategoriaRepository subCategoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Page<SubCategoriaDto> Paginado(String search, Pageable pageable) {
        Page<SubCategoria> subcategorias;
        if (search == null || search.isEmpty()) {
            subcategorias = subCategoriaRepository.findAllActive(pageable);
        } else {
            subcategorias = subCategoriaRepository.paginarSubCategorias(search, pageable);
        }
        return SubCategoriaMapper.toDtoList(subcategorias, pageable);
    }
    
    @Override
    public Page<SubCategoriaDto> listarDtoPorCategoria(Long idcategoria, String search, Pageable pageable) {
        if (search == null || search.isEmpty()) {
            search = "";
        }
        Page<SubCategoria> subcategorias = subCategoriaRepository.paginarSubCategoriasPorCategoria(idcategoria, search, pageable);
        return SubCategoriaMapper.toDtoList(subcategorias, pageable);
    }

    @Override
    public void guardar(SubCategoria subcategoria) {
        subcategoria.setEstado_auditoria(true);
        subCategoriaRepository.save(subcategoria);
    }

    @Override
    public SubCategoriaDto obtenerDtoPorId(Long id) {
        SubCategoria subcategoria = subCategoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subcategoría no encontrada con ID: " + id));
        return SubCategoriaMapper.toDto(subcategoria);
    }

    @Override
    public SubCategoria obtenerPorId(Long id) {
        return subCategoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subcategoría no encontrada con ID: " + id));
    }

    @Override
    public void eliminar(Long id) {
        boolean existenProductos = productoRepository.existsByIdsubcategoriaAndEstado_auditoriaTrue(id);
        
        if (existenProductos) {
            throw new IllegalStateException("No se puede eliminar la subcategoría porque tiene productos asociados");
        }
        SubCategoria subcategoria = obtenerPorId(id);
        subcategoria.setEstado_auditoria(false);
        subCategoriaRepository.save(subcategoria);
    }

    @Override
    public void actualizar(SubCategoria subcategoria) {
        subCategoriaRepository.findById(subcategoria.getId())
                .orElseThrow(() -> new RuntimeException("Subcategoría no encontrada con ID: " + subcategoria.getId()));

        subCategoriaRepository.save(subcategoria);
    }

}
