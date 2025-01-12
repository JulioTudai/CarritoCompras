package com.CarritoCompras.Service.Interface;


import com.CarritoCompras.Model.DTO.ProveedorDTO;

import java.util.List;

public interface IProveedorService {

    List<ProveedorDTO> findAll();

    ProveedorDTO findById(Long id);

    ProveedorDTO saveProveedor(ProveedorDTO proveedorDTO);

    ProveedorDTO updateProveedor(Long id, ProveedorDTO proveedorDTO);

    String deletProveedor(Long id);

}
