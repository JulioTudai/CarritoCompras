package com.CarritoCompras.Repository;

import com.CarritoCompras.Model.Entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long>{
}
