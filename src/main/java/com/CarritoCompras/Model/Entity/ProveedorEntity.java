package com.CarritoCompras.Model.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="Proveedor")
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
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    @Column
    private String companyName;

    @ElementCollection
    @CollectionTable(name = "proveedor_productos", joinColumns = @JoinColumn(name = "proveedor_id"))
    @Column(name = "producto")
    private List<String> products;
}
