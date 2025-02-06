package com.CarritoCompras.Service.Implementation;

import com.CarritoCompras.Mapper.IVentaMapper;
import com.CarritoCompras.Model.DTO.VentaDTO;
import com.CarritoCompras.Model.Entity.VentaEntity;
import com.CarritoCompras.Repository.IVentaRepository;
import com.CarritoCompras.Service.Interface.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class VentaServiceIMP implements IVentaService {

    @Autowired
    IVentaRepository ventaRepository;
    @Autowired
    IVentaMapper ventaMapper;


    @Override
    public List<VentaDTO> obtenerTodas() {
        List<VentaEntity> ventas = ventaRepository.findAll();
        return ventas.stream()
                .map(ventaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VentaDTO obtenerPorId(Long id) {
        VentaEntity ventaEntity = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));
        return ventaMapper.toDTO(ventaEntity);
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDTO) {
        VentaEntity ventaEntity = ventaMapper.toEntity(ventaDTO);
        VentaEntity ventaGuardada = ventaRepository.save(ventaEntity);
        return ventaMapper.toDTO(ventaGuardada);
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO) {
        Optional<VentaEntity> ventaExistente = ventaRepository.findById(id);
        if (ventaExistente.isEmpty()) {
            throw new RuntimeException("No existe una venta con ID: " + id);
        }

        VentaEntity ventaActualizada = ventaMapper.toEntity(ventaDTO);
        ventaActualizada.setId(id); // Mantener el ID de la venta existente
        VentaEntity ventaGuardada = ventaRepository.save(ventaActualizada);
        return ventaMapper.toDTO(ventaGuardada);
    }

    @Override
    public void eliminarVenta(Long id) {
        if (!ventaRepository.existsById(id)) {
            throw new RuntimeException("No existe una venta con ID: " + id);
        }
        ventaRepository.deleteById(id);
    }
}
