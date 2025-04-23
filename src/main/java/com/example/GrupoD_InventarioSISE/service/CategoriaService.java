/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.service;

import com.example.GrupoD_InventarioSISE.dto.CategoriaDto;
import com.example.GrupoD_InventarioSISE.iservice.ICategoriaService;
import com.example.GrupoD_InventarioSISE.mapper.CategoriaMapper;
import com.example.GrupoD_InventarioSISE.model.Categoria;
import com.example.GrupoD_InventarioSISE.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author RANDY
 */
@Service
public class CategoriaService implements ICategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Page<CategoriaDto> Paginado(String search, Pageable pageable) {
        Page<Categoria> categorias;
        if (search == null || search.isEmpty()) {
            categorias = categoriaRepository.findAllActive(pageable);
        } else {
            categorias = categoriaRepository.paginarCategorias(search, pageable);
        }
        return CategoriaMapper.toDtoList(categorias, pageable);
    }

    @Override
    public Page<CategoriaDto> listarDtoPorDepartamento(Long iddepartamento, String search, Pageable pageable) {
        if (search == null || search.isEmpty()) {
            search = "";
        }
        Page<Categoria> categorias = categoriaRepository.paginarCategoriasPorDepartamento(iddepartamento, search, pageable);
        return CategoriaMapper.toDtoList(categorias, pageable);
    }
    
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    @Override
    public CategoriaDto obtenerDtoPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
        return CategoriaMapper.toDto(categoria);
    }

    @Override
    public Categoria obtenerPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
    }

    @Override
    public void eliminar(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
        categoria.setEstado_auditoria(false);
        categoriaRepository.save(categoria);
    }

    @Override
    public void guardar(Categoria categoria) {
        categoria.setEstado_auditoria(true);
        categoriaRepository.save(categoria);
    }

    @Override
    public void actualizar(Categoria categoria) {
        categoriaRepository.findById(categoria.getId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + categoria.getId()));

        categoriaRepository.save(categoria);
    }
}
