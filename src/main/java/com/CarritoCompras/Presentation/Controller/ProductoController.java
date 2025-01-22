package com.CarritoCompras.Presentation.Controller;

import com.CarritoCompras.Model.DTO.ProductoDTO;
import com.CarritoCompras.Model.DTO.ProductoProveedorDTO;
import com.CarritoCompras.Service.Interface.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")

public class ProductoController {

    @Autowired
    private IProdutoService produtoService;

    @GetMapping("/find")
    public ResponseEntity<List<ProductoDTO>> findAll(){
        return  new ResponseEntity<>(this.produtoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProductoProveedorDTO> findById(@PathVariable Long id){

        return new ResponseEntity<>(this.produtoService.findById(id),HttpStatus.OK);

    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoDTO>> findByName(@RequestParam("name") String name){
        return new ResponseEntity<>(this.produtoService.findByName(name),HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<ProductoDTO> saveProducto(@RequestBody ProductoDTO productoDTO){

        return new ResponseEntity<>(this.produtoService.saveProducto(productoDTO),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductoDTO> updateProducto(@PathVariable Long id,@RequestBody ProductoDTO productoDTO){

        return new ResponseEntity<>(this.produtoService.updateProducto(id,productoDTO),HttpStatus.CREATED);
    }

    @DeleteMapping("/delet/{id}")
    public ResponseEntity<String> deletProducto(@PathVariable Long id){

        return new ResponseEntity<>(this.produtoService.deletProducto(id),HttpStatus.NO_CONTENT);
    }















}
