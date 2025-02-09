package com.CarritoCompras.Service.Implementation;

import com.CarritoCompras.Model.Entity.MercadoEntity;
import com.CarritoCompras.Repository.IMercadoRepository;
import com.CarritoCompras.Service.Interface.IMercadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MercadoServiceIMP implements IMercadoService {

    @Autowired
    IMercadoRepository mercadoRepository;

    @Override
    public MercadoEntity crearMercado(MercadoEntity mercadoEntity) {
        return mercadoRepository.save(mercadoEntity);
    }

    @Override
    public Optional<MercadoEntity> obtenerMercadoPorId(Long id) {
        return mercadoRepository.findById(id);
    }

    @Override
    public List<MercadoEntity> listarMercados() {
        return mercadoRepository.findAll();
    }

    @Override
    public Optional<MercadoEntity> actualizarMercado(Long id, MercadoEntity mercadoEntity) {
        return mercadoRepository.findById(id).map(existing -> {
            existing.setNombre(mercadoEntity.getNombre());
            existing.setVentas(mercadoEntity.getVentas());
            existing.setClientesId(mercadoEntity.getClientesId());
            existing.setInventario(mercadoEntity.getInventario());
            return mercadoRepository.save(existing);
        });
    }

    @Override
    public boolean eliminarMercado(Long id) {
        return mercadoRepository.findById(id).map(existing -> {
            mercadoRepository.delete(existing);
            return true;
        }).orElse(false);
    }
}
