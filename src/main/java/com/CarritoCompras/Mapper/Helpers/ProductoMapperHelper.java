package com.CarritoCompras.Mapper.Helpers;

import com.CarritoCompras.Model.Entity.ProductoEntity;
import com.CarritoCompras.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.stream.Collectors;

public class ProductoMapperHelper {
    @Autowired
    private ProductoRepository productoRepository;

    public Map<ProductoEntity, Integer> mapIdsToProductos(Map<Long, Integer> productos) {
        return productos.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> productoRepository.findById(entry.getKey())
                                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + entry.getKey())),
                        Map.Entry::getValue
                ));
    }
}
