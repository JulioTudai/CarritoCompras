package com.CarritoCompras.Model.DTO;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MercadoDTO {
    private Long id;
    private String nombre;
    private Map<Long, Integer> productos; // Mapea IDs de productos a su stock
    private List<Long> ventasIds; // Lista de IDs de ventas
    private List<Long> clientesIds; // Lista de IDs de clientes
    private Double ingresosTotales;
}
