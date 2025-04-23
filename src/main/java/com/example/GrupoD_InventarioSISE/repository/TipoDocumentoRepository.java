package com.example.GrupoD_InventarioSISE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.GrupoD_InventarioSISE.model.TipoDocumento;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {
    
    @Query("SELECT t FROM TipoDocumento t WHERE " +
            "LOWER(t.nombre) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<TipoDocumento> paginarTipoDocumentos(@Param("search") String search, Pageable pageable);

    @Query("SELECT t FROM TipoDocumento t")
    Page<TipoDocumento> findAllActive(Pageable pageable);
}
