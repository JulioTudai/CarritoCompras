package com.CarritoCompras.Repository;

import com.CarritoCompras.Model.Entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long>{

    @Query("SELECT p FROM ProductoEntity p JOIN FETCH p.proveedor WHERE p.id = :id")
    Optional<ProductoEntity> findByIdWithProveedor(@Param("id") Long id);
}
