package com.CarritoCompras.Service.Interface;

import com.CarritoCompras.Model.Entity.MercadoEntity;

import java.util.List;
import java.util.Optional;

public interface IMercadoService {

    MercadoEntity crearMercado(MercadoEntity mercadoEntity);
    Optional<MercadoEntity> obtenerMercadoPorId(Long id);
    List<MercadoEntity> listarMercados();
    Optional<MercadoEntity> actualizarMercado(Long id, MercadoEntity mercadoEntity);
    boolean eliminarMercado(Long id);
}
