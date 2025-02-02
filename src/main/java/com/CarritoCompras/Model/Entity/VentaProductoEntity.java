package com.CarritoCompras.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "venta_producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaProductoEntity {

    @EmbeddedId
    private VentaProductoPK id = new VentaProductoPK();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ventaId")
    private VentaEntity venta;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productoId")
    private ProductoEntity producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Double precioUnitario;

}
