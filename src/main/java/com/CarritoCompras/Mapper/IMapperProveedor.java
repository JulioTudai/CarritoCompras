package com.CarritoCompras.Mapper;

import com.CarritoCompras.Model.DTO.ProveedorDTO;
import com.CarritoCompras.Model.Entity.ProveedorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IMapperProveedor {

    IMapperProveedor INSTANCE = Mappers.getMapper(IMapperProveedor.class);

    ProveedorDTO toDTO(ProveedorEntity proveedorEntity);
    ProveedorEntity toEntity(ProveedorDTO proveedorDTO);

}
