package com.CarritoCompras.Mapper;

import com.CarritoCompras.Model.DTO.CarritoDTO;
import com.CarritoCompras.Model.Entity.CarritoEntity;
import com.CarritoCompras.Model.Entity.ProductoEntity;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

public interface IMapperCarrito {

    IMapperCarrito INSTANCE = Mappers.getMapper(IMapperCarrito.class);

    CarritoDTO toDTO(CarritoEntity carritoEntity);

    CarritoEntity toEntity(CarritoDTO carritoDTO);

    default List<ProductoEntity> mapProductos(List<Long> productoIds) {
        return productoIds.stream()
                .map(id -> ProductoEntity.builder().id(id).build())
                .collect(Collectors.toList());
    }
}
