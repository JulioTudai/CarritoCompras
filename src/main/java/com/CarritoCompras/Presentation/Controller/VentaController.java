package com.CarritoCompras.Presentation.Controller;

import com.CarritoCompras.Model.DTO.VentaDTO;
import com.CarritoCompras.Service.Interface.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/ventas")

public class VentaController {



        @Autowired
        IVentaService ventaService;

        @GetMapping
        public ResponseEntity<List<VentaDTO>> obtenerTodas() {
            return ResponseEntity.ok(ventaService.obtenerTodas());
        }

        @GetMapping("/{id}")
        public ResponseEntity<VentaDTO> obtenerPorId(@PathVariable Long id) {
            return ResponseEntity.ok(ventaService.obtenerPorId(id));
        }

        @PostMapping
        public ResponseEntity<VentaDTO> crearVenta(@RequestBody VentaDTO ventaDTO) {
            return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.crearVenta(ventaDTO));
        }

        @PutMapping("/{id}")
        public ResponseEntity<VentaDTO> actualizarVenta(@PathVariable Long id, @RequestBody VentaDTO ventaDTO) {
            return ResponseEntity.ok(ventaService.actualizarVenta(id, ventaDTO));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
            ventaService.eliminarVenta(id);
            return ResponseEntity.noContent().build();
        }

}
