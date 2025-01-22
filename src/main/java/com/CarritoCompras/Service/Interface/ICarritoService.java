package com.CarritoCompras.Service.Interface;

import com.CarritoCompras.Model.DTO.CarritoDTO;

public interface ICarritoService {


    CarritoDTO findByClienteId(Long clienteId);
    CarritoDTO saveCarrito(CarritoDTO carritoDTO);
    CarritoDTO updateCarrito(Long id, CarritoDTO carritoDTO);
}
