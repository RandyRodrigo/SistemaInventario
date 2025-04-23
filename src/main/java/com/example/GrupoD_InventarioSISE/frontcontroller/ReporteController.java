package com.example.GrupoD_InventarioSISE.frontcontroller;

import com.example.GrupoD_InventarioSISE.dto.ProductoDto;
import com.example.GrupoD_InventarioSISE.iservice.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reporte")
public class ReporteController {

    @Autowired
    private IProductoService productoService;
    
    // Umbral predeterminado para stock bajo
    private static final int STOCK_BAJO_DEFAULT = 5;
    
    @GetMapping
    public String dashboard(Model model) {
        // Contar productos con stock bajo para mostrar en el dashboard
        long productosStockBajo = productoService.contarProductosStockBajo(STOCK_BAJO_DEFAULT);
        model.addAttribute("cantidadStockBajo", productosStockBajo);
        model.addAttribute("umbralStockBajo", STOCK_BAJO_DEFAULT);
        
        return "reporte/dashboard";
    }
    
    @GetMapping("/stock-bajo")
    public String stockBajo(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer umbral) {
        
        // Si no se proporciona un umbral, usar el predeterminado
        int umbralFinal = (umbral != null) ? umbral : STOCK_BAJO_DEFAULT;
        
        // Obtener productos con stock bajo
        Page<ProductoDto> productos = productoService.obtenerProductosStockBajo(
                umbralFinal, 
                PageRequest.of(page, size, Sort.by("stock").ascending())
        );
        
        model.addAttribute("productos", productos);
        model.addAttribute("umbralActual", umbralFinal);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productos.getTotalPages());
        model.addAttribute("size", size);
        
        return "reporte/stock-bajo";
    }
}