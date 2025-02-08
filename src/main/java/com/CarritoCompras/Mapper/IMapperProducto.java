package com.CarritoCompras.Mapper;

import com.CarritoCompras.Model.DTO.ProductoDTO;
import com.CarritoCompras.Model.Entity.ProductoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IMapperProducto {
    IMapperProducto INSTANCE = Mappers.getMapper(IMapperProducto.class);

    ProductoDTO productoEntityToProductoDTO(ProductoEntity productoEntity);

    ProductoEntity productoDTOToProductoEntity(ProductoDTO productoDTO);
}
