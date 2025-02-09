package com.CarritoCompras.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorDTO {
    private Long id;
    private String nombre;
    private String email;
    private String celular;
    private String compania;
    private List<Long> productosIds;
}
