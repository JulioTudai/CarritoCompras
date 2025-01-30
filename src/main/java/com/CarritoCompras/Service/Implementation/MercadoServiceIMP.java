package com.CarritoCompras.Service.Implementation;

import com.CarritoCompras.Mapper.IMapperMercado;
import com.CarritoCompras.Mapper.IMapperProducto;
import com.CarritoCompras.Model.DTO.MercadoDTO;
import com.CarritoCompras.Model.DTO.ProductoDTO;
import com.CarritoCompras.Model.DTO.VentaDTO;
import com.CarritoCompras.Model.Entity.MercadoEntity;
import com.CarritoCompras.Model.Entity.VentaEntity;
import com.CarritoCompras.Repository.MercadoRepository;
import com.CarritoCompras.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MercadoServiceIMP {
    @Autowired
    private MercadoRepository mercadoRepository;

    @Autowired
    ProductoRepository productoRepository;

    public MercadoDTO findMercadoById(Long id){
        MercadoEntity mercado = mercadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mercado no encontrado"));

        return IMapperMercado.INSTANCE.toDTO(mercado);

    }

    // Registrar compra
    public VentaDTO registrarCompra(Long mercadoId, VentaDTO ventaDTO) {
        // Implementación pendiente: verificar stock, actualizar inventario y registrar la venta
        throw new UnsupportedOperationException("Método no implementado");
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
