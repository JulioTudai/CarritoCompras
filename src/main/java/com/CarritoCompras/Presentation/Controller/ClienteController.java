package com.CarritoCompras.Presentation.Controller;

import com.CarritoCompras.Model.DTO.ClienteDTO;
import com.CarritoCompras.Service.Interface.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/find")
    public ResponseEntity<List<ClienteDTO>> findAll(){

        return new ResponseEntity<>(this.clienteService.finAll(), HttpStatus.OK);
    }

    @GetMapping("/find{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id){

        return new ResponseEntity<>(this.clienteService.findById(id), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<ClienteDTO> saveCliente(@RequestBody ClienteDTO clienteDTO){

        return new ResponseEntity<>(this.clienteService.saveCliente(clienteDTO),HttpStatus.CREATED);
    }

}
