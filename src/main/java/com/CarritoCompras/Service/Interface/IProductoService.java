package com.CarritoCompras.Service.Interface;


import com.CarritoCompras.Model.Entity.ProductoEntity;

import java.util.List;
import java.util.Optional;

public interface IProductoService {


    ProductoEntity crearProducto(ProductoEntity productoEntity);

    Optional<ProductoEntity> obtenerProductoPorId(Long id);

    List<ProductoEntity> listarProductos();

    Optional<ProductoEntity> actualizarProducto(Long id, ProductoEntity productoEntity);

    boolean eliminarProducto(Long id);
}
