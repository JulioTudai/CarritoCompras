package com.CarritoCompras.Persistence.DAO.Implementation;

import com.CarritoCompras.Model.Entity.ClienteEntity;
import com.CarritoCompras.Persistence.DAO.Interface.IClienteDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteDaoIMP implements IClienteDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<ClienteEntity> findAll() {
        return this.em.createQuery("SELECT u FROM ClienteEntity u").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClienteEntity> findById(Long id) {
        return Optional.ofNullable(this.em.find(ClienteEntity.class,id));
    }

    @Override
    @Transactional
    public void saveCliente(ClienteEntity clienteEntity) {
        this.em.persist(clienteEntity);
        this.em.flush();

    }
}
