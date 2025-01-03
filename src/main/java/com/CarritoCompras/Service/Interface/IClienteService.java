package com.CarritoCompras.Service.Interface;

import com.CarritoCompras.Model.DTO.ClienteDTO;

import java.util.List;
;

public interface IClienteService {

    List<ClienteDTO> findAll();

    ClienteDTO findById(Long id);

    ClienteDTO saveCliente(ClienteDTO clienteDTO);

    ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO);

    String deletCliente(Long id);
}
