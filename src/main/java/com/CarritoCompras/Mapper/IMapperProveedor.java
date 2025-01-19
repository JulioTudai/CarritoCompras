package com.CarritoCompras.Mapper;

import com.CarritoCompras.Model.DTO.ProveedorDTO;
import com.CarritoCompras.Model.Entity.ProveedorEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {IMapperProducto.class})
public interface IMapperProveedor {

    IMapperProveedor INSTANCE = Mappers.getMapper(IMapperProveedor.class);

    @Mapping(target = "id", ignore = true)
    ProveedorEntity toEntity(ProveedorDTO dto);


    ProveedorDTO toDTO(ProveedorEntity entity);

}
