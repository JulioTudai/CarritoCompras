package com.CarritoCompras.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private long id;
    private String name;
    private String lastName;
    private String email;
    private long age;
}
