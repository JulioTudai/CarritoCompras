package com.CarritoCompras.Mapper;

import com.CarritoCompras.Model.DTO.ClienteDTO;
import com.CarritoCompras.Model.Entity.ClienteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMapperCliente {

    ClienteEntity toEntity(ClienteDTO dto);

    ClienteDTO toDTO(ClienteEntity entity);
}
