package com.CarritoCompras.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "ventas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @ManyToMany
    @JoinTable(
            name = "venta_productos",
            joinColumns = @JoinColumn(name = "venta_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<ProductoEntity> productosVendidos;

    @ElementCollection
    @CollectionTable(name = "venta_cantidad_productos", joinColumns = @JoinColumn(name = "venta_id"))
    @MapKeyJoinColumn(name = "producto_id")
    @Column(name = "cantidad")
    private Map<ProductoEntity, Integer> cantidadPorProducto;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private String medioDePago;

    @Column
    private Double descuento;

    @Column(nullable = false)
    private String estado;

    public void calcularTotal() {
        double totalCalculado = cantidadPorProducto.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();

        if (descuento != null && descuento > 0) {
            totalCalculado -= totalCalculado * (descuento / 100);
        }

        this.total = totalCalculado;
    }

    public boolean verificarStock() {
        return cantidadPorProducto.entrySet().stream()
                .allMatch(entry -> entry.getKey().getStock() >= entry.getValue());
    }

    public void asociarCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public void registrarProductos(Map<ProductoEntity, Integer> productosConCantidad) {
        this.cantidadPorProducto = productosConCantidad;
        this.productosVendidos = productosConCantidad.keySet().stream().toList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public List<ProductoEntity> getProductosVendidos() {
        return productosVendidos;
    }

    public void setProductosVendidos(List<ProductoEntity> productosVendidos) {
        this.productosVendidos = productosVendidos;
    }

    public Map<ProductoEntity, Integer> getCantidadPorProducto() {
        return cantidadPorProducto;
    }

    public void setCantidadPorProducto(Map<ProductoEntity, Integer> cantidadPorProducto) {
        this.cantidadPorProducto = cantidadPorProducto;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getMedioDePago() {
        return medioDePago;
    }

    public void setMedioDePago(String medioDePago) {
        this.medioDePago = medioDePago;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }



}
