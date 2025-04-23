/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.service;

import com.example.GrupoD_InventarioSISE.iservice.IDepartamentoService;
import com.example.GrupoD_InventarioSISE.model.Departamento;
import com.example.GrupoD_InventarioSISE.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author RANDY
 */
@Service
public class DepartamentoService implements IDepartamentoService{

    @Autowired
    private DepartamentoRepository departamentoRepository;
    
    @Override
    public Page<Departamento> paginado(String search, Pageable pageable) {
        if (search == null || search.isEmpty()) {
            return departamentoRepository.findAllActive(pageable);
        } else {
            return departamentoRepository.paginarDepartamentos(search, pageable);
        }
    }

    @Override
    public Departamento listarPorId(Long id) {
        return departamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado con ID: " + id));
    }

    @Override
    public void eliminar(Long id) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado con ID: " + id));
        departamento.setEstado_auditoria(false); // Cambiar el estado a inactivo
        departamentoRepository.save(departamento);
    }

    @Override
    public void guardar(Departamento departamento) {
        departamento.setEstado_auditoria(true); // Establecer el estado como activo
        departamentoRepository.save(departamento);
    }

    @Override
    public void actualizar(Departamento departamento) {
        departamentoRepository.findById(departamento.getId())
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado con ID: " + departamento.getId()));
        departamentoRepository.save(departamento);
    }
    
}
