package com.CarritoCompras.Presentation.Controller;

import com.CarritoCompras.Model.DTO.ClienteDTO;
import com.CarritoCompras.Service.Interface.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        return new ResponseEntity<>(this.clienteService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id){

        return new ResponseEntity<>(this.clienteService.findById(id), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<ClienteDTO> saveCliente(@RequestBody ClienteDTO clienteDTO){

        return new ResponseEntity<>(this.clienteService.saveCliente(clienteDTO),HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id,@RequestBody ClienteDTO clienteDTO){


        return new ResponseEntity<>(this.clienteService.updateCliente(id, clienteDTO),HttpStatus.CREATED);
    }

    @DeleteMapping("/delet/{id}")
    public ResponseEntity<String> deletCliente(@PathVariable Long id){


        return new ResponseEntity<>(clienteService.deletCliente(id),HttpStatus.NO_CONTENT);
    }

}
