/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.repository;

import com.example.GrupoD_InventarioSISE.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

/**
 *
 * @author RANDY
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {

        @Query("SELECT p FROM Producto p WHERE " +
                        "LOWER(p.subcategoria.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                        "LOWER(p.marca.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                        "LOWER(p.proveedor.nombre_comercial) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                        "LOWER(p.codigo) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                        "LOWER(p.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                        "LOWER(p.descripcion) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                        "LOWER(p.especificaciones) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                        "CONCAT(p.precio, '') LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                        "CONCAT(p.stock, '') LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                        "LOWER(p.imagen_url) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                        "LOWER(p.informacion_fabricante_url) LIKE LOWER(CONCAT('%', :search, '%'))")
        Page<Producto> paginarProductos(@Param("search") String search, Pageable pageable);

        @Query("SELECT p FROM Producto p WHERE p.idsubcategoria = :idsubcategoria AND " +
                        "(LOWER(p.subcategoria.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                        "LOWER(p.marca.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                        "LOWER(p.proveedor.nombre_comercial) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                        "LOWER(p.codigo) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                        "LOWER(p.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                        "LOWER(p.descripcion) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                        "LOWER(p.especificaciones) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                        "CONCAT(p.precio, '') LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                        "CONCAT(p.stock, '') LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                        "LOWER(p.imagen_url) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                        "LOWER(p.informacion_fabricante_url) LIKE LOWER(CONCAT('%', :search, '%')))")
        Page<Producto> paginarProductosPorSubCategoria(@Param("idsubcategoria") Long idsubcategoria,
                        @Param("search") String search, Pageable pageable);

        @Query("SELECT p FROM Producto p WHERE p.estado_auditoria = true ")
        Page<Producto> findAllActive(Pageable Pageable);

        @Query("SELECT p FROM Producto p WHERE p.id = :id")
        Optional<Producto> findByIdIncludingInactive(@Param("id") Long id);

        @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Producto p WHERE p.idsubcategoria = :idsubcategoria AND p.estado_auditoria = true")
        boolean existsByIdsubcategoriaAndEstado_auditoriaTrue(@Param("idsubcategoria") Long idsubcategoria);

        @Query("SELECT p FROM Producto p WHERE p.stock <= :umbral AND p.estado_auditoria = true ORDER BY p.stock")
        Page<Producto> findByStockLessThanEqualAndEstado_auditoriaTrue(@Param("umbral") int umbral, Pageable pageable);

        @Query("SELECT COUNT(p) FROM Producto p WHERE p.stock <= :umbral AND p.estado_auditoria = true")
        long countByStockLessThanEqualAndEstado_auditoriaTrue(@Param("umbral") int umbral);
}
