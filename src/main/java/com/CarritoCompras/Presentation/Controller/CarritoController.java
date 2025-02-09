package com.CarritoCompras.Presentation.Controller;

import com.CarritoCompras.Model.DTO.CarritoDTO;
import com.CarritoCompras.Service.Interface.ICarritoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carritos")

public class CarritoController {

    @Autowired
    ICarritoService carritoService;

    // Crear un carrito nuevo
    @PostMapping
    public ResponseEntity<CarritoDTO> crearCarrito(@Valid @RequestBody CarritoDTO carritoDTO) {
        // Llamamos al servicio para crear el carrito y obtener el DTO
        CarritoDTO carritoCreado = carritoService.crearCarrito(carritoDTO);

        // Retornamos la respuesta con el DTO y el estado 201 (CREATED)
        return new ResponseEntity<>(carritoCreado, HttpStatus.CREATED);
    }

    // Obtener el carrito de un cliente por su ID
    @GetMapping("/{clienteId}")
    public ResponseEntity<CarritoDTO> obtenerCarritoPorClienteId(@PathVariable Long clienteId) {
        // Llamamos al servicio para obtener el carrito
        CarritoDTO carritoDTO = carritoService.obtenerCarritoPorClienteId(clienteId);

        // Si no encontramos el carrito, respondemos con un estado 404 (NOT FOUND)
        if (carritoDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Si encontramos el carrito, respondemos con el carrito y estado 200 (OK)
        return new ResponseEntity<>(carritoDTO, HttpStatus.OK);
    }

    // Agregar un producto al carrito
    @PutMapping("/{carritoId}/producto/{productoId}")
    public ResponseEntity<Void> agregarProductoAlCarrito(
            @PathVariable Long carritoId,
            @PathVariable Long productoId,
            @RequestParam Integer cantidad) {

        carritoService.agregarProductoAlCarrito(carritoId, productoId, cantidad);

        // Responder con estado 204 (NO CONTENT) ya que no devolvemos cuerpo en la respuesta
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Eliminar un producto del carrito
    @DeleteMapping("/{carritoId}/producto/{productoId}")
    public ResponseEntity<Void> eliminarProductoDelCarrito(
            @PathVariable Long carritoId,
            @PathVariable Long productoId) {

        carritoService.eliminarProductoDelCarrito(carritoId, productoId);

        // Responder con estado 204 (NO CONTENT) ya que no devolvemos cuerpo en la respuesta
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Modificar la cantidad de un producto en el carrito
    @PatchMapping("/{carritoId}/producto/{productoId}")
    public ResponseEntity<Void> modificarCantidadProductoEnCarrito(
            @PathVariable Long carritoId,
            @PathVariable Long productoId,
            @RequestParam Integer nuevaCantidad) {

        carritoService.modificarCantidadProductoEnCarrito(carritoId, productoId, nuevaCantidad);

        // Responder con estado 204 (NO CONTENT) ya que no devolvemos cuerpo en la respuesta
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Eliminar un carrito
    @DeleteMapping("/{carritoId}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable Long carritoId) {
        carritoService.eliminarCarrito(carritoId);

        // Responder con estado 204 (NO CONTENT) ya que no devolvemos cuerpo en la respuesta
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
