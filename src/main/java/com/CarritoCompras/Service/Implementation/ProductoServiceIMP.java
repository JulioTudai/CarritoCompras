package com.CarritoCompras.Service.Implementation;

import com.CarritoCompras.Model.Entity.ProductoEntity;
import com.CarritoCompras.Repository.IProductoRepository;
import com.CarritoCompras.Service.Interface.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceIMP implements IProductoService {


    @Autowired
    IProductoRepository productoRepository;


    @Override
    public ProductoEntity crearProducto(ProductoEntity productoEntity) {
        return productoRepository.save(productoEntity);
    }


    @Override
    public Optional<ProductoEntity> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }


    @Override
    public List<ProductoEntity> listarProductos() {
        return productoRepository.findAll();
    }


    @Override
    public Optional<ProductoEntity> actualizarProducto(Long id, ProductoEntity productoEntity) {
        return productoRepository.findById(id)
                .map(productoExistente -> {
                    // Actualizamos los campos necesarios
                    productoExistente.setNombre(productoEntity.getNombre());
                    productoExistente.setPrecio(productoEntity.getPrecio());
                    productoExistente.setStock(productoEntity.getStock());
                    productoExistente.setProveedorId(productoEntity.getProveedorId());
                    return productoRepository.save(productoExistente);
                });
    }


    @Override
    public boolean eliminarProducto(Long id) {
        return productoRepository.findById(id)
                .map(productoExistente -> {
                    productoRepository.delete(productoExistente);
                    return true;
                }).orElse(false);
    }

}








