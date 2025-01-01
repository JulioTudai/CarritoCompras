package com.CarritoCompras.Service.Implementation;

import com.CarritoCompras.Model.DTO.ClienteDTO;
import com.CarritoCompras.Model.Entity.ClienteEntity;
import com.CarritoCompras.Persistence.DAO.Interface.IClienteDao;
import com.CarritoCompras.Service.Interface.IClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceIMP implements IClienteService {

    @Autowired
    private IClienteDao clienteDAO;

    @Override
    public List<ClienteDTO> finAll() {


        ModelMapper modelMapper = new ModelMapper();
        return this.clienteDAO.findAll().stream()
                .map(ClienteEntity -> modelMapper.map(ClienteEntity,ClienteDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO findById(Long id) {

        Optional<ClienteEntity> clienteBuscado = clienteDAO.findById(id);

        if (clienteBuscado.isPresent()){
            ModelMapper modelMapper = new ModelMapper();
            ClienteEntity clienteEntity = clienteBuscado.get();

            return modelMapper.map(clienteEntity,ClienteDTO.class);
        }

        return new ClienteDTO();
    }

    @Override
    public ClienteDTO saveCliente(ClienteDTO clienteDTO) {

        /*Optional<ClienteEntity> clienteBuscado = clienteDAO.findById(clienteDTO.getId());

        if (clienteBuscado.isPresent()){
            throw new IllegalArgumentException("El usuario con el correo " + clienteDTO.getName() + " ya existe.");
        }

         */
        try{
            ModelMapper modelMapper = new ModelMapper();
            ClienteEntity userEntity = modelMapper.map(clienteDTO, ClienteEntity.class);
            this.clienteDAO.saveCliente(userEntity);

            return clienteDTO;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error al guardar el usuario");
        }
    }
}
