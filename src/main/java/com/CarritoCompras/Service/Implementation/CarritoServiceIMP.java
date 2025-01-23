package com.CarritoCompras.Service.Implementation;

import com.CarritoCompras.Mapper.IMapperCarrito;
import com.CarritoCompras.Model.DTO.CarritoDTO;
import com.CarritoCompras.Model.Entity.CarritoEntity;
import com.CarritoCompras.Model.Entity.ProductoEntity;
import com.CarritoCompras.Repository.ClienteRepository;
import com.CarritoCompras.Repository.ICarritoRepository;
import com.CarritoCompras.Repository.ProductoRepository;
import com.CarritoCompras.Service.Interface.ICarritoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class CarritoServiceIMP implements ICarritoService {

    @Autowired
    ICarritoRepository carritoRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    ClienteRepository clienteRepository;

    private List<ProductoEntity> mapearIdsAProductos(List<Long> productoIds) {
        return productoIds.stream()
                .map(id -> productoRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + id)))
                .collect(Collectors.toList());
    }
    public CarritoDTO findCarritoById(Long id) {
        CarritoEntity carritoEntity = carritoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado con ID: " + id));
        return IMapperCarrito.INSTANCE.toDTO((carritoEntity));
    }


    public CarritoDTO findByClienteId(Long clienteId){

        CarritoEntity carrito = this.carritoRepository.findByClienteId(clienteId);
        return IMapperCarrito.INSTANCE.toDTO(carrito);
    }

    public CarritoDTO saveCarrito(CarritoDTO carritoDTO) {
        CarritoEntity carritoEntity = IMapperCarrito.INSTANCE.toEntity(carritoDTO);
        CarritoEntity savedCarrito = carritoRepository.save(carritoEntity);
        return IMapperCarrito.INSTANCE.toDTO(savedCarrito);
    }

    public CarritoDTO updateCarrito(Long id, CarritoDTO carritoDTO) {
        CarritoEntity carrito = carritoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado"));

        if (carritoDTO.getClienteId() != null) {
            carrito.setCliente(clienteRepository.findById(carritoDTO.getClienteId())
                    .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + carritoDTO.getClienteId())));
        }
        if (carritoDTO.getProductos() != null) {
            carritoEntity.setProductos(mapperCarrito.mapProductos(carritoDTO.getProductos())); // Mapea IDs a entidades
        }


        carrito.setProductos(IMapperCarrito.INSTANCE.mapProductos(carritoDTO.getProductos()));
        return IMapperCarrito.INSTANCE.toDTO(carritoRepository.save(carrito));
    }
}
