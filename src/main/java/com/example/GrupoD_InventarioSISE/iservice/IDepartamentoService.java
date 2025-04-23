/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.iservice;

import com.example.GrupoD_InventarioSISE.model.Departamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author RANDY
 */
public interface IDepartamentoService {
    
    Page<Departamento> paginado (String search, Pageable pageable);
    Departamento listarPorId(Long id);
    void eliminar(Long id);
    void guardar(Departamento departamento);
    void actualizar(Departamento departamento);
}
