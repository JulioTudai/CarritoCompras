package com.CarritoCompras.Presentation.Controller;

import com.CarritoCompras.Mapper.IMapperProducto;
import com.CarritoCompras.Model.DTO.ProductoDTO;
import com.CarritoCompras.Service.Interface.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")

public class ProductoController {

    @Autowired
    IProductoService productoService;
    @Autowired
    IMapperProducto productoMapper;

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO productoDTO) {
        var productoEntity = productoMapper.productoDTOToProductoEntity(productoDTO);
        var productoCreado = productoService.crearProducto(productoEntity);
        return new ResponseEntity<>(productoMapper.productoEntityToProductoDTO(productoCreado), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Long id) {
        var productoEntity = productoService.obtenerProductoPorId(id);
        return productoEntity != null
                ? new ResponseEntity<>(productoMapper.productoEntityToProductoDTO(productoEntity), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarProductos() {
        var productos = productoService.listarProductos();
        var productosDTO = productos.stream()
                .map(productoMapper::productoEntityToProductoDTO)
                .toList();
        return new ResponseEntity<>(productosDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(
            @PathVariable Long id,
            @RequestBody ProductoDTO productoDTO) {
        var productoActualizado = productoService.actualizarProducto(id, productoMapper.productoDTOToProductoEntity(productoDTO));
        return productoActualizado != null
                ? new ResponseEntity<>(productoMapper.productoEntityToProductoDTO(productoActualizado), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
        if (!productoService.eliminarProducto(id)) {
            throw new ResourceNotFoundException("No se pudo eliminar. Producto no encontrado con ID: " + id);
        }
        return new ResponseEntity<>("Producto eliminado exitosamente", HttpStatus.NO_CONTENT);
    }

}
