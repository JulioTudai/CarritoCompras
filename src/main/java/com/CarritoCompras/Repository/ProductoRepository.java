package com.CarritoCompras.Repository;

import com.CarritoCompras.Model.Entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long>{

    @Query("SELECT p FROM ProductoEntity p JOIN FETCH p.proveedor WHERE p.id = :id")
    Optional<ProductoEntity> findByIdWithProveedor(@Param("id") Long id);

    List<ProductoEntity> findByNameContainingIgnoreCase(String name);

    public static List<ProductoEntity> mapIdsToProductos(List<Long> ids, ProductoRepository productoRepository) {
        return ids.stream()
                .map(id -> productoRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + id)))
                .collect(Collectors.toList());
    }
}
