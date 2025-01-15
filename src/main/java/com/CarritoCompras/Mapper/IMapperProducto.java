package com.CarritoCompras.Mapper;

import com.CarritoCompras.Model.DTO.ProductoDTO;
import com.CarritoCompras.Model.Entity.ProductoEntity;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface IMapperProducto {

    IMapperProducto INSTANCE = Mappers.getMapper(IMapperProducto.class);

    @Mapping(target = "id", ignore = true)
    ProductoEntity toEntity(ProductoDTO dto);

    ProductoDTO toDTO(ProductoEntity entity);
}
