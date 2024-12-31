package com.CarritoCompras.Service.Interface;

import com.CarritoCompras.Model.DTO.ClienteDTO;

import java.util.List;
;

public interface IClienteService {

    List<ClienteDTO> finAll();

    ClienteDTO findById(Long id);

    ClienteDTO saveCliente(ClienteDTO clienteDTO);
}
