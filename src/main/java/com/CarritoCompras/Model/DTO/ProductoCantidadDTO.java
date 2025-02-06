package com.CarritoCompras.Model.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoCantidadDTO {
    private Long productoId;
    private Integer cantidad;
}
