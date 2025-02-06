package com.CarritoCompras.Mapper;

import com.CarritoCompras.Model.DTO.CarritoDTO;
import com.CarritoCompras.Model.Entity.CarritoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IMapperCarrito {

    // Instancia estática para acceder al mapper generado
    IMapperCarrito INSTANCE = Mappers.getMapper(IMapperCarrito.class);

    //de CarritoEntity a CarritoDTO
    @Mapping(source = "productosAgregados", target = "productosAgregados")
    CarritoDTO carritoEntityToCarritoDTO(CarritoEntity carritoEntity);

    //de CarritoDTO a CarritoEntity
    @Mapping(source = "productosAgregados", target = "productosAgregados")
    CarritoEntity carritoDTOToCarritoEntity(CarritoDTO carritoDTO);

    //para mapear una lista de CarritoEntity a una lista de CarritoDTO
    List<CarritoDTO> carritoEntitiesToCarritoDTOs(List<CarritoEntity> carritoEntities);

    //para mapear una lista de CarritoDTO a una lista de CarritoEntity
    List<CarritoEntity> carritoDTOsToCarritoEntities(List<CarritoDTO> carritoDTOs);
}
