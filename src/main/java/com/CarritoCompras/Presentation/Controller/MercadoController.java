package com.CarritoCompras.Presentation.Controller;

import com.CarritoCompras.Model.DTO.MercadoDTO;
import com.CarritoCompras.Model.DTO.ProductoDTO;
import com.CarritoCompras.Model.DTO.VentaDTO;
import com.CarritoCompras.Service.Interface.IMercadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/mercado")
public class MercadoController {
    @Autowired
    private IMercadoService mercadoService;

    // Obtener un mercado por su ID
    @GetMapping("/{id}")
    public ResponseEntity<MercadoDTO> obtenerMercadoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mercadoService.findMercadoById(id));
    }

    // Registrar una compra
    @PostMapping("/{mercadoId}/compra")
    public ResponseEntity<String> registrarCompra(@PathVariable Long mercadoId, @RequestBody Long ventaId) {

        mercadoService.registrarCompra(mercadoId,ventaId); //terminar venta
        return ResponseEntity.ok("Compra registrada con éxito.");
    }

    // Consultar stock
    @GetMapping("/{mercadoId}/stock")
    public ResponseEntity<List<ProductoDTO>> consultarStock(@PathVariable Long mercadoId) {
        return ResponseEntity.ok(mercadoService.consultarStock(mercadoId));
    }

    // Agregar producto al mercado
    @PostMapping("/{mercadoId}/productos")
    public ResponseEntity<String> agregarProducto(
            @PathVariable Long mercadoId,
            @RequestBody ProductoDTO productoDTO) {
        mercadoService.agregarProducto(mercadoId, productoDTO);
        return ResponseEntity.ok("Producto agregado con éxito.");
    }

    // Consultar ventas del mercado
    @GetMapping("/{mercadoId}/ventas")
    public ResponseEntity<?> consultarVentas(@PathVariable Long mercadoId) {
        return ResponseEntity.ok(mercadoService.consultarVentas(mercadoId));
    }

    // Generar reporte de ingresos
    @GetMapping("/{mercadoId}/reporte-ingresos")
    public ResponseEntity<Double> generarReporteDeIngresos(@PathVariable Long mercadoId) {
        return ResponseEntity.ok(mercadoService.generarReporteIngresos(mercadoId));
    }
}
