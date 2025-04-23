/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.iservice;

import com.example.GrupoD_InventarioSISE.model.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author RANDY
 */
public interface IMarcaService {
    
    Page<Marca> paginado (String search, Pageable pageable);
    Marca listarPorId(Long id);
    Marca guardar(Marca marca);
    void eliminar(Long id);
    void actualizar(Marca marca);
}
