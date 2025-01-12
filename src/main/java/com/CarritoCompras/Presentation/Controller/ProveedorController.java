package com.CarritoCompras.Presentation.Controller;

import com.CarritoCompras.Model.DTO.ProveedorDTO;
import com.CarritoCompras.Service.Interface.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    IProveedorService proveedorService;

    @GetMapping("/find")
    public ResponseEntity<List<ProveedorDTO>> findAll(){

        return new ResponseEntity<>(this.proveedorService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<ProveedorDTO> findById(@PathVariable Long id){

        return new ResponseEntity<>(this.proveedorService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProveedorDTO> saveProveedor(@RequestBody ProveedorDTO proveedorDTO){
        System.out.println("Nombre proveedor " + proveedorDTO.getName());

        return new ResponseEntity<>(this.proveedorService.saveProveedor(proveedorDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProveedorDTO> updateProovedor(@PathVariable Long id,@RequestBody ProveedorDTO proveedorDTO){

        return new ResponseEntity<>(this.proveedorService.updateProveedor(id,proveedorDTO),HttpStatus.CREATED);
    }

    @DeleteMapping("/delet/{id}")
    public ResponseEntity<String> deletProveedor(@PathVariable Long id){

        return new ResponseEntity<>(this.proveedorService.deletProveedor(id),HttpStatus.NO_CONTENT);
    }




}
