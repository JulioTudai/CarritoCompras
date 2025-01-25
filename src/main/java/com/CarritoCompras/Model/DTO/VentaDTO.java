package com.CarritoCompras.Model.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class VentaDTO {

    private Long id;
    private LocalDateTime fechaHora;
    private Long clienteId; // Solo se envía el ID del cliente para simplificar
    private List<Long> productosVendidosIds; // Lista de IDs de productos vendidos
    private Map<Long, Integer> cantidadPorProducto; // Map de ProductoID -> Cantidad
    private Double total;
    private String medioDePago;
    private Double descuento;
    private String estadoVenta; // Ejemplo: "Pendiente", "Confirmada", "Cancelada"
}
