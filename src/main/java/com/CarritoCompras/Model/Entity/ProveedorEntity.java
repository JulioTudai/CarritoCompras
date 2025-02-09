package com.CarritoCompras.Model.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="proveedor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String celular;

    @Column(nullable = false)
    private String compania;

    @ElementCollection
    @CollectionTable(name = "proveedor_productos", joinColumns = @JoinColumn(name = "proveedor_id"))
    @Column(name = "producto_id")
    private List<Long> productosIds;
}
