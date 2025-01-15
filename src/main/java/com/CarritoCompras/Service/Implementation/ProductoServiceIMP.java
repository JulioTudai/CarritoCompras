package com.CarritoCompras.Service.Implementation;

import com.CarritoCompras.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceIMP {

    @Autowired
    private ProductoRepository productoRepository;



}
