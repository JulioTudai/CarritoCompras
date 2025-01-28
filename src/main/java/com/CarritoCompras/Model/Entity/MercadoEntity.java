package com.CarritoCompras.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;
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

    @Column(nullable = false, unique = true)
    private String nombre;

    @ElementCollection
    @CollectionTable(name = "mercado_productos", joinColumns = @JoinColumn(name = "mercado_id"))
    @MapKeyJoinColumn(name = "producto_id")
    @Column(name = "stock")
    private Map<ProductoEntity, Integer> productos;

    @OneToMany(mappedBy = "mercado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VentaEntity> ventas;

    @OneToMany(mappedBy = "mercado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClienteEntity> clientes;

    @Column(nullable = false)
    private Double ingresosTotales = 0.0;

}
