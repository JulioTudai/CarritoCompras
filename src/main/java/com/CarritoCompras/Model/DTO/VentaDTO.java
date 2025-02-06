package com.CarritoCompras.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {

    private Long id;
    private Long carritoId;
    private LocalDateTime fechaHora;
    private Long clienteId;
    private Double total;
    private Double descuento;

    // Productos vendidos con sus cantidades
    private List<ProductoCantidadDTO> productosVendidos;
}
