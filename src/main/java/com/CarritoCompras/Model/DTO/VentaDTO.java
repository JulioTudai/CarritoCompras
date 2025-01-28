package com.CarritoCompras.Model.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class VentaDTO {

    private Long id;
    private LocalDateTime fechaHora;
    private Long clienteId;
    private List<Long> productosVendidosIds;
    private Map<Long, Integer> cantidadPorProducto;
    private Double total;
    private String medioDePago;
    private Double descuento;
    private String estadoVenta;
}
