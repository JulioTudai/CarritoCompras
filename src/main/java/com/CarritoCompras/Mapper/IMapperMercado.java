package com.CarritoCompras.Mapper;

import com.CarritoCompras.Model.DTO.MercadoDTO;
import com.CarritoCompras.Model.Entity.MercadoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IMapperMercado {

    IMapperMercado INSTANCE = Mappers.getMapper(IMapperMercado.class);

    MercadoDTO mercadoEntityToMercadoDTO(MercadoEntity mercadoEntity);
    MercadoEntity mercadoDTOToMercadoEntity(MercadoDTO mercadoDTO);
}

