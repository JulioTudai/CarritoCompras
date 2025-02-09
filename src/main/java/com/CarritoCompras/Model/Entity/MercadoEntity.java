package com.CarritoCompras.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name ="mercado")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class MercadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @ElementCollection
    @CollectionTable(name = "mercado_ventas", joinColumns = @JoinColumn(name = "mercado_id"))
    @Column(name = "venta_id")
    private List<Long> ventas;

    @ElementCollection
    @CollectionTable(name = "mercado_clientes", joinColumns = @JoinColumn(name = "mercado_id"))
    @Column(name = "cliente_id")
    private List<Long> clientesId;

    @ElementCollection
    @CollectionTable(name = "mercado_inventario", joinColumns = @JoinColumn(name = "mercado_id"))
    private List<ProductoCantidad> inventario;

}
