package com.CarritoCompras.Mapper.Helpers;


import com.CarritoCompras.Model.Entity.ProductoEntity;
import org.mapstruct.Named;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VentaMapperHelper {

    @Named("mapProductosToIds")
    public static List<Long> mapProductosToIds(List<ProductoEntity> productos) {
        return productos.stream()
                .map(ProductoEntity::getId)
                .collect(Collectors.toList());
    }

    @Named("mapIdsToProductos")
    public static List<ProductoEntity> mapIdsToProductos(List<Long> ids) {
        // Aquí debes implementar cómo obtienes las entidades ProductoEntity a partir de los IDs
        // Por ejemplo, usando un repositorio o un servicio
        return ids.stream()
                .map(id -> ProductoEntity.builder().id(id).build()) // Mock de ProductoEntity
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

