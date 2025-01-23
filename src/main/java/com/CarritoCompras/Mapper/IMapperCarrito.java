package com.CarritoCompras.Mapper;

import com.CarritoCompras.Model.DTO.CarritoDTO;
import com.CarritoCompras.Model.Entity.CarritoEntity;
import com.CarritoCompras.Model.Entity.ProductoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface IMapperCarrito {

    IMapperCarrito INSTANCE = Mappers.getMapper(IMapperCarrito.class);

    // Mapeo de Entity a DTO
    @Mapping(target = "productos", source = "productos", qualifiedByName = "mapProductosToIds")
    CarritoDTO toDTO(CarritoEntity carritoEntity);

    // Mapeo de DTO a Entity
    @Mapping(target = "productos", source = "productos", qualifiedByName = "mapIdsToProductos")
    CarritoEntity toEntity(CarritoDTO carritoDTO);

    //  para convertir lista de ProductoEntity a lista de IDs
    @Named("mapProductosToIds")
    default List<Long> mapProductosToIds(List<ProductoEntity> productos) {
        return productos.stream()
                .map(ProductoEntity::getId)
                .collect(Collectors.toList());
    }

    // para convertir lista de IDs a lista de ProductoEntity
    @Named("mapIdsToProductos")
    default List<ProductoEntity> mapIdsToProductos(List<Long> productoIds) {
        return productoIds.stream()
                .map(id -> ProductoEntity.builder().id(id).build())
                .collect(Collectors.toList());
    }
}
