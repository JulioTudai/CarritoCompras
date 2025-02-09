package com.CarritoCompras.Presentation.Controller;

import com.CarritoCompras.Mapper.IMapperProveedor;
import com.CarritoCompras.Model.DTO.ProveedorDTO;
import com.CarritoCompras.Service.Interface.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private IProveedorService proveedorService;

    @Autowired
    private IMapperProveedor proveedorMapper;

    @PostMapping
    public ResponseEntity<ProveedorDTO> crearProveedor(@RequestBody ProveedorDTO proveedorDTO) {
        var proveedorEntity = proveedorMapper.toEntity(proveedorDTO);
        var proveedorCreado = proveedorService.crearProveedor(proveedorEntity);
        return new ResponseEntity<>(proveedorMapper.toDTO(proveedorCreado), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> obtenerProveedorPorId(@PathVariable Long id) {
        var proveedorOpt = proveedorService.obtenerProveedorPorId(id);
        if (proveedorOpt.isPresent()) {
            return new ResponseEntity<>(proveedorMapper.toDTO(proveedorOpt.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProveedorDTO>> listarProveedores() {
        var proveedores = proveedorService.listarProveedores();
        var proveedoresDTO = proveedores.stream()
                .map(proveedorMapper::toDTO)
                .toList();
        return new ResponseEntity<>(proveedoresDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> actualizarProveedor(@PathVariable Long id, @RequestBody ProveedorDTO proveedorDTO) {
        var proveedorEntity = proveedorMapper.toEntity(proveedorDTO);
        var proveedorActualizado = proveedorService.actualizarProveedor(id, proveedorEntity);
        if (proveedorActualizado.isPresent()) {
            return new ResponseEntity<>(proveedorMapper.toDTO(proveedorActualizado.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProveedor(@PathVariable Long id) {
        if (!proveedorService.eliminarProveedor(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar. Proveedor no encontrado con ID: " + id);
        }
        // Se puede retornar NO_CONTENT (204) sin cuerpo o cambiar a OK (200) si se requiere mensaje.
        return new ResponseEntity<>("Proveedor eliminado exitosamente", HttpStatus.NO_CONTENT);
    }

}
