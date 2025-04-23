/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.repository;

import com.example.GrupoD_InventarioSISE.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author RANDY
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
     
    @Query("SELECT c FROM Categoria c WHERE " +
            "LOWER(c.departamento.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(c.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(c.imagen_url) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Categoria> paginarCategorias(@Param("search") String search, Pageable pageable);
    
    @Query("SELECT c FROM Categoria c WHERE c.iddepartamento = :iddepartamento AND " +
            "(LOWER(c.departamento.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(c.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(c.imagen_url) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Categoria> paginarCategoriasPorDepartamento(@Param("iddepartamento") Long iddepartamento, @Param("search") String search, Pageable pageable);

    @Query("SELECT c FROM Categoria c WHERE c.estado_auditoria = true ")
    Page<Categoria> findAllActive(Pageable Pageable);
}
