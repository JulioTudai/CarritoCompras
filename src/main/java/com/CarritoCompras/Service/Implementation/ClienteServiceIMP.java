package com.CarritoCompras.Service.Implementation;

import com.CarritoCompras.Mapper.IMapperCliente;
import com.CarritoCompras.Model.DTO.ClienteDTO;
import com.CarritoCompras.Model.Entity.ClienteEntity;
import com.CarritoCompras.Repository.ClienteRepository;
import com.CarritoCompras.Service.Interface.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceIMP implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public List<ClienteDTO> findAll() {

        return this.clienteRepository.findAll().stream()
                .map(ClienteEntity -> IMapperCliente.INSTANCE.toDTO(ClienteEntity))
                .collect(Collectors.toList());
    }

     @Override
    public ClienteDTO findById(Long id) {

        Optional<ClienteEntity> clienteBuscado = clienteRepository.findById(id);

        if (clienteBuscado.isPresent()){

            ClienteEntity clienteEntity = clienteBuscado.get();

            return IMapperCliente.INSTANCE.toDTO(clienteEntity);
        }
        return new ClienteDTO();
    }

    @Override
    public ClienteDTO saveCliente(ClienteDTO clienteDTO) {
        System.out.println(clienteDTO.getName());

        Optional<ClienteEntity> clienteBuscado = clienteRepository.findByName(clienteDTO.getName());
        if (clienteBuscado.isPresent()){
            throw new IllegalArgumentException("El usuario con el correo " + clienteDTO.getName() + " ya existe.");
        }
        try{

            ClienteEntity clienteEntity = IMapperCliente.INSTANCE.toEntity(clienteDTO);
            return IMapperCliente.INSTANCE.toDTO(clienteRepository.save(clienteEntity));

        } catch (Exception e) {
            throw new UnsupportedOperationException("Error al guardar el usuario");
        }
    }

    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO){

        if (!clienteRepository.existsById(id)){
            throw new IllegalArgumentException("El usuario con el id " + clienteDTO.getId() + " no existe.");
        }
        ClienteEntity clienteEntity = clienteRepository.findById(id).get();
        clienteEntity.setName(clienteDTO.getName());
        clienteEntity.setLastName(clienteDTO.getLastName());
        clienteEntity.setEmail(clienteDTO.getEmail());
        clienteEntity.setAge(clienteDTO.getAge());

        return IMapperCliente.INSTANCE.toDTO(clienteRepository.save(clienteEntity));

    }

    public String deletCliente(Long id){
        System.out.println("llegue a delet con el id :" + id);
        if(clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
            return "Cliente con ID " + id + " eliminado correctamente.";
        }
        else {
        throw new IllegalArgumentException("Cliente no encontrado con ID: " + id);
    }

    }


}
