package com.CarritoCompras.Repository;

import com.CarritoCompras.Model.Entity.MercadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMercadoRepository extends JpaRepository<MercadoEntity, Long> {
}
