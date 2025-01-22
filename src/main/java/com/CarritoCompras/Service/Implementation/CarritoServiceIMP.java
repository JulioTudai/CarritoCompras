package com.CarritoCompras.Service.Implementation;

import com.CarritoCompras.Mapper.IMapperCarrito;
import com.CarritoCompras.Model.DTO.CarritoDTO;
import com.CarritoCompras.Model.Entity.CarritoEntity;
import com.CarritoCompras.Repository.ICarritoRepository;
import com.CarritoCompras.Service.Interface.ICarritoService;
import org.springframework.beans.factory.annotation.Autowired;

public class CarritoServiceIMP implements ICarritoService {

    @Autowired
    ICarritoRepository carritoRepository;


    public CarritoDTO findByClienteId(Long clienteId){

        CarritoEntity carrito = this.carritoRepository.findByClienteId(clienteId);
        return IMapperCarrito.INSTANCE.toDTO(carrito);
    }

    public CarritoDTO saveCarrito(CarritoDTO carritoDTO){

        CarritoEntity carrito = this.carritoRepository.findById(carritoDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Carrito Existente"));

        return IMapperCarrito.INSTANCE.toDTO(this.carritoRepository.save(carrito));

    }
    public CarritoDTO updateCarrito(Long id, CarritoDTO carritoDTO) {
        CarritoEntity carrito = carritoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado"));


        carrito.setProductos(IMapperCarrito.INSTANCE.mapProductos(carritoDTO.getProductos()));
        return IMapperCarrito.INSTANCE.toDTO(carritoRepository.save(carrito));
    }
}
