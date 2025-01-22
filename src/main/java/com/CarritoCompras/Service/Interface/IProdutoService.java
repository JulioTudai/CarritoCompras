package com.CarritoCompras.Service.Interface;


import com.CarritoCompras.Model.DTO.ProductoDTO;
import com.CarritoCompras.Model.DTO.ProductoProveedorDTO;

import java.util.List;

public interface IProdutoService {

    List<ProductoDTO> findAll();

    ProductoProveedorDTO findById(Long id);

    ProductoDTO saveProducto(ProductoDTO productoDTO);

    ProductoDTO updateProducto(Long id, ProductoDTO productoDTO);

    String deletProducto(Long id);

    List<ProductoDTO> findByName(String name);
}
