package com.CarritoCompras.Service.Implementation;

import com.CarritoCompras.Mapper.IMapperCarrito;
import com.CarritoCompras.Model.DTO.CarritoDTO;
import com.CarritoCompras.Model.Entity.CarritoEntity;
import com.CarritoCompras.Repository.ICarritoRepository;
import com.CarritoCompras.Service.Interface.ICarritoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CarritoServiceIMP implements ICarritoService {

    @Autowired
    private ICarritoRepository carritoRepository;

    @Autowired
    private IMapperCarrito carritoMapper;

    @Override
    public CarritoDTO obtenerCarritoPorClienteId(Long clienteId) {
        // Buscar el carrito con el clienteId
        Optional<CarritoEntity> carritoOptional = carritoRepository.findByClienteId(clienteId);

        // Si no se encuentra el carrito, retornamos null (o podrías lanzar una excepción o retornar un DTO vacío)
        if (carritoOptional.isEmpty()) {
            return null;  // O podrías devolver un nuevo CarritoDTO() si prefieres un objeto vacío
        }

        // Convertimos la entidad a DTO usando el mapper
        CarritoEntity carritoEntity = carritoOptional.get();
        return carritoMapper.carritoEntityToCarritoDTO(carritoEntity);
    }

    @Override
    public void agregarProductoAlCarrito(Long carritoId, Long productoId, Integer cantidad) {
        CarritoEntity carritoEntity = carritoRepository.findById(carritoId).orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        carritoEntity.agregarProducto(productoId, cantidad);
        carritoRepository.save(carritoEntity);
    }

    @Override
    public void eliminarProductoDelCarrito(Long carritoId, Long productoId) {
        CarritoEntity carritoEntity = carritoRepository.findById(carritoId).orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        carritoEntity.eliminarProducto(productoId);
        carritoRepository.save(carritoEntity);
    }

    @Override
    public void modificarCantidadProductoEnCarrito(Long carritoId, Long productoId, Integer nuevaCantidad) {
        CarritoEntity carritoEntity = carritoRepository.findById(carritoId).orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        carritoEntity.modificarCantidadProducto(productoId, nuevaCantidad);
        carritoRepository.save(carritoEntity);
    }

    @Override
    public CarritoDTO crearCarrito(CarritoDTO carritoDTO) {
        CarritoEntity carritoEntity = carritoMapper.carritoDTOToCarritoEntity(carritoDTO);
        return carritoMapper.carritoEntityToCarritoDTO(carritoRepository.save(carritoEntity));
    }

    @Override
    public void eliminarCarrito(Long carritoId) {
        carritoRepository.deleteById(carritoId);
    }
}
