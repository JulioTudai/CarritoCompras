package com.CarritoCompras.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrito")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long clienteId;

    @ElementCollection
    @CollectionTable(
            name = "productos_carrito",
            joinColumns = @JoinColumn(name = "carrito_id")
    )
    @Column(name = "cantidad")
    private List<ProductoCantidad> productosAgregados = new ArrayList<>();


    public void agregarProducto(Long productoId, Integer cantidad) {
        productosAgregados.add(new ProductoCantidad(productoId, cantidad));
    }


    public void eliminarProducto(Long productoId) {
        productosAgregados.removeIf(producto -> producto.getProductoId().equals(productoId));
    }


    public void modificarCantidadProducto(Long productoId, Integer nuevaCantidad) {
        for (ProductoCantidad producto : productosAgregados) {
            if (producto.getProductoId().equals(productoId)) {
                producto.setCantidad(nuevaCantidad);
                break;
            }
        }
    }
}

