package com.CarritoCompras.Repository;


import com.CarritoCompras.Model.Entity.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IVentaRepository extends JpaRepository<VentaEntity, Long> {
    // Encuentra todas las ventas realizadas por un cliente específico
    List<VentaEntity> findByClienteId(Long clienteId);

    // Encuentra todas las ventas que contienen un carrito específico
    List<VentaEntity> findByCarritoId(Long carritoId);

    // Encuentra ventas realizadas en una fecha específica (ejemplo básico)
    List<VentaEntity> findByFechaHoraBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
