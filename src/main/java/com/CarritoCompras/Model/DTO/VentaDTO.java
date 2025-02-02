package com.CarritoCompras.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {

    private Long id;
    private String fechaVenta;
    private Double total; // Monto total de la venta
    private List<Long> productoIds; // IDs de los productos vendidos
    private Map<Long, Integer> cantidades;
}
