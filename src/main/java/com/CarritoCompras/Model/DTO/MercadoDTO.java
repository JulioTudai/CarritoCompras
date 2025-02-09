package com.CarritoCompras.Model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class MercadoDTO {
    private Long id;
    private String nombre;
    private List<Long> ventas;
    private List<Long> clientesId;
    private List<ProductoCantidadDTO> inventario;
}
