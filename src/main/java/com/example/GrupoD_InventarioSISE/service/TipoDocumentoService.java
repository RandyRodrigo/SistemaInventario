package com.example.GrupoD_InventarioSISE.service;

import com.example.GrupoD_InventarioSISE.iservice.ITipoDocumentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.GrupoD_InventarioSISE.model.TipoDocumento;
import com.example.GrupoD_InventarioSISE.repository.TipoDocumentoRepository;

/**
 *
 * @author RANDY
 */
@Service
public class TipoDocumentoService implements ITipoDocumentoService {

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Override
    public Page<TipoDocumento> paginado(String search, Pageable pageable) {
        if (search == null || search.isEmpty()) {
            return tipoDocumentoRepository.findAllActive(pageable);
        } else {
            return tipoDocumentoRepository.paginarTipoDocumentos(search, pageable);
        }
    }
    
}
