package com.CarritoCompras.Persistence.DAO.Interface;


import com.CarritoCompras.Model.Entity.ClienteEntity;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IClienteDao {

    List<ClienteEntity> findAll();

    Optional<ClienteEntity> findById(Long id);

    void saveCliente(ClienteEntity clienteEntity);

}
