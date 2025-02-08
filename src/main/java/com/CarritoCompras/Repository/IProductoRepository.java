package com.CarritoCompras.Repository;

import com.CarritoCompras.Model.Entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductoRepository extends JpaRepository<ProductoEntity, Long> {

    Optional<ProductoEntity> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
