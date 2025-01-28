package com.CarritoCompras.Repository;


import com.CarritoCompras.Model.Entity.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IVentaRepository extends JpaRepository<VentaEntity, Long> {
    // Si necesitas agregar métodos personalizados, puedes definirlos aquí

    // Ejemplo de consulta por cliente
    List<VentaEntity> findByClienteId(Long clienteId);

    // Ejemplo de consulta por rango de fechas
    List<VentaEntity> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
}
