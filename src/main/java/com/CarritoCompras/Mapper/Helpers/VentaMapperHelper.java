package com.CarritoCompras.Mapper.Helpers;


import com.CarritoCompras.Model.Entity.ProductoEntity;
import com.CarritoCompras.Repository.ProductoRepository;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VentaMapperHelper {

    @Autowired
    private ProductoRepository productoRepository;

    @Named("mapProductosToIds")
    public static List<Long> mapProductosToIds(List<ProductoEntity> productos) {
        return productos.stream()
                .map(ProductoEntity::getId)
                .collect(Collectors.toList());
    }

    @Named("mapIdsToProductos")
    public List<ProductoEntity> mapIdsToProductos(List<Long> ids) {
        return ids.stream()
                .map(id -> productoRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + id)))
                .collect(Collectors.toList());
    }

    @Named("mapCantidadPorProducto")
    public static Map<Long, Integer> mapCantidadPorProducto(Map<ProductoEntity, Integer> cantidadPorProducto) {
        return cantidadPorProducto.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getId(),
                        Map.Entry::getValue
                ));
    }

    @Named("mapCantidadPorProductoDTO")
    public static Map<ProductoEntity, Integer> mapCantidadPorProductoDTO(Map<Long, Integer> cantidadPorProductoDTO) {
        return cantidadPorProductoDTO.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> ProductoEntity.builder().id(entry.getKey()).build(),
                        Map.Entry::getValue
                ));
    }

}

