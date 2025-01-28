package com.CarritoCompras.Mapper;

import com.CarritoCompras.Mapper.Helpers.ProductoMapperHelper;
import com.CarritoCompras.Model.DTO.MercadoDTO;
import com.CarritoCompras.Model.Entity.MercadoEntity;
import com.CarritoCompras.Model.Entity.ProductoEntity;
import com.CarritoCompras.Repository.ProductoRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface IMapperMercado {
    IMapperMercado INSTANCE = Mappers.getMapper(IMapperMercado.class);

    @Mapping(target = "productos", expression = "java(mapProductosToIds(mercadoEntity.getProductos()))")
    MercadoDTO toDTO(MercadoEntity mercadoEntity);

    @Mapping(target = "productos", expression = "java(mapIdsToProductos(mercadoDTO.getProductos()))")
    MercadoEntity toEntity(MercadoDTO mercadoDTO);

    default Map<Long, Integer> mapProductosToIds(Map<ProductoEntity, Integer> productos) {
        return productos.entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().getId(), Map.Entry::getValue));
    }

    default Map<ProductoEntity, Integer> mapIdsToProductos(Map<Long, Integer> productos) {
        {
            ProductoRepository productoRepository;

            return productos.entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            entry -> productoRepository.findById(entry.getKey())
                                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + entry.getKey())),
                            Map.Entry::getValue
                    ));
        }
        throw new UnsupportedOperationException("La conversión de IDs a entidades no está implementada.");
    }
}
