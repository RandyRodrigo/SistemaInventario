/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.service;

import com.example.GrupoD_InventarioSISE.iservice.IMarcaService;
import com.example.GrupoD_InventarioSISE.model.Marca;
import com.example.GrupoD_InventarioSISE.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *
 * @author RANDY
 */
@Service
public class MarcaService implements IMarcaService{
    
    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public Page<Marca> paginado(String search, Pageable pageable) {
        if (search == null || search.isEmpty()) {
            return marcaRepository.findAllActive(pageable);
        } else {
            return marcaRepository.paginarMarcas(search, pageable);
        }
    }

    public List<Marca> listarTodas() {
        return marcaRepository.findAll();
    }

    @Override
    public Marca listarPorId(Long id) {
        return marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con ID: " + id));
    }

    @Override
    public Marca guardar(Marca marca) {
        marca.setEstado_auditoria(true); // Establecer el estado como activo
        return marcaRepository.save(marca);
    }

    @Override
    public void eliminar(Long id) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con ID: " + id));
        marca.setEstado_auditoria(false); // Cambiar el estado a inactivo
        marcaRepository.save(marca);
    }

    @Override
    public void actualizar(Marca marca) {
        marcaRepository.findById(marca.getId())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con ID: " + marca.getId()));
        marcaRepository.save(marca);
    }
}
