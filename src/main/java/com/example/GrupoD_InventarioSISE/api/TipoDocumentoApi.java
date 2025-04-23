package com.example.GrupoD_InventarioSISE.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.GrupoD_InventarioSISE.iservice.ITipoDocumentoService;
import com.example.GrupoD_InventarioSISE.model.TipoDocumento;

/**
 *
 * @author RANDY
 */
@RestController
@RequestMapping("/api/tipodocumento")
public class TipoDocumentoApi {
    
    @Autowired
    private ITipoDocumentoService iTipoDocumentoService;

    @GetMapping("/listartodo")
    public Page<TipoDocumento> listartodo(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(value = "search", required = false) String search
            ) {
        return iTipoDocumentoService.paginado(search, PageRequest.of(page, size));
        }
}
