package com.CarritoCompras.Service.Interface;


import com.CarritoCompras.Model.Entity.ProveedorEntity;

import java.util.List;
import java.util.Optional;

public interface IProveedorService {

    ProveedorEntity crearProveedor(ProveedorEntity proveedorEntity);

    Optional<ProveedorEntity> obtenerProveedorPorId(Long id);

    List<ProveedorEntity> listarProveedores();

    Optional<ProveedorEntity> actualizarProveedor(Long id, ProveedorEntity proveedorEntity);

    boolean eliminarProveedor(Long id);

}
