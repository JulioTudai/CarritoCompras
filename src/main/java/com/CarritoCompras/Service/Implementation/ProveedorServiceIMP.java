package com.CarritoCompras.Service.Implementation;

import com.CarritoCompras.Model.Entity.ProveedorEntity;
import com.CarritoCompras.Repository.IProveedorRepository;
import com.CarritoCompras.Service.Interface.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceIMP implements IProveedorService {

    @Autowired
    IProveedorRepository proveedorRepository;

    @Override
    public ProveedorEntity crearProveedor(ProveedorEntity proveedorEntity) {
        return proveedorRepository.save(proveedorEntity);
    }

    @Override
    public Optional<ProveedorEntity> obtenerProveedorPorId(Long id) {
        return proveedorRepository.findById(id);
    }

    @Override
    public List<ProveedorEntity> listarProveedores() {
        return proveedorRepository.findAll();
    }

    @Override
    public Optional<ProveedorEntity> actualizarProveedor(Long id, ProveedorEntity proveedorEntity) {
        return proveedorRepository.findById(id).map(existing -> {
            existing.setNombre(proveedorEntity.getNombre());
            existing.setEmail(proveedorEntity.getEmail());
            existing.setCelular(proveedorEntity.getCelular());
            existing.setCompania(proveedorEntity.getCompania());
            existing.setProductosIds(proveedorEntity.getProductosIds());
            return proveedorRepository.save(existing);
        });
    }

    @Override
    public boolean eliminarProveedor(Long id) {
        return proveedorRepository.findById(id).map(existing -> {
            proveedorRepository.delete(existing);
            return true;
        }).orElse(false);
    }












}
