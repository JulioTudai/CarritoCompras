package com.CarritoCompras.Mapper;

import com.CarritoCompras.Model.DTO.ProductoDTO;
import com.CarritoCompras.Model.DTO.ProductoProveedorDTO;
import com.CarritoCompras.Model.Entity.ProductoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IMapperProducto {

    IMapperProducto INSTANCE = Mappers.getMapper(IMapperProducto.class);


    ProductoBasicDTO toBasicDTO(ProductoEntity producto);

    ProductoDetailDTO toDetailDTO(ProductoEntity producto);

    ProductoEntity toEntity(ProductoDetailDTO dto);
}
