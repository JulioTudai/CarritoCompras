package com.CarritoCompras.Service.Interface;

import com.CarritoCompras.Model.DTO.VentaDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IVentaService {

    // Crear una nueva venta
    VentaDTO saveVenta(VentaDTO ventaDTO);

    // Buscar una venta por ID
    VentaDTO findVentaById(Long id);

    // Obtener todas las ventas
    List<VentaDTO> findAllVentas();

    // Buscar ventas por ID de cliente
    List<VentaDTO> findVentasByClienteId(Long clienteId);

    // Buscar ventas dentro de un rango de fechas
    List<VentaDTO> findVentasByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    // Eliminar una venta por ID
    void deleteVenta(Long id);
}
