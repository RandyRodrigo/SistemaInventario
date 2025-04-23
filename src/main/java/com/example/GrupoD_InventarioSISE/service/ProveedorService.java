/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.service;

import com.example.GrupoD_InventarioSISE.dto.ProveedorDto;
import com.example.GrupoD_InventarioSISE.iservice.IProveedorService;
import com.example.GrupoD_InventarioSISE.mapper.ProveedorMapper;
import com.example.GrupoD_InventarioSISE.model.Proveedor;
import com.example.GrupoD_InventarioSISE.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author RANDY
 */
@Service
public class ProveedorService implements IProveedorService{
    
    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public Page<ProveedorDto> Paginado(String search, Pageable pageable) {
        Page<Proveedor> proveedores;
        if (search == null || search.isEmpty()) {
            proveedores = proveedorRepository.findAllActive(pageable);
        } else {
            proveedores = proveedorRepository.paginarProveedores(search, pageable);
        }
        return ProveedorMapper.toDtoList(proveedores, pageable);
    }

    @Override
    public ProveedorDto obtenerDtoPorId(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));
        return ProveedorMapper.toDto(proveedor);
    }

    @Override
    public Proveedor obtenerPorId(Long id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));
    }

    @Override
    public void guardar(Proveedor proveedor) {
        proveedor.setEstado_auditoria(true); // Establecer el estado de auditoría como activo
        proveedorRepository.save(proveedor); // Guardar el proveedor en la base de datos
    }

    @Override
    public void actualizar(Proveedor proveedor) {
        proveedorRepository.findById(proveedor.getId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + proveedor.getId()));
        proveedorRepository.save(proveedor); // Guardar el proveedor actualizado en la base de datos
    }

    @Override
    public void eliminar(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));
        proveedor.setEstado_auditoria(false); // Establecer el estado de auditoría como inactivo
        proveedorRepository.save(proveedor); // Guardar el proveedor actualizado en la base de datos
    }
    
}
