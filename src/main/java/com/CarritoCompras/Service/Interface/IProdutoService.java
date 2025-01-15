package com.CarritoCompras.Service.Interface;


import com.CarritoCompras.Model.Entity.ProductoEntity;

import java.util.List;

public interface IProdutoService {

    List<ProductoEntity> findAll();

    ProductoEntity findById(Long id);

    ProductoEntity saveProducto(ProductoEntity entity);

    ProductoEntity updateProducto(Long id, ProductoEntity entity);

    String deletProducto(Long id);
}
