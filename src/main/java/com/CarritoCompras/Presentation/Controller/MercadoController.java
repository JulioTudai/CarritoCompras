package com.CarritoCompras.Presentation.Controller;

import com.CarritoCompras.Mapper.IMapperMercado;
import com.CarritoCompras.Model.DTO.MercadoDTO;
import com.CarritoCompras.Service.Interface.IMercadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
@RequestMapping("/mercado")
public class MercadoController {
    @Autowired
    private IMercadoService mercadoService;

    @Autowired
    private IMapperMercado mercadoMapper;

    @PostMapping
    public ResponseEntity<MercadoDTO> crearMercado(@RequestBody MercadoDTO mercadoDTO) {
        var mercadoEntity = mercadoMapper.mercadoDTOToMercadoEntity(mercadoDTO);
        var mercadoCreado = mercadoService.crearMercado(mercadoEntity);
        return new ResponseEntity<>(mercadoMapper.mercadoEntityToMercadoDTO(mercadoCreado), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MercadoDTO> obtenerMercadoPorId(@PathVariable Long id) {
        var mercadoOpt = mercadoService.obtenerMercadoPorId(id);
        if (mercadoOpt.isPresent()) {
            return new ResponseEntity<>(mercadoMapper.mercadoEntityToMercadoDTO(mercadoOpt.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<MercadoDTO>> listarMercados() {
        var mercados = mercadoService.listarMercados();
        var mercadosDTO = mercados.stream()
                .map(mercadoMapper::mercadoEntityToMercadoDTO)
                .toList();
        return new ResponseEntity<>(mercadosDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MercadoDTO> actualizarMercado(@PathVariable Long id, @RequestBody MercadoDTO mercadoDTO) {
        var mercadoEntity = mercadoMapper.mercadoDTOToMercadoEntity(mercadoDTO);
        var mercadoActualizado = mercadoService.actualizarMercado(id, mercadoEntity);
        if (mercadoActualizado.isPresent()) {
            return new ResponseEntity<>(mercadoMapper.mercadoEntityToMercadoDTO(mercadoActualizado.get()), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mercado no encontrado con ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarMercado(@PathVariable Long id) {
        if (!mercadoService.eliminarMercado(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar. Mercado no encontrado con ID: " + id);
        }
        // Considera que NO_CONTENT (204) típicamente no retorna cuerpo; ajusta según tus necesidades.
        return new ResponseEntity<>("Mercado eliminado exitosamente", HttpStatus.NO_CONTENT);
    }
}
