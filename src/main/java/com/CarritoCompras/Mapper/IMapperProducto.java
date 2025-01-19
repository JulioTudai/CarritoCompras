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


    @Mapping(target = "proveedor", ignore = true)
    ProductoEntity toEntity(ProductoDTO dto);

    // Para findAll y save ID del proveedor
    @Mapping(target = "proveedorId", source = "proveedor.id")
    ProductoDTO toDTO(ProductoEntity entity);

    // Para findById proveedor completo
    @Mapping(target = "proveedor", source = "proveedor")
    ProductoProveedorDTO toProductoProvedorDTO(ProductoEntity entity);
}
