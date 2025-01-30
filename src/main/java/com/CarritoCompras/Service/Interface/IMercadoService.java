package com.CarritoCompras.Service.Interface;

import com.CarritoCompras.Model.DTO.MercadoDTO;
import com.CarritoCompras.Model.DTO.ProductoDTO;
import com.CarritoCompras.Model.DTO.VentaDTO;
import com.CarritoCompras.Model.Entity.VentaEntity;

import java.util.List;

public interface IMercadoService {

    MercadoDTO findMercadoById(Long id);

    VentaDTO registrarCompra(Long mercadoId, VentaDTO ventaDTO);

    List<ProductoDTO> consultarStock(Long mercadoId);

    void agregarProducto(Long mercadoId, ProductoDTO productoDTO);

    List<VentaEntity> consultarVentas(Long mercadoId);

    Double generarReporteIngresos(Long mercadoId);
}
