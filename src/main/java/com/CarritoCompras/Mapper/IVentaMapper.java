package com.CarritoCompras.Mapper;


import com.CarritoCompras.Mapper.Helpers.VentaMapperHelper;
import com.CarritoCompras.Model.DTO.ProductoCantidadDTO;
import com.CarritoCompras.Model.DTO.VentaDTO;
import com.CarritoCompras.Model.Entity.ProductoCantidad;
import com.CarritoCompras.Model.Entity.VentaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = VentaMapperHelper.class)
public interface IVentaMapper {

    IVentaMapper INSTANCE = Mappers.getMapper(IVentaMapper.class);

    // Mapea de VentaEntity a VentaDTO
    @Mapping(target = "productosVendidos", source = "productosVendidos")
    VentaDTO toDTO(VentaEntity ventaEntity);

    // Mapea de VentaDTO a VentaEntity
    @Mapping(target = "productosVendidos", source = "productosVendidos")
    VentaEntity toEntity(VentaDTO ventaDTO);

    // Mapeos para ProductoCantidad y ProductoCantidadDTO
    ProductoCantidadDTO toProductoCantidadDTO(ProductoCantidad productoCantidad);

    ProductoCantidad toProductoCantidad(ProductoCantidadDTO productoCantidadDTO);

    // Métodos auxiliares para listas de productos vendidos
    List<ProductoCantidadDTO> toProductoCantidadDTOList(List<ProductoCantidad> productosVendidos);

    List<ProductoCantidad> toProductoCantidadList(List<ProductoCantidadDTO> productosVendidosDTO);
}
