package com.CarritoCompras.Service.Implementation;

import com.CarritoCompras.Mapper.IMapperMercado;
import com.CarritoCompras.Mapper.IVentaMapper;
import com.CarritoCompras.Model.DTO.MercadoDTO;
import com.CarritoCompras.Model.DTO.ProductoDTO;
import com.CarritoCompras.Model.DTO.VentaDTO;
import com.CarritoCompras.Model.Entity.MercadoEntity;
import com.CarritoCompras.Model.Entity.ProductoEntity;
import com.CarritoCompras.Model.Entity.VentaEntity;
import com.CarritoCompras.Repository.ClienteRepository;
import com.CarritoCompras.Repository.IVentaRepository;
import com.CarritoCompras.Repository.MercadoRepository;
import com.CarritoCompras.Repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MercadoServiceIMP {
    @Autowired
    private MercadoRepository mercadoRepository;

    @Autowired
    IProductoRepository productoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    IVentaRepository ventaRepository;

    @Autowired
    private IVentaMapper ventaMapper;

    public MercadoDTO findMercadoById(Long id){
        MercadoEntity mercado = mercadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mercado no encontrado"));

        return IMapperMercado.INSTANCE.toDTO(mercado);

    }

    // Registrar compra
    public void registrarVenta(Long mercadoId, VentaDTO ventaDTO) {
        // Validar mercado existente
        MercadoEntity mercado = mercadoRepository.findById(mercadoId)
                .orElseThrow(() -> new IllegalArgumentException("Mercado no encontrado"));

        // Convertir VentaDTO a VentaEntity
        VentaEntity ventaEntity = ventaMapper.toEntity(ventaDTO);

        // Validar cliente
        clienteRepository.findById(ventaDTO.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + ventaDTO.getClienteId()));

        // Validar productos y stock
        ventaDTO.getCantidadPorProducto().forEach((productoId, cantidad) -> {
            ProductoEntity producto = productoRepository.findById(productoId)
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + productoId));
            if (producto.getStock() < cantidad) {
                throw new IllegalArgumentException("Stock insuficiente para el producto: " + producto.getName());
            }
            producto.setStock(producto.getStock() - cantidad);
            productoRepository.save(producto);
        });

        // Asignar el mercado a la venta y calcular total
        ventaEntity.setFechaHora(LocalDateTime.now());
        ventaEntity.calcularTotal();

        // Guardar la venta
        ventaRepository.save(ventaEntity);
    }

    // Consultar stock
    public List<ProductoDTO> consultarStock(Long mercadoId) {
        MercadoEntity mercado = mercadoRepository.findById(mercadoId)
                .orElseThrow(() -> new IllegalArgumentException("Mercado no encontrado"));
        return mercado.getProductos().keySet().stream()
                .map(productoEntity -> IMapperProducto.INSTANCE.toDTO(productoEntity))
                .collect(Collectors.toList());
    }

    // Agregar producto al inventario
    public void agregarProducto(Long mercadoId, ProductoDTO productoDTO) {
        MercadoEntity mercado = mercadoRepository.findById(mercadoId)
                .orElseThrow(() -> new IllegalArgumentException("Mercado no encontrado"));
        // Lógica de agregar producto en inventario pendiente
    }

    // Consultar ventas
    public List<VentaEntity> consultarVentas(Long mercadoId) {
        MercadoEntity mercado = mercadoRepository.findById(mercadoId)
                .orElseThrow(() -> new IllegalArgumentException("Mercado no encontrado"));
        return mercado.getVentas();
    }

    // Generar reporte de ingresos
    public Double generarReporteIngresos(Long mercadoId) {
        MercadoEntity mercado = mercadoRepository.findById(mercadoId)
                .orElseThrow(() -> new IllegalArgumentException("Mercado no encontrado"));
        return mercado.getIngresosTotales();
    }
}
