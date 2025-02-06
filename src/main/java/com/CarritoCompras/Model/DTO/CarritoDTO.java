package com.CarritoCompras.Model.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoDTO {

    private Long id;
    private Long clienteId;
    private List<ProductoCantidadDTO> productosAgregados;

}
