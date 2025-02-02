package com.CarritoCompras.Mapper;


import com.CarritoCompras.Mapper.Helpers.VentaMapperHelper;
import com.CarritoCompras.Model.DTO.VentaDTO;
import com.CarritoCompras.Model.Entity.VentaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = VentaMapperHelper.class)
public interface IVentaMapper {

    IVentaMapper INSTANCE = Mappers.getMapper(IVentaMapper.class);

    @Mapping(target = "clienteId", source = "cliente.id")
    @Mapping(target = "productosVendidosIds", source = "productosVendidos", qualifiedByName = "mapProductosToIds")
    @Mapping(target = "cantidadPorProducto", source = "cantidadPorProducto", qualifiedByName = "mapCantidadPorProducto")
    VentaDTO toDTO(VentaEntity ventaEntity);

    @Mapping(target = "cliente.id", source = "clienteId")
    @Mapping(target = "productosVendidos", source = "productosVendidosIds", qualifiedByName = "mapIdsToProductos")
    @Mapping(target = "cantidadPorProducto", source = "cantidadPorProducto", qualifiedByName = "mapCantidadPorProductoDTO")
    VentaEntity toEntity(VentaDTO ventaDTO);
}
