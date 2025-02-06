package com.CarritoCompras.Service.Interface;

import com.CarritoCompras.Model.DTO.VentaDTO;

import java.util.List;

public interface IVentaService {

    List<VentaDTO> obtenerTodas();

    VentaDTO obtenerPorId(Long id);

    VentaDTO crearVenta(VentaDTO ventaDTO);

    VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO);

    void eliminarVenta(Long id);
}
