/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.repository;

import com.example.GrupoD_InventarioSISE.model.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author RANDY
 */
public interface MarcaRepository extends JpaRepository<Marca, Long>{
    
    @Query("SELECT m FROM Marca m WHERE " +
            "LOWER(m.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(m.logo_url) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Marca> paginarMarcas(@Param("search") String search, Pageable pageable);

    @Query("SELECT m FROM Marca m WHERE m.estado_auditoria = true ")
    Page<Marca> findAllActive(Pageable Pageable);
}
