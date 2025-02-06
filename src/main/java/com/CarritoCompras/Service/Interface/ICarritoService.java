package com.CarritoCompras.Service.Interface;

import com.CarritoCompras.Model.DTO.CarritoDTO;

public interface ICarritoService {


    // Obtener el carrito de un cliente por su ID
    CarritoDTO obtenerCarritoPorClienteId(Long clienteId);

    // Agregar un producto al carrito
    void agregarProductoAlCarrito(Long carritoId, Long productoId, Integer cantidad);

    // Eliminar un producto del carrito
    void eliminarProductoDelCarrito(Long carritoId, Long productoId);

    // Modificar la cantidad de un producto en el carrito
    void modificarCantidadProductoEnCarrito(Long carritoId, Long productoId, Integer nuevaCantidad);

    // Crear un nuevo carrito
    CarritoDTO crearCarrito(CarritoDTO carritoDTO);

    // Eliminar un carrito
    void eliminarCarrito(Long carritoId);
}
