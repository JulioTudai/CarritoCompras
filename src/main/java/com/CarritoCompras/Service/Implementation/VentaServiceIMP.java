package com.CarritoCompras.Service.Implementation;

import com.CarritoCompras.Mapper.IVentaMapper;
import com.CarritoCompras.Model.DTO.VentaDTO;
import com.CarritoCompras.Model.Entity.VentaEntity;
import com.CarritoCompras.Repository.ClienteRepository;
import com.CarritoCompras.Repository.IVentaRepository;
import com.CarritoCompras.Repository.ProductoRepository;
import com.CarritoCompras.Service.Interface.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class VentaServiceIMP implements IVentaService {
    @Autowired
    private IVentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private IVentaMapper ventaMapper;

    @Override
    public VentaDTO saveVenta(VentaDTO ventaDTO) {
        // Convertir VentaDTO a VentaEntity
        VentaEntity ventaEntity = ventaMapper.toEntity(ventaDTO);

        // Validar cliente
        clienteRepository.findById(ventaDTO.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + ventaDTO.getClienteId()));

        // Validar productos y stock
        ventaDTO.getCantidadPorProducto().forEach((productoId, cantidad) -> {
            productoRepository.findById(productoId).ifPresentOrElse(producto -> {
                if (producto.getStock() < cantidad) {
                    throw new IllegalArgumentException("Stock insuficiente para el producto: " + producto.getName());
                }
                producto.setStock(producto.getStock() - cantidad);
                productoRepository.save(producto); // Actualizar el stock en la base de datos
            }, () -> {
                throw new IllegalArgumentException("Producto no encontrado con ID: " + productoId);
            });
        });

        // Calcular el total de la venta
        Double total = ventaDTO.getCantidadPorProducto().entrySet().stream()
                .mapToDouble(entry -> {
                    Long productoId = entry.getKey();
                    Integer cantidad = entry.getValue();
                    return productoRepository.findById(productoId)
                            .map(producto -> producto.getPrice() * cantidad)
                            .orElse(0.0);
                })
                .sum();

        ventaEntity.setTotal(total - ventaDTO.getDescuento());

        // Guardar la venta
        VentaEntity savedVenta = ventaRepository.save(ventaEntity);

        // Devolver VentaDTO
        return ventaMapper.toDTO(savedVenta);
    }

    @Override
    public VentaDTO findVentaById(Long id) {
        VentaEntity ventaEntity = ventaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada con ID: " + id));
        return ventaMapper.toDTO(ventaEntity);
    }

    @Override
    public List<VentaDTO> findAllVentas() {
        return ventaRepository.findAll().stream()
                .map(ventaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VentaDTO> findVentasByClienteId(Long clienteId) {
        return ventaRepository.findByClienteId(clienteId).stream()
                .map(ventaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VentaDTO> findVentasByFechaBetween(LocalDateTime inicio, LocalDateTime fin) {
        return ventaRepository.findByFechaHoraBetween(inicio, fin).stream()
                .map(ventaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteVenta(Long id) {
        VentaEntity ventaEntity = ventaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada con ID: " + id));
        ventaRepository.delete(ventaEntity);
    }
}
