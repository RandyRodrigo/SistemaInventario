package com.example.GrupoD_InventarioSISE.iservice;

import com.example.GrupoD_InventarioSISE.model.TipoDocumento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITipoDocumentoService {
    
    Page<TipoDocumento> paginado (String search, Pageable pageable);
}
