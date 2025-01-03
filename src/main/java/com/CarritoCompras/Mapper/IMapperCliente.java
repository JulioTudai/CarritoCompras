package com.CarritoCompras.Mapper;

import com.CarritoCompras.Model.DTO.ClienteDTO;
import com.CarritoCompras.Model.Entity.ClienteEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IMapperCliente {

    IMapperCliente INSTANCE = Mappers.getMapper(IMapperCliente.class);

    @Mapping(target = "id", ignore = true)
    ClienteEntity toEntity(ClienteDTO dto);

    ClienteDTO toDTO(ClienteEntity entity);
}
