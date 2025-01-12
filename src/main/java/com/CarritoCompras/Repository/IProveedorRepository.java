package com.CarritoCompras.Repository;

import com.CarritoCompras.Model.Entity.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProveedorRepository extends JpaRepository<ProveedorEntity,Long> {

    Optional<ProveedorEntity> findByName(String name);
}
