package com.CarritoCompras.Repository;

import com.CarritoCompras.Model.Entity.CarritoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICarritoRepository extends JpaRepository<CarritoEntity, Long> {

    Optional<CarritoEntity> findByClienteId(Long clienteId);
}
