package com.CarritoCompras.Mapper;

import com.CarritoCompras.Model.DTO.MercadoDTO;
import com.CarritoCompras.Model.Entity.MercadoEntity;
import com.CarritoCompras.Model.Entity.ProductoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IMapperMercado {

    IMapperMercado INSTANCE = Mappers.getMapper(IMapperMercado.class);

    @Mapping(target = "productos", expression = "java(mapProductosToDTOs(mercadoEntity.getProductos()))")
    MercadoDTO toDTO(MercadoEntity mercadoEntity);

    @Mapping(target = "productos", expression = "java(mapDTOsToProductos(mercadoDTO.getProductos()))")
    MercadoEntity toEntity(MercadoDTO mercadoDTO);

    default Map<Long, Integer> mapProductosToDTOs(Map<ProductoEntity, Integer> productos) {
        return productos.entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().getId(), Map.Entry::getValue));
    }

    default Map<ProductoEntity, Integer> mapDTOsToProductos(Map<Long, Integer> productos) {
        return productos.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> ProductoEntity.builder().id(entry.getKey()).build(), // Considera que ProductoEntity tenga un constructor que acepte solo el ID
                        Map.Entry::getValue
                ));
    }
}

