package com.CarritoCompras.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ventas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generación del ID
    private Long id;

    @Column(nullable = false) // No puede ser null
    private Long carritoId;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false)
    private Long clienteId;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private Double descuento;

    // Relación de la venta con productos y sus cantidades
    @ElementCollection
    @CollectionTable(
            name = "productos_vendidos",
            joinColumns = @JoinColumn(name = "venta_id") // Clave foránea hacia `VentaEntity`
    )
    @Column(name = "cantidad") // Define la columna que almacena la cantidad
    private List<ProductoCantidad> productosVendidos;


    public void calcularTotal(List<Double> preciosProductos) {
        if (productosVendidos == null || productosVendidos.isEmpty()) {
            this.total = 0.0;
            return;
        }

        double sumaTotal = 0.0;
        for (int i = 0; i < productosVendidos.size(); i++) {
            ProductoCantidad pc = productosVendidos.get(i);
            Double precio = preciosProductos.get(i);
            sumaTotal += pc.getCantidad() * precio;
        }
        this.total = sumaTotal;
    }


    public void aplicarDescuento(double porcentajeDescuento) {
        if (total != null && total > 0 && porcentajeDescuento > 0) {
            this.descuento = total * (porcentajeDescuento / 100);
            this.total -= this.descuento;
        } else {
            this.descuento = 0.0;
        }
    }

    public boolean validarStockSuficiente(List<Integer> stockDisponible) {
        for (int i = 0; i < productosVendidos.size(); i++) {
            if (productosVendidos.get(i).getCantidad() > stockDisponible.get(i)) {
                return false;
            }
        }
        return true;
    }



}
