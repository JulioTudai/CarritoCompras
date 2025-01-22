package com.CarritoCompras.Repository;

import com.CarritoCompras.Model.Entity.CarritoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarritoRepository extends JpaRepository<CarritoEntity, Long> {

    CarritoEntity findByClienteId(Long clienteId);
}
