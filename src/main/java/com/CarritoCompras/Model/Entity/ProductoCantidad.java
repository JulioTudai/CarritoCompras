package com.CarritoCompras.Model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductoCantidad {
    @Column(name = "producto_id", nullable = false) // Define el nombre de la columna y la restricción NOT NULL
    private Long productoId;

    @Column(name = "cantidad", nullable = false) // Define la columna cantidad y su restricción
    private Integer cantidad;
}
